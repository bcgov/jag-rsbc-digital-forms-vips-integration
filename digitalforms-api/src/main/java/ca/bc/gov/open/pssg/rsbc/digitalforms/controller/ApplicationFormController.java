package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.apache.commons.lang3.EnumUtils;
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
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationIdResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONError;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.FORM_TYPE;
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

	@GetMapping(value = "/{formType}/{GUID}/application", produces = "application/json")
	@ApiOperation(value = "Get Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApplicationInfoSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationInfoResponse>> applicationFormGet(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "GUID", required = true) String formGuid) {
		JSONResponse<ApplicationInfoResponse> resp;
		try {
			validateFormType(formType);
			ApplicationResponse data = service.getApplicationForm(formType, formGuid);
			if (data.getRespCode() == DigitalFormsConstants.DIGITAL_FORMS_ORDS_SUCCESS_CD) {
				// success
				resp = new JSONResponse<>(new ApplicationInfoResponse(data.getApplicationInfo()));
				return new ResponseEntity<>(resp, HttpStatus.OK);
			} else {
				// failure
				return new ResponseEntity<>(buildErrorResponse(DigitalFormsConstants.NOT_FOUND, 404),
						HttpStatus.NOT_FOUND);
			}
		} catch (IllegalArgumentException e) {
			// invalid form type
			return new ResponseEntity<>(buildErrorResponse(e.getMessage(), 404), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(buildErrorResponse(DigitalFormsConstants.UNKNOWN_ERROR, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/{formType}/{noticeNo}/application", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Post Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success", response = ApplicationIdSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationIdResponse>> applicationFormPost(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "noticeNo", required = true) String noticeNo,
			@RequestBody(required = true) ApplicationFormData formData) {
		JSONResponse<ApplicationIdResponse> resp;
		try {
			validateFormType(formType);
			ApplicationResponse data = service.postApplicationForm(formType, noticeNo, formData);
			if (data.getRespCode() == DigitalFormsConstants.DIGITAL_FORMS_ORDS_SUCCESS_CD) {
				// success
				resp = new JSONResponse<>(
						new ApplicationIdResponse(data.getApplicationId(), data.getCreatedTime(), null));
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			} else {
				// failure
				return new ResponseEntity<>(buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED, 400),
						HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			// invalid form type
			return new ResponseEntity<>(buildErrorResponse(e.getMessage(), 404), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(buildErrorResponse(DigitalFormsConstants.UNKNOWN_ERROR, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping(value = "/{formType}/{GUID}/application", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Update Form data", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ApplicationIdSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationIdResponse>> applicationFormPatch(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "GUID", required = true) String formGuid,
			@RequestBody(required = true) ApplicationFormData formData) {
		JSONResponse<ApplicationIdResponse> resp;
		try {
			validateFormType(formType);
			ApplicationResponse data = service.patchApplicationForm(formType, formGuid, formData);
			if (data.getRespCode() == DigitalFormsConstants.DIGITAL_FORMS_ORDS_SUCCESS_CD) {
				// success
				resp = new JSONResponse<>(
						new ApplicationIdResponse(data.getApplicationId(), null, data.getUpdatedTime()));
				return new ResponseEntity<>(resp, HttpStatus.OK);
			} else {
				// failure
				return new ResponseEntity<>(buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED, 400),
						HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			// invalid form type
			return new ResponseEntity<>(buildErrorResponse(e.getMessage(), 404), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(buildErrorResponse(DigitalFormsConstants.UNKNOWN_ERROR, 500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void validateFormType(String formType) {
		if (!EnumUtils.isValidEnumIgnoreCase(FORM_TYPE.class, formType)) {
			throw new IllegalArgumentException(DigitalFormsConstants.INVALID_FORM_TYPE_ERROR);
		}
	}

	public <T> JSONResponse<T> buildErrorResponse(String errorMessage, int statusCode) {
		JSONResponse<T> errorResp = new JSONResponse<>();
		errorResp.setResp(DigitalFormsConstants.JSON_RESPONSE_FAIL);
		JSONError error = new JSONError(errorMessage, statusCode);
		errorResp.setError(error);
		return errorResp;
	}

}
