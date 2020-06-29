package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPPaymentTransRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.TransactionInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPPaymentServiceImpl;

/**
 * 
 * IRP Payment Service Controller Tests.
 * 
 * @author shaunmillargov
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class IRPPaymentServiceControllerTests {

	private final Long IRP_TEST_NOTICE_NUMBER = 1L;
	private final IRPPaymentTransRequest GOOD_TRANSACTION_REQUEST = new IRPPaymentTransRequest(new TransactionInfo("MC", "50.01"));
	private final boolean GOOD_TRANSACTION_RESPONSE = true;

	@MockBean
	private IRPPaymentServiceImpl paymentService;

	private IRPPaymentServiceController controller;

	@BeforeEach
	public void init() {
		controller = new IRPPaymentServiceController(paymentService);
		when(paymentService.setIRPReviewPaid(1L, GOOD_TRANSACTION_REQUEST)).thenReturn(GOOD_TRANSACTION_RESPONSE);
	}

	// Test setIRPReviewPaid for 200 returned on success.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post HTTP status code - good")
	@Test
	void setIRPReviewPaidReturns200() {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setIRPReviewPaid(IRP_TEST_NOTICE_NUMBER,
				GOOD_TRANSACTION_REQUEST);
		Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	// Test setIRPReviewPaid for proper JSON response on success.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post response object - good")
	@Test
	void setIRPReviewPaidReturnsSuccess() {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setIRPReviewPaid(IRP_TEST_NOTICE_NUMBER,
				GOOD_TRANSACTION_REQUEST);
		Assert.assertEquals(GOOD_TRANSACTION_RESPONSE, resp.getBody().getData().booleanValue());
	}

	// Test setIRPReviewPaid for IRP not found.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post IRP Review not found")
	@Test
	void setIRPReviewPaidNotFound() {
		// test for not found
	}

	// Test setIRPReview for exception state.
	// TODO - update when fully functional
	@DisplayName("setIRPReviewPaid - Post generate exception")
	@Test
	void setIRPReviewPaidReturnException() {
		// test for exception
	}

}
