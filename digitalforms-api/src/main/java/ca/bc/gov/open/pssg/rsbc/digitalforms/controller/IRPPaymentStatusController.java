package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPPaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * IRP Payment Status.
 * 
 * @author sivakaruna
 *
 */
@RestController
@RequestMapping("/IRP")
@Api(value = "IRP Payment Status", tags = { "IRP Payment Status" })
public class IRPPaymentStatusController {
	
	// Provides generic type class defs for Swagger 2. 
	private class IRPPaymentStatusSwaggerResponse extends JSONResponse<IRPPaymentStatusResponse>{}

	@Autowired
	IRPPaymentService irpPaymentService;

	@GetMapping(value = "/{irpNoticeNumber}/payment/status")
	@ApiOperation(value = "Get IRP Payment Status", response = IRPPaymentStatusSwaggerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = IRPPaymentStatusSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<IRPPaymentStatusResponse>> irpPaymentStatusGet(
			@PathVariable(value = "irpNoticeNumber", required = true) Long irpNoticeNumber) {
		IRPPaymentStatusResponse data = irpPaymentService.getIRPReviewPaymentStatus(irpNoticeNumber);
		JSONResponse<IRPPaymentStatusResponse> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
