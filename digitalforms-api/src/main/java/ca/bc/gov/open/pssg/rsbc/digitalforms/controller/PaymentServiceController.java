package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Payment Controller. 
 * 
 * @author shaunmillargov
 *
 */
@RestController
@Api(value = "Payment", tags = { "Payment" })
public class PaymentServiceController {
	
	// Provides generic type class defs for Swagger 2. 
	private class IRPReviewPaidSwaggerResponse extends JSONResponse<Boolean>{}
	
	@Autowired 
	private PaymentService paymentService; 
	
	public PaymentServiceController(PaymentServiceImpl paymentService) {
		this.paymentService = paymentService;
	}
	
	@ApiOperation(value = "Set Prohibition Review Paid", response = IRPReviewPaidSwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = IRPReviewPaidSwaggerResponse.class)})
	@PostMapping(path ="/{noticeNumber}/payment",
	consumes = "application/json",
	produces = "application/json"
	)	
	public ResponseEntity<JSONResponse<Boolean>> setIRPReviewPaid(@PathVariable (value="noticeNumber", required=true) Long noticeNumber, @RequestBody (required=true) PaymentTransRequest paymentInfo)  {
	    boolean data = paymentService.setReviewPaid(noticeNumber, paymentInfo);
	    JSONResponse<Boolean> resp = new JSONResponse<>(data);
	    return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
