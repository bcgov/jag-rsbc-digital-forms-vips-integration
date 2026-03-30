package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ca.bc.gov.open.jag.ordsvipsclient.api.DocumentApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.VipsDocumentOrdsResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ErrorDetails;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.StoreVIPSDocument;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.VipsDocumentResponse2;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Document", description = "Document")
public class DocumentController {

    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentApi documentApi;

    @Autowired
    private ConfigProperties properties;

    @Operation(summary = "Upload VIPS Document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping(
        value = { "/document/{correlationId}" },
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<VipsDocumentResponse2> documentCorrelationIdPost(
            @PathVariable String correlationId,
            @Valid @RequestPart("metadata") StoreVIPSDocument storeVIPSDocument,
            @RequestPart(value = "file", required = false) MultipartFile file) throws DigitalFormsException {

        logger.info("Heard a call to the endpoint 'documentCorrelationIdPost'");

        MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
        MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "documentCorrelationIdPost");

        VipsDocumentResponse2 resp = new VipsDocumentResponse2();
        VipsDocumentOrdsResponse _resp = null;

        try {
            if (file == null || file.isEmpty()) {
                resp.setResp("fail");
                ErrorDetails err = new ErrorDetails();
                err.setMessage("file is required");
                err.setHttpStatus(400);
                resp.setError(err);
                return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
            }

            _resp = documentApi.storeDocumentPost(
                    storeVIPSDocument.getTypeCode(),           // required
                    "application",                             // mime type is always 'application'
                    "pdf",                                     // subtype is always 'pdf'
                    properties.getOrdsUserGuid(),             // required
                    file.getBytes(),                           // required - binary file content
                    storeVIPSDocument.getNoticeTypeCode(),     // optional
                    storeVIPSDocument.getNoticeSubjectCode(),
                    null                                       // optional page count
            );

            if (_resp.getStatusCode().equals(Integer.toString(DigitalFormsConstants.ORDS_SUCCESS_CD))) {
                resp.setDocumentId(_resp.getDocumentId());
                resp.setResp("success");
                return new ResponseEntity<>(resp, HttpStatus.CREATED);
            } else {
                resp.setResp("fail");
                ErrorDetails err = new ErrorDetails();
                err.setMessage("Bad Request");
                err.setHttpStatus(400);
                resp.setError(err);
                return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
            }

        } catch (ApiException e) {
            String err = "Upstream 500 error indicated from the SSG. VIPS ORDS returned 400, Bad Request";
            logger.info(err);

            resp.setResp("fail");
            ErrorDetails err1 = new ErrorDetails();
            err1.setMessage(err);
            err1.setHttpStatus(400);
            resp.setError(err1);

            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);

        } catch (IOException e) {
            logger.error("Failed to read uploaded file", e);

            resp.setResp("fail");
            ErrorDetails err = new ErrorDetails();
            err.setMessage("Failed to read uploaded file");
            err.setHttpStatus(400);
            resp.setError(err);

            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);

        } finally {
            MDC.clear();
        }
    }
}