package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

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

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPPaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPPaymentService;

import static org.mockito.ArgumentMatchers.any;

/**
 * 
 * IRP Payment Status Controller Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class IRPPaymentStatusControllerTests {

	@Mock
	private IRPPaymentService irpPaymentService;

	@InjectMocks
	private IRPPaymentStatusController controller = new IRPPaymentStatusController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - IRPReviewController")
	@Test
	void irpGetFormSuccess() {
		when(irpPaymentService.getIRPReviewPaymentStatus(any())).thenReturn(new IRPPaymentStatusResponse("1"));
		ResponseEntity<JSONResponse<IRPPaymentStatusResponse>> resp = controller.irpPaymentStatusGet(1L);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
}
