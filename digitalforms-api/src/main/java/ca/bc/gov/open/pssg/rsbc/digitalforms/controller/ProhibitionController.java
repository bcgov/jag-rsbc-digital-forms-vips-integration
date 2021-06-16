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
 * API pass-through for Prohibition & Documents VIPS API calls
 */
@RestController
@Api(value = "Prohibition", tags = { "Prohibition" })
public class ProhibitionController {

    private final VipswsDao vipswsdao;

    private final Logger logger;

    @Autowired
    public ProhibitionController(VipswsDao vipswsdao) {
        this.vipswsdao = vipswsdao;
        this.logger = LoggerFactory.getLogger(ProhibitionController.class);
    }

    /**
     *  Note that this is a multi-part part process.
     *
     *  If validation passes above, and 'update' is indicated from back end,
     *  perform update prohibition,
     *  else perform create prohibition.
     *
     */
    @PostMapping(value = { "/cases/prohibition/",
        "/cases/prohibition/" }, produces = DigitalFormsConstants.JSON_CONTENT)
    @ApiOperation(value = "Create a prohibition", response = CreateProhibitionServiceResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = CreateProhibitionServiceResponse.class) })
    public ResponseEntity<CreateProhibitionServiceResponse> saveProhibition(
        @ApiParam(value = "")
        @Valid
        @RequestBody CreateProhibition body) {

        logger.info("Data posted to saveProhibition. NoticeNumber = " + body.getVipsProhibitionCreate().getProhibitionNoticeNo());
        logger.info("Pre-validating incoming Prohibition, object: " + objectToJSON(body));

        PrevalidateProhibitionServiceResponse preValResp = vipswsdao.prevalidateProhibition(body);

        // Failure on prevalidation
        if ( checkForFailure( preValResp.getRespCd() ) ) {

            logger.info("Pre-validation failed, respCd from VIPS was " + preValResp.getRespCd() + " and message: " + preValResp.getRespMsg());

            // re map response type
            CreateProhibitionServiceResponse  resp = new CreateProhibitionServiceResponse();
            resp.setRespCd(preValResp.getRespCd());
            resp.setRespMsg(preValResp.getRespMsg());

            return new ResponseEntity<>(resp, HttpStatus.OK);

            // Perform the rest of the calls dependent on 'update' or 'create' review decision type indicated from back end.
        } else {

            logger.info("Pre-validation passed. Saving prohibition to VIPS.");

            if ( preValResp.isUpdate() ) {

                logger.info("Pre-validation passed. Updating the prohibition in VIPS");

                // update case
                return updateProhibition( preValResp, body);

            } else {

                logger.info("Pre-validation passed. Creating the prohibition in VIPS");

                // create case
                return createProhibition( preValResp, body);

            }
        }
    }

    @GetMapping(value = { "/cases/prohibition/{prohibitionId}",
        "/cases/prohibition/{prohibitionId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
    @ApiOperation(value = "Retrieve a prohibition", response = GetProhibitionServiceResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = GetProhibitionServiceResponse.class) })
    public ResponseEntity<GetProhibitionServiceResponse> getProhibition(
        @PathVariable(value = "prohibitionId", required = true) Long prohibitionId) {
        return vipswsdao.getProhibition(prohibitionId);
    }

    @PutMapping(value = { "/cases/prohibition/doc/",
        "/cases/prohibition/doc/" }, produces = DigitalFormsConstants.JSON_CONTENT)
    @ApiOperation(value = "Create a prohibition document association", response = UpdateDocumentAssocServiceResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = UpdateDocumentAssocServiceResponse.class) })
    public ResponseEntity<UpdateDocumentAssocServiceResponse> saveProhibitionDocument(
        @ApiParam(value = "")
        @Valid
        @RequestBody VipsDocAssocUpdateObj body) {

        logger.info("Data posted to saveProhibitionDocument. DocumentId = " + body.getDocumentId());
        logger.info("Pre-validating incoming Prohibition Document, object: " + objectToJSON(body));

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

    @GetMapping(value = { "/cases/prohibition/doc/{documentId}",
        "/cases/prohibition/doc/{documentId}" }, produces = DigitalFormsConstants.TEXT_CONTENT)
    @ApiOperation(value = "Get a prohibition document", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = String.class) })
    public ResponseEntity<String> getProhibitionDocument(
        @PathVariable(value = "documentId", required = true) Long documentId,
        @RequestParam(value = "b64", required = false) Boolean documentInB64,
        @RequestParam(value = "url", required = false) Boolean documentAsUrl) {

        logger.info("Data posted to getProhibitionDocument. DocumentId = " + documentId);

        GetDocument documentParams = new GetDocument();
        documentParams.setDocumentInB64(documentInB64);
        documentParams.setDocumentAsUrl(documentAsUrl);

        return getDocument(documentId, documentParams);
    }

    /**
     *
     * createProhibition
     *
     * @param preValResp - pre validation response
     * @param body
     * @return
     */
    private ResponseEntity<CreateProhibitionServiceResponse> createProhibition(
            PrevalidateProhibitionServiceResponse preValResp, CreateProhibition body) {
        return new ResponseEntity<>(vipswsdao.createProhibition(body), HttpStatus.OK);
    }

    /**
     *
     * updateProhibition
     *
     * @param preValResp
     * @param body
     * @return
     */
    private ResponseEntity<CreateProhibitionServiceResponse> updateProhibition(
            PrevalidateProhibitionServiceResponse preValResp, CreateProhibition body) {
        return new ResponseEntity<>(vipswsdao.createProhibition(body), HttpStatus.OK);
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
     * @param documentId of case document
     * @param documentParams document GET prarms
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
