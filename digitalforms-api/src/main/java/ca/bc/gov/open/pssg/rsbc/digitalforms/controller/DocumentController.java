package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * 
 * Document service Controller.
 * 
 * @author 176899
 *
 */
@RestController
@Tag(name = "Document", description = "Document")
public class DocumentController {

	private final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentApi documentApi;

	@Autowired
	private ConfigProperties properties;

	
	/**
	 * POST Document (Store document in VIPS) 
	 * 
	 * Possible Response codes from SSG (2):
	 * 	200. Success (translated to 201 from this controller).    
	 *  500. Catch all (translated to 400 from this controller).  
	 *  
	 * @throws ApiException 
	 */
	@Operation(summary = "Upload VIPS Document")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Success"),
	        @ApiResponse(responseCode = "400", description = "Bad Request")})
	@PostMapping(value = { "/document/{correlationId}" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<VipsDocumentResponse2> documentCorrelationIdPost(
			@PathVariable String correlationId,
	        @Valid @RequestBody StoreVIPSDocument storeVIPSDocument) throws DigitalFormsException {
		
		logger.info("Heard a call to the endpoint 'documentCorrelationIdPost'");
		
		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "documentCorrelationIdPost");
		
		VipsDocumentResponse2 resp = new VipsDocumentResponse2();
	
		VipsDocumentOrdsResponse _resp = null; 
		
		try {
			_resp = documentApi.storeDocumentPost(
						storeVIPSDocument.getTypeCode(),				//required
						"application", 									//mime type is always 'application'
						"pdf",											//subtype is always 'pdf'	 
						properties.getOrdsUserGuid(),					//required 
						storeVIPSDocument.getFileObject(), 				//required 
						storeVIPSDocument.getNoticeTypeCode(),			//optional 
						storeVIPSDocument.getNoticeSubjectCode(),
						null);											//optional page count. 
			
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
			err1.setMessage("Upstream 500 error indicated from the SSG. VIPS ORDS returned 400, Bad Request");
			err1.setHttpStatus(400);
			resp.setError(err1);
			
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
			
		} finally {
			MDC.clear();
		}
				 
	}
	
}

