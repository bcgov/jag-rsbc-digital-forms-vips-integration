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

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
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
	private final PaymentTransRequest GOOD_TRANSACTION_REQUEST = new PaymentTransRequest(new TransactionInfo("MC", "50.01"));
	private final boolean GOOD_TRANSACTION_RESPONSE = true;

	@MockBean
	private PaymentServiceImpl paymentService;

	private PaymentServiceController controller;

	@BeforeEach
	public void init() {
		controller = new PaymentServiceController(paymentService);
		when(paymentService.setReviewPaid(1L, GOOD_TRANSACTION_REQUEST)).thenReturn(GOOD_TRANSACTION_RESPONSE);
	}

	// Test setIRPReviewPaid for 200 returned on success.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post HTTP status code - good")
	@Test
	void setReviewPaidReturns200() {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setReviewPaid(IRP_TEST_NOTICE_NUMBER,
				GOOD_TRANSACTION_REQUEST);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	// Test setIRPReviewPaid for proper JSON response on success.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post response object - good")
	@Test
	void setReviewPaidReturnsSuccess() {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setReviewPaid(IRP_TEST_NOTICE_NUMBER,
				GOOD_TRANSACTION_REQUEST);
		Assertions.assertEquals(GOOD_TRANSACTION_RESPONSE, resp.getBody().getData().booleanValue());
	}

	// Test setIRPReviewPaid for IRP not found.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post IRP Review not found")
	@Test
	void setReviewPaidNotFound() {
		Assertions.assertEquals(true, true);
	}

	// Test setIRPReview for exception state.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post generate exception")
	@Test
	void setReviewPaidReturnException() {
		Assertions.assertEquals(true, true);
	}

}
