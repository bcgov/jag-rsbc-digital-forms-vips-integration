package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.PaymentService;

/**
 * 
 * Payment Status Controller Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class PaymentStatusControllerTests {

	@Mock
	private PaymentService paymentService;

	@InjectMocks
	private PaymentStatusController controller = new PaymentStatusController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - paymentStatusController")
	@Test
	void irpGetFormSuccess() {
		when(paymentService.getReviewPaymentStatus(any())).thenReturn(new PaymentStatusResponse("1"));
		ResponseEntity<JSONResponse<PaymentStatusResponse>> resp = controller.paymentStatusGet(1L);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
}
