package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import ca.bc.gov.open.pssg.rsbc.digitalforms.dao.VipswsDao;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.*;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.VipswsConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * API pass-through for Impoundment & Documents VIPS API calls
 */
@RestController
@Api(value = "Impoundment", tags = { "Impoundment" })
public class ImpoundmentController {

    private final VipswsDao vipswsdao;

    private final Logger logger;

    @Autowired
    public ImpoundmentController(VipswsDao vipswsdao) {
        this.vipswsdao = vipswsdao;
        this.logger = LoggerFactory.getLogger(ImpoundmentController.class);
    }

    /**
     *  Note that this is a multi-part part process.
     *
     *  If validation passes above, and 'update' is indicated from back end,
     *  perform update impoundment,
     *  else perform create impoundment.
     *
     */
    @PostMapping(value = { "/cases/impoundment/",
        "/cases/impoundment/" }, produces = DigitalFormsConstants.JSON_CONTENT)
    @ApiOperation(value = "Create an impoundment", response = CreateImpoundmentServiceResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = CreateImpoundmentServiceResponse.class) })
    public ResponseEntity<CreateImpoundmentServiceResponse> saveImpoundment(
        @ApiParam(value = "")
        @Valid
        @RequestBody CreateImpoundment body) {

        logger.info("Data posted to getImpoundment. NoticeNumber = " + body.getVipsImpoundmentCreate().getImpoundmentNoticeNo());
        logger.info("Pre-validating incoming Impoundment, object: " + objectToJSON(body));

        PrevalidateImpoundmentServiceResponse preValResp = vipswsdao.prevalidateImpoundment(body);

        // Failure on prevalidation
        if ( checkForFailure( preValResp.getRespCd() ) ) {

            logger.info("Pre-validation failed, respCd from VIPS was " + preValResp.getRespCd() + " and message: " + preValResp.getRespMsg());

            // re map response type
            CreateImpoundmentServiceResponse  resp = new CreateImpoundmentServiceResponse();
            resp.setRespCd(preValResp.getRespCd());
            resp.setRespMsg(preValResp.getRespMsg());

            return new ResponseEntity<>(resp, HttpStatus.OK);

            // Perform the rest of the calls dependent on 'update' or 'create' review decision type indicated from back end.
        } else {

            logger.info("Pre-validation passed. Saving impoundment to VIPS.");

            if ( preValResp.isUpdate() ) {

                logger.info("Pre-validation passed. Updating the impoundment in VIPS");

                // update case
                return updateImpoundment( preValResp, body);

            } else {

                logger.info("Pre-validation passed. Creating the impoundment in VIPS");

                // create case
                return createImpoundment( preValResp, body);

            }
        }
    }

    @GetMapping(value = { "/cases/impoundment/{impoundmentId}",
        "/cases/impoundment/{impoundmentId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
    @ApiOperation(value = "Retrieve an impoundment", response = GetImpoundmentServiceResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = GetImpoundmentServiceResponse.class) })
    public ResponseEntity<GetImpoundmentServiceResponse> getImpoundment(
        @PathVariable(value = "impoundmentId", required = true) Long impoundmentId) {
        return vipswsdao.getImpoundment(impoundmentId);
    }

    @PutMapping(value = { "/cases/impoundment/doc/",
        "/cases/impoundment/doc/" }, produces = DigitalFormsConstants.JSON_CONTENT)
    @ApiOperation(value = "Create a impoundment document association", response = UpdateDocumentAssocServiceResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = UpdateDocumentAssocServiceResponse.class) })
    public ResponseEntity<UpdateDocumentAssocServiceResponse> saveImpoundmentDocument(
        @ApiParam(value = "")
        @Valid
        @RequestBody VipsDocAssocUpdateObj body) {

        logger.info("Data posted to saveImpoundmentDocument. DocumentId = " + body.getDocumentId());
        logger.info("Pre-validating incoming Impoundment Document, object: " + objectToJSON(body));

        PrevalidateDocumentServiceResponse preValResp = vipswsdao.prevalidateDocument(body);

        // Failure on prevalidation
        if ( checkForFailure( preValResp.getRespCd() ) ) {

            logger.info("Pre-validation failed, respCd from VIPS was " + preValResp.getRespCd() + " and message: " + preValResp.getRespMsg());

            // re map response type
            UpdateDocumentAssocServiceResponse  resp = new UpdateDocumentAssocServiceResponse();
            resp.setRespCd(preValResp.getRespCd());
            resp.setRespMsg(preValResp.getRespMsg());

            return new ResponseEntity<>(resp, HttpStatus.OK);

            // Perform the rest of the calls dependent on 'update' or 'create' review decision type indicated from back end.
        } else {
            logger.info("Pre-validation passed. Creating the prohibition document in VIPS");

            // create case
            return createDocument( preValResp, body);
        }
    }

    @GetMapping(value = { "/cases/impoundment/doc/{documentId}",
        "/cases/impoundment/doc/{documentId}" }, produces = DigitalFormsConstants.TEXT_CONTENT)
    @ApiOperation(value = "Get a impoundment document", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = String.class) })
    public ResponseEntity<String> getImpoundmentDocument(
        @PathVariable(value = "documentId", required = true) Long documentId,
        @RequestParam(value = "b64", required = false) Boolean documentInB64,
        @RequestParam(value = "url", required = false) Boolean documentAsUrl) {

        logger.info("Data posted to getImpoundmentDocument. DocumentId = " + documentId);
        GetDocument documentParams = new GetDocument();
        documentParams.setDocumentInB64(documentInB64);
        documentParams.setDocumentAsUrl(documentAsUrl);

        return getDocument(documentId, documentParams);
    }

    /**
     *
     * createImpoundment
     *
     * @param preValResp - pre validation response
     * @param body
     * @return
     */
    private ResponseEntity<CreateImpoundmentServiceResponse> createImpoundment(PrevalidateImpoundmentServiceResponse preValResp, CreateImpoundment body) {
        return new ResponseEntity<>(vipswsdao.createImpoundment(body), HttpStatus.OK);
    }

    /**
     *
     * updateImpoundment
     *
     * @param preValResp
     * @param body
     * @return
     */
    private ResponseEntity<CreateImpoundmentServiceResponse> updateImpoundment(
            PrevalidateImpoundmentServiceResponse preValResp, CreateImpoundment body) {
        return new ResponseEntity<>(vipswsdao.createImpoundment(body), HttpStatus.OK);
    }

    /**
     *
     * createDocument
     *
     * @param preValResp - pre validation response
     * @param body
     * @return
     */
    private ResponseEntity<UpdateDocumentAssocServiceResponse> createDocument(
            PrevalidateDocumentServiceResponse preValResp, VipsDocAssocUpdateObj body) {
        return vipswsdao.createDocumentAssociation(body);
    }

    /**
     *
     * getDocument
     *
     * @param preValResp - pre validation response
     * @param documentId of case document
     * @return
     */
    private ResponseEntity<String> getDocument(Long documentId, GetDocument documentParams) {
        return vipswsdao.getDocument(documentId, documentParams);
    }

    /**
     *
     * Helper function which displays java objects as JSON
     *
     * @param javaObj
     * @return
     */
    private String objectToJSON(Object javaObj) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(javaObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Check for good or bad response values
     *
     * true == failure case
     *
     * @param respCd
     * @return
     */
    private boolean checkForFailure (int respCd) {
        return ( respCd == VipswsConstants.VIPSWS_GENERAL_FAILURE_CD ) || ( respCd == VipswsConstants.VIPSWS_JAVA_EX );
    }
}
