package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransaction;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.TransactionInfo;

/**
 * 
 * Payment Service Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class PaymentServiceImplTests {

	@Mock
	private ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentService service;

	@InjectMocks
	private PaymentService serviceImpl = new PaymentServiceImpl();

	private TransactionInfo transactionInfoSample = new TransactionInfo("VISA", "30.12", "123",
			"2020-03-05 00:00:00 -08:00");

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Patch success - PaymentService")
	@Test
	void setReviewPaidSuccess() throws DigitalFormsException {
		when(service.patchPaymentReceipt(any(), any()))
				.thenReturn(PaymentResponse.successResponse("updatedTime", "0", null));
		PaymentResponse resp = serviceImpl.setReviewPaid("1", new PaymentTransaction(transactionInfoSample));
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Get success - PaymentService")
	@Test
	void getReviewPaymentStatusSuccess() throws DigitalFormsException {
		when(service.getPaymentStatus(any())).thenReturn(PaymentResponse.successStatusResponse(null, "0", "success"));
		PaymentResponse resp = serviceImpl.getReviewPaymentStatus("1");
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Patch error - PaymentService")
	@Test
	void setReviewPaidError() throws DigitalFormsException {
		when(service.patchPaymentReceipt(any(), any())).thenReturn(PaymentResponse.errorResponse("Get error"));
		PaymentResponse resp = serviceImpl.setReviewPaid("1", new PaymentTransaction(transactionInfoSample));
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Get error", resp.getRespMsg());

	}

	@DisplayName("Get error - PaymentService")
	@Test
	void getReviewPaymentStatusError() throws DigitalFormsException {
		when(service.getPaymentStatus(any())).thenReturn(PaymentResponse.errorResponse("Post error"));
		PaymentResponse resp = serviceImpl.getReviewPaymentStatus("1");
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Post error", resp.getRespMsg());
	}
}
