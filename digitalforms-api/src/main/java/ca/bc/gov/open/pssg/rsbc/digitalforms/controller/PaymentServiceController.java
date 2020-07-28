package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
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
	private class ReviewPaidSwaggerResponse extends JSONResponse<Boolean>{}
	private class PaymentStatusSwaggerResponse extends JSONResponse<PaymentStatusResponse> {}
	
	@Autowired 
	private PaymentService paymentService; 
	
	Logger logger = LoggerFactory.getLogger(PaymentServiceController.class);
	
	public PaymentServiceController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@ApiOperation(value = "Set Prohibition Review Paid", response = ReviewPaidSwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ReviewPaidSwaggerResponse.class)})
	@PostMapping(path = { "**/payment/**",
			"/{noticeNumber}/payment/{correlationId}" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<JSONResponse<Boolean>> setReviewPaid(
			@PathVariable (value="noticeNumber", required=true) Long noticeNumber,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody (required=true) PaymentTransRequest paymentInfo)  {
		
		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "setReviewPaid");
		logger.info("Set review paid request received [{}]", correlationId);
		
		try {
			boolean data = paymentService.setReviewPaid(noticeNumber, paymentInfo);
			JSONResponse<Boolean> resp = new JSONResponse<>(data);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} finally {
			MDC.clear();
		}
	}
	
	@ApiOperation(value = "Get Payment Status", response = PaymentStatusSwaggerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = PaymentStatusSwaggerResponse.class) })
	@GetMapping(value = { "**/payment/status/**",
			"{noticeNumber}/payment/status/{correlationId}" }, produces = "application/json")
	public ResponseEntity<JSONResponse<PaymentStatusResponse>> paymentStatusGet(
			@PathVariable(value = "noticeNumber", required = true) Long noticeNumber,
			@PathVariable(value = "correlationId", required = true) String correlationId) {
		
		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "paymentStatusGet");
		logger.info("Get payment status request received [{}]", correlationId);
		
		try {
			PaymentStatusResponse data = paymentService.getReviewPaymentStatus(noticeNumber);
			JSONResponse<PaymentStatusResponse> resp = new JSONResponse<>(data);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} finally {
			MDC.clear();
		}
	}
}
