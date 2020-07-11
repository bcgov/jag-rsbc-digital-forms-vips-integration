package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

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
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;
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

	@Autowired
	ApplicationFormService service;

	@GetMapping(value = "/{formType}/application/{formGuid}", produces = "application/json")
	@ApiOperation(value = "Get Form data", response = JSONResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = JSONResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationResponse>> applicationFormGet(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "formGuid", required = true) String formGuid) {
		ApplicationResponse data = service.getApplicationForm(formType, formGuid);
		JSONResponse<ApplicationResponse> resp = new JSONResponse<>(data);

		if (data.getRespCode() == DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD) {
			return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping(value = "/{formType}/application", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Post Form data", response = JSONResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = JSONResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationResponse>> applicationFormPost(
			@PathVariable(value = "formType", required = true) String formType,
			@RequestBody(required = true) ApplicationFormData formData) {
		ApplicationResponse data = service.postApplicationForm(formType, formData);
		JSONResponse<ApplicationResponse> resp = new JSONResponse<>(data);
		if (data.getRespCode() == DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD) {
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{formType}/application/{formGuid}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Update Form data", response = JSONResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = JSONResponse.class) })
	public ResponseEntity<JSONResponse<ApplicationResponse>> applicationFormPatch(
			@PathVariable(value = "formType", required = true) String formType,
			@PathVariable(value = "formGuid", required = true) String formGuid,
			@RequestBody(required = true) ApplicationFormData formData) {
		ApplicationResponse data = service.patchApplicationForm(formType, formGuid, formData);
		JSONResponse<ApplicationResponse> resp = new JSONResponse<>(data);
		if (data.getRespCode() == DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD) {
			return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
