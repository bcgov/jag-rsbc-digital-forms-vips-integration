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

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewFormRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.FormSubmissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Prohibition Form Submission Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@RequestMapping("/IRP")
@Api(value = "Form Submission", tags = { "Form Submission" })
public class FormSubmissionController {

	@Autowired
	FormSubmissionService service;

	@GetMapping(value = "/{noticeNumber}/application")
	@ApiOperation(value = "Get IRP Review Form", response = JSONResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = JSONResponse.class)})
	public ResponseEntity<JSONResponse<String>> reviewFormGet(
			@PathVariable(value = "noticeNumber", required = true) Long noticeNumber) {
		String data = service.getReviewForm(noticeNumber);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping(value = "/{noticeNumber}/application")
	@ApiOperation(value = "Post IRP Review Form", response = JSONResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Success", response = JSONResponse.class)})
	public ResponseEntity<JSONResponse<String>> reviewFormPost(
			@PathVariable(value = "noticeNumber", required = true) Long noticeNumber,
			@RequestBody(required = true) ReviewFormRequest formData) {
		String data = service.postReviewForm(noticeNumber, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{noticeNumber}/application/{id}")
	@ApiOperation(value = "Patch IRP Review Form", response = JSONResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = JSONResponse.class)})
	public ResponseEntity<JSONResponse<String>> reviewFormPatch(
			@PathVariable(value = "noticeNumber", required = true) Long noticeNumber,
			@PathVariable(value = "id", required = true) Long id, @RequestBody(required = true) ReviewFormRequest formData) {
		String data = service.patchReviewForm(noticeNumber, id, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
