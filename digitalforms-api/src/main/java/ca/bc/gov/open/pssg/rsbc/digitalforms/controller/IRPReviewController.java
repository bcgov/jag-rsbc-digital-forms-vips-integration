package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPReviewFormRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * IRP Review Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@RequestMapping("/IRP")
@Api(value = "IRP Review", tags = { "IRP Review" })
public class IRPReviewController {

	@Autowired
	IRPReviewService irpService;

	@GetMapping(value = "/{irpNoticeNumber}/application")
	@ApiOperation(value = "Get IRP Review Form", response = JSONResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = JSONResponse.class)})
	public ResponseEntity<JSONResponse<String>> irpReviewFormGet(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber) {
		String data = irpService.getIRPReviewForm(irpNoticeNumber);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping(value = "/{irpNoticeNumber}/application")
	@ApiOperation(value = "Post IRP Review Form", response = JSONResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Success", response = JSONResponse.class)})
	public ResponseEntity<JSONResponse<String>> irpReviewFormPost(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber,
			@RequestBody(required = true) IRPReviewFormRequest formData) {
		String data = irpService.postIRPReviewForm(irpNoticeNumber, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{irpNoticeNumber}/application/{id}")
	@ApiOperation(value = "Patch IRP Review Form", response = JSONResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = JSONResponse.class)})
	public ResponseEntity<JSONResponse<String>> irpReviewFormPatch(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber,
			@PathVariable(value = "id", required = true) Long id, @RequestBody(required = true) IRPReviewFormRequest formData) {
		String data = irpService.patchIRPReviewForm(irpNoticeNumber, id, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
