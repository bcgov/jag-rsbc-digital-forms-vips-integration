package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransaction;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 * 
 * Payment Controller.
 * 
 * @author shaunmillargov
 *
 */
@RestController
@Tag(name = "Payment", description = "Payment")
public class PaymentServiceController {

	// Provides generic type class defs for Swagger 2.
	private class ReviewPaidSwaggerResponse extends JSONResponse<Boolean> {
	}

	private class PaymentStatusSwaggerResponse extends JSONResponse<PaymentTransaction> {
	}

	@Autowired
	private PaymentService paymentService;

	private final Logger logger = LoggerFactory.getLogger(PaymentServiceController.class);

	public PaymentServiceController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Operation(summary = "Set Prohibition Review Paid")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success") })
	@PatchMapping(path = { "/{applicationId}/payment/{correlationId}" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<JSONResponse<Boolean>> setReviewPaid(
			@PathVariable(value = "applicationId", required = true) String applicationId,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) PaymentTransaction paymentInfo) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "setReviewPaid");
		logger.info("Set review paid request received");
		
		// Validate payment date format
		DigitalFormsUtils.validateTimeDate(paymentInfo.getTransactionInfo().getPaymentDate());
		
		PaymentResponse data = paymentService.setReviewPaid(applicationId, correlationId, paymentInfo);

		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<Boolean> resp = new JSONResponse<>(Boolean.TRUE);
			logger.info("Set review paid request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Set review paid request not processed");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Get Payment Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success") })
	@GetMapping(value = { "{applicationId}/payment/status/{correlationId}" }, produces = "application/json")
	public ResponseEntity<JSONResponse<PaymentTransaction>> paymentStatusGet(
			@PathVariable(value = "applicationId", required = true) String applicationId,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "paymentStatusGet");
		logger.info("Get payment status request received");

		PaymentResponse data = paymentService.getReviewPaymentStatus(applicationId, correlationId);
		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<PaymentTransaction> resp = new JSONResponse<>(new PaymentTransaction(data.getPaymentStatus()));
			logger.info("Get payment status request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Get payment status data not found");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_FOUND_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}
}
