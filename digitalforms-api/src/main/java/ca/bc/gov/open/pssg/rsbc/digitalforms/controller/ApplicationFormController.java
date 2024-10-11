package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 *
 * Application Form submission Controller.
 *
 * @author sivakaruna
 *
 */
@RestController
@Tag(name = "Application Form", description = "Application Form" )
public class ApplicationFormController {

	private class ApplicationInfoSwaggerResponse extends JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>> {
	}

	private class ApplicationIdSwaggerResponse extends JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>> {
	}

	private class ApplicationExistsSwaggerResponse extends JSONResponse<ApplicationIdResponse> {
	}

	@Autowired
	ApplicationFormService service;

	private final Logger logger = LoggerFactory.getLogger(ApplicationFormController.class);

	@GetMapping(value = { "**/application/**",
			"/{applicationId}/application/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Get Form data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>>> applicationFormGet(
			@PathVariable(value = "applicationId", required = true) String applicationId,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormGet");
		logger.info("Get application form request received");

		ApplicationResponse data = service.getApplicationForm(applicationId, correlationId);
		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>> resp = new JSONResponse<>(
					new ApplicationInfoWrapper<>(new ApplicationInfoResponse(data.getApplicationInfo())));
			logger.info("Get application form data request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Get application form data not found");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_FOUND_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = { "**/application/**",
			"/application/notice/{noticeId}/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Get Form data")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<JSONResponse<ApplicationExistsResponse>> applicationFormExists(
			@PathVariable(value = "noticeId", required = true) String noticeId,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormExists");
		logger.info("Get application exists request received");

		ApplicationResponse data = service.getApplicationExists(noticeId, correlationId);
		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<ApplicationExistsResponse> resp = new JSONResponse<>(
					new ApplicationExistsResponse(data.getApplicationId(), data.getFormExists()));
			logger.info("Get application exists request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		else {
			logger.info("Get application exists request error");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_FOUND_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = { "**/application/**",
			"/{formType}/{noticeNumber}/application/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Post Form data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Success") })
	public ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> applicationFormPost(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "noticeNumber", required = true) String noticeNumber,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@Valid @RequestBody(required = true) ApplicationInfoWrapper<ApplicationFormDataPost> formData)
			throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormPost");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Post application form request received");

		// Validate request fields
		DigitalFormsUtils.validateFormType(formType);
		formData.getApplicationInfo().validateRequiredFields();

		//DigitalFormsUtils.validateApplicationForm(formData.getApplicationInfo(), formType);
		DigitalFormsUtils.validateApplicationForm(formData.getApplicationInfo().getNoticeSubjectCd(),
				formData.getApplicationInfo().getPresentationTypeCd(),
				formData.getApplicationInfo().getReviewRoleTypeCd(),
				formData.getApplicationInfo().getManualEntryYN(),
				formType);

		ApplicationResponse data = service.postApplicationForm(formType, noticeNumber, correlationId,
				formData.getApplicationInfo());


		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>> resp = new JSONResponse<>(
					new ApplicationInfoWrapper<>(
							new ApplicationIdResponse(data.getApplicationId(), data.getCreatedTime(), null)));
			logger.info("Post application form data request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} else {
			logger.info("Post application form data not processed");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED_ERROR, 400),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PatchMapping(value = { "**/application/**",
			"/{formType}/{applicationId}/application/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Update Form data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> applicationFormPatch(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "applicationId", required = true) String applicationId,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@Valid @RequestBody(required = true) ApplicationInfoWrapper<ApplicationFormDataPatch> formData)
			throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormPatch");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Patch application form request received");

		// Validate request fields
		DigitalFormsUtils.validateFormType(formType);
		
		//DigitalFormsUtils.validateApplicationForm(formData.getApplicationInfo(), formType);
		DigitalFormsUtils.validateApplicationForm(
				formData.getApplicationInfo().getNoticeSubjectCd(),
				formData.getApplicationInfo().getPresentationTypeCd(), 
				formData.getApplicationInfo().getReviewRoleTypeCd(), 
				formData.getApplicationInfo().getManualEntryYN(), 
				formType);
		
		ApplicationResponse data = service.patchApplicationForm(formType, applicationId, correlationId,
				formData.getApplicationInfo());
		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>> resp = new JSONResponse<>(
					new ApplicationInfoWrapper<>(
							new ApplicationIdResponse(data.getApplicationId(), null, data.getUpdatedTime())));
			logger.info("Patch application form data request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Patch application form data not processed");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED_ERROR, 400),
					HttpStatus.BAD_REQUEST);
		}
	}
}
