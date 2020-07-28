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

	private class ApplicationInfoSwaggerResponse extends JSONResponse<ApplicationInfoResponse> {
	}

	private class ApplicationIdSwaggerResponse extends JSONResponse<ApplicationIdResponse> {
	}

	@Autowired
	ApplicationFormService service;

	Logger logger = LoggerFactory.getLogger(ApplicationFormController.class);

	@GetMapping(value = { "**/application/**",
			"/{formType}/{GUID}/application/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Get Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApplicationInfoSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationInfoResponse>> applicationFormGet(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "GUID", required = true) String formGuid,
			@PathVariable(value = "correlationId", required = true) String correlationId) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormGet");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Get application form request received [{}]", correlationId);

		try {
			DigitalFormsUtils.validateFormType(formType);
			ApplicationResponse data = service.getApplicationForm(formType, formGuid);
			if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
				JSONResponse<ApplicationInfoResponse> resp = new JSONResponse<>(
						new ApplicationInfoResponse(data.getApplicationInfo()));
				return new ResponseEntity<>(resp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_FOUND, 404),
						HttpStatus.NOT_FOUND);
			}
		} finally {
			MDC.clear();
		}

	}

	@PostMapping(value = { "**/application/**",
			"/{formType}/{noticeNo}/application/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Post Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success", response = ApplicationIdSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationIdResponse>> applicationFormPost(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "noticeNo", required = true) String noticeNo,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) ApplicationFormData formData) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormPost");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Post application form request received [{}]", correlationId);

		try {
			DigitalFormsUtils.validateFormType(formType);
			ApplicationResponse data = service.postApplicationForm(formType, noticeNo, formData);
			if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
				JSONResponse<ApplicationIdResponse> resp = new JSONResponse<>(
						new ApplicationIdResponse(data.getApplicationId(), data.getCreatedTime(), null));
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(
						DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED, 400),
						HttpStatus.BAD_REQUEST);
			}
		} finally {
			MDC.clear();
		}
	}

	@PatchMapping(value = { "**/application/**",
			"/{formType}/{GUID}/application/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Update Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApplicationIdSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationIdResponse>> applicationFormPatch(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "GUID", required = true) String formGuid,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) ApplicationFormData formData) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "applicationFormPatch");
		MDC.put(DigitalFormsConstants.REQUEST_FORMTYPE, formType);
		logger.info("Patch application form request received [{}]", correlationId);

		try {
			DigitalFormsUtils.validateFormType(formType);
			ApplicationResponse data = service.patchApplicationForm(formType, formGuid, formData);
			if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
				JSONResponse<ApplicationIdResponse> resp = new JSONResponse<>(
						new ApplicationIdResponse(data.getApplicationId(), null, data.getUpdatedTime()));
				return new ResponseEntity<>(resp, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED, 400),
						HttpStatus.BAD_REQUEST);
			}
		} finally {
			MDC.clear();
		}
	}
}
