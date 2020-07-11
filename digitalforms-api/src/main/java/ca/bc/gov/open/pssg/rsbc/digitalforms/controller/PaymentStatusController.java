package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Payment Status.
 * 
 * @author sivakaruna
 *
 */
@RestController
@Api(value = "Payment", tags = { "Payment" })
public class PaymentStatusController {
	
	// Provides generic type class defs for Swagger 2. 
	private class PaymentStatusSwaggerResponse extends JSONResponse<PaymentStatusResponse>{}

	@Autowired
	PaymentService paymentService;

	@GetMapping(value = "{formType}/{noticeNumber}/payment/status")
	@ApiOperation(value = "Get Payment Status", response = PaymentStatusSwaggerResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = PaymentStatusSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<PaymentStatusResponse>> paymentStatusGet(
			@PathVariable(value = "noticeNumber", required = true) Long noticeNumber) {
		PaymentStatusResponse data = paymentService.getReviewPaymentStatus(noticeNumber);
		JSONResponse<PaymentStatusResponse> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
