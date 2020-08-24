package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

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

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationIdResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Application Form submission Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@Api(value = "Application Form", tags = { "Application Form" })
public class ApplicationFormController {

	private class ApplicationInfoSwaggerResponse extends JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>> {
	}

	private class ApplicationIdSwaggerResponse extends JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>> {
	}

	@Autowired
	ApplicationFormService service;

	private final Logger logger = LoggerFactory.getLogger(ApplicationFormController.class);

	@GetMapping(value = { "**/application/**",
			"/{GUID}/application/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Get Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApplicationInfoSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>>> applicationFormGet(
			@PathVariable(value = "GUID", required = true) String formGuid,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormGet");
		logger.info("Get application form request received");

		ApplicationResponse data = service.getApplicationForm(formGuid, correlationId);
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

	@PostMapping(value = { "**/application/**",
			"/{formType}/{noticeNumber}/application/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Post Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success", response = ApplicationIdSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> applicationFormPost(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "noticeNumber", required = true) String noticeNumber,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) ApplicationInfoWrapper<ApplicationFormData> formData)
			throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormPost");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Post application form request received");

		// Validate request fields
		DigitalFormsUtils.validateFormType(formType);
		formData.getApplicationInfo().validateRequiredFields();
		DigitalFormsUtils.validateApplicationForm(formData.getApplicationInfo(), formType);
		
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
			"/{formType}/{GUID}/application/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Update Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApplicationIdSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> applicationFormPatch(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "GUID", required = true) String formGuid,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) ApplicationInfoWrapper<ApplicationFormData> formData)
			throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormPatch");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Patch application form request received");

		// Validate request fields
		DigitalFormsUtils.validateFormType(formType);
		DigitalFormsUtils.validateApplicationForm(formData.getApplicationInfo(), formType);
		
		ApplicationResponse data = service.patchApplicationForm(formType, formGuid, correlationId,
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
