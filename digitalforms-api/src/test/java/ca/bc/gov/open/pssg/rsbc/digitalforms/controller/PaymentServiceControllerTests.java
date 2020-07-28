package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.ArgumentMatchers.any;
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

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.TransactionInfo;
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

	private final Long IRP_TEST_NOTICE_NUMBER = 1L;
	private final PaymentTransRequest GOOD_TRANSACTION_REQUEST = new PaymentTransRequest(new TransactionInfo("MC", "50.01", "12345", "2018-06-29 00:00:00 -07:00"));
	private final boolean GOOD_TRANSACTION_RESPONSE = true;
	private final String CORRELATION_ID = "correlationId";

	@MockBean
	private PaymentServiceImpl paymentService;

	private PaymentServiceController controller;

	@BeforeEach
	public void init() {
		controller = new PaymentServiceController(paymentService);
		when(paymentService.setReviewPaid(1L, GOOD_TRANSACTION_REQUEST)).thenReturn(GOOD_TRANSACTION_RESPONSE);
	}

	// Test setReviewPaid for 200 returned on success.
	// TODO - update when fully functional
	@DisplayName("setReviewPaid - Post HTTP status code - good")
	@Test
	void setReviewPaidReturns200() {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setReviewPaid(IRP_TEST_NOTICE_NUMBER, CORRELATION_ID,
				GOOD_TRANSACTION_REQUEST);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	// Test setReviewPaid for proper JSON response on success.
	// TODO - update when fully functional
	@DisplayName("setReviewPaid - Post response object - good")
	@Test
	void setReviewPaidReturnsSuccess() {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setReviewPaid(IRP_TEST_NOTICE_NUMBER, CORRELATION_ID,
				GOOD_TRANSACTION_REQUEST);
		Assertions.assertEquals(GOOD_TRANSACTION_RESPONSE, resp.getBody().getData().booleanValue());
	}

	// Test setReviewPaid for prohibition not found.
	// TODO - update when fully functional
	@DisplayName("setReviewPaid - Post Review Prohibition not found")
	@Test
	void setReviewPaidNotFound() {
		Assertions.assertTrue(true);
	}

	// Test setReviewPaid for exception state.
	// TODO - update when fully functional
	@DisplayName("setReviewPaid - Post generate exception")
	@Test
	void setReviewPaidReturnException() {
		Assertions.assertTrue(true);
	}
	
	@DisplayName("paymentStatusGet - Get success")
	@Test
	void getPaymentStatusSuccess() {
		when(paymentService.getReviewPaymentStatus(any())).thenReturn(new PaymentStatusResponse("1"));
		ResponseEntity<JSONResponse<PaymentStatusResponse>> resp = controller.paymentStatusGet(1L, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

}
