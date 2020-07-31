package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransaction;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.TransactionInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentServiceImpl;

/**
 * 
 * Payment Service Controller Tests.
 * 
 * @author shaunmillargov
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class PaymentServiceControllerTests {

	private final String IRP_TEST_NOTICE_NUMBER_SUCCESS = "1";
	private final String IRP_TEST_NOTICE_NUMBER_ERROR = "2";
	private final PaymentTransaction GOOD_TRANSACTION_REQUEST = new PaymentTransaction(
			new TransactionInfo("MC", "50.01", "12345", "2018-06-29 00:00:00 -07:00"));
	private final String CORRELATION_ID = "correlationId";
	private final String SUCCESS_STATUS = "success";
	private final String SUCCESS_CODE = "1";
	private final String ERROR_STATUS = "error";

	@MockBean
	private PaymentServiceImpl paymentService;

	private PaymentServiceController controller;

	@BeforeEach
	public void init() throws DigitalFormsException {
		controller = new PaymentServiceController(paymentService);
		when(paymentService.setReviewPaid(IRP_TEST_NOTICE_NUMBER_SUCCESS, GOOD_TRANSACTION_REQUEST))
				.thenReturn(PaymentResponse.successResponse("updatedTime", SUCCESS_CODE, SUCCESS_STATUS));
		when(paymentService.setReviewPaid(IRP_TEST_NOTICE_NUMBER_ERROR, GOOD_TRANSACTION_REQUEST))
				.thenReturn(PaymentResponse.errorResponse(ERROR_STATUS));
		when(paymentService.getReviewPaymentStatus(IRP_TEST_NOTICE_NUMBER_SUCCESS)).thenReturn(PaymentResponse
				.successStatusResponse(new DigitalFormPaymentStatusResponse(), SUCCESS_CODE, SUCCESS_STATUS));
		when(paymentService.getReviewPaymentStatus(IRP_TEST_NOTICE_NUMBER_ERROR))
				.thenReturn(PaymentResponse.errorResponse(ERROR_STATUS));
	}

	@DisplayName("setReviewPaid - Patch HTTP status code - good")
	@Test
	void setReviewPaidReturnsSuccess() throws DigitalFormsException {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setReviewPaid(IRP_TEST_NOTICE_NUMBER_SUCCESS,
				CORRELATION_ID, GOOD_TRANSACTION_REQUEST);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertTrue(resp.getBody().getData().booleanValue());
	}

	@DisplayName("setReviewPaid - Patch Review Prohibition not found")
	@Test
	void setReviewPaidNotFound() throws DigitalFormsException {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setReviewPaid(IRP_TEST_NOTICE_NUMBER_ERROR,
				CORRELATION_ID, GOOD_TRANSACTION_REQUEST);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	@DisplayName("paymentStatusGet - Get success")
	@Test
	void getPaymentStatusSuccess() {
		ResponseEntity<JSONResponse<PaymentTransaction>> resp = controller
				.paymentStatusGet(IRP_TEST_NOTICE_NUMBER_SUCCESS, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("paymentStatusGet - Get error")
	@Test
	void getPaymentStatusError() {
		ResponseEntity<JSONResponse<PaymentTransaction>> resp = controller
				.paymentStatusGet(IRP_TEST_NOTICE_NUMBER_ERROR, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

}
