package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.jagvipsclient.disclosure.DisclosureResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DisclosureWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.DisclosureService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 * 
 * Disclosure service Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@Tag(name = "Disclosure", description = "Disclosure")
public class DisclosureController {

	private class DisclosureDocumentSwaggerResponse extends JSONResponse<DocumentWrapper> {
	}

	private class DocumentDisclosedSwaggerResponse extends JSONResponse<Boolean> {
	}

	@Autowired
	DisclosureService service;

	private final Logger logger = LoggerFactory.getLogger(DisclosureController.class);

	@GetMapping(value = { "**/disclosure/**", "/{documentId}/disclosure/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Get disclosure document")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<JSONResponse<DocumentWrapper>> getDisclosureDocument(
			@PathVariable(value = "documentId", required = true) String documentId,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "disclosureDocumentGet");
		logger.info("Get disclosure document request received");

		DisclosureResponse data = service.getDisclosureDocument(documentId, correlationId);
		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<DocumentWrapper> resp = new JSONResponse<>(new DocumentWrapper(data.getDocumentInfo()));
			logger.info("Get disclosure document request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Get disclosure document data not found");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_FOUND_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping(value = { "**/disclosure/**",
			"/disclosure/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Set Disclosure Document Sent")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<JSONResponse<Boolean>> setDisclosureSent(
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) DisclosureWrapper disclosureInfo) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "disclosureDocumentPatch");
		logger.info("Set disclosure document as sent request received");
		
		// Validate disclosedDtm format 
		DigitalFormsUtils.validateTimeDate(disclosureInfo.getDisclosure().getDisclosedDtm());

		DisclosureResponse data = service.setDisclosureSent(correlationId, disclosureInfo.getDisclosure());
		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<Boolean> resp = new JSONResponse<>(Boolean.TRUE);
			logger.info("Set disclosure document as sent request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Set disclosure document as sent request not processed");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED_ERROR, 400),
					HttpStatus.BAD_REQUEST);
		}
	}
}
