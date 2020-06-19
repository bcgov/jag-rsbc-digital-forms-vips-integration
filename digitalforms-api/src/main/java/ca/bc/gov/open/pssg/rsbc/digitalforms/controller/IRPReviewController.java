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
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryService;

/**
 * 
 * IRP Review Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@RequestMapping("/IRP")
public class IRPReviewController {

	@Autowired
	IRPQueryService irpService;

	@GetMapping(value = "/{irpNoticeNumber}/application")
	public ResponseEntity<JSONResponse<String>> irpReviewGet(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber,
			@RequestBody IRPReviewFormRequest formData) {
		String data = irpService.getIRPReview(irpNoticeNumber, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping(value = "/{irpNoticeNumber}/application")
	public ResponseEntity<JSONResponse<String>> irpReviewPost(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber,
			@RequestBody IRPReviewFormRequest formData) {
		String data = irpService.postIRPReview(irpNoticeNumber, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{irpNoticeNumber}/application/{id}")
	public ResponseEntity<JSONResponse<String>> irpReviewPatch(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber,
			@PathVariable(value = "id", required = true) Long id, @RequestBody IRPReviewFormRequest formData) {
		String data = irpService.patchIRPReview(irpNoticeNumber, id, formData);
		JSONResponse<String> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
