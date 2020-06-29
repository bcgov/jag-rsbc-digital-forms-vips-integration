package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPReviewFormRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPReviewService;

import static org.mockito.ArgumentMatchers.any;

/**
 * 
 * IRP Review Controller Tests.
 * 
 * @author sivakaruna
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class IRPReviewControllerTests {

	private final String JSON_RESPONSE_GOOD = "IRP review form";

	@Mock
	private IRPReviewService irpService;

	@InjectMocks
	private IRPReviewController controller = new IRPReviewController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - IRPReviewController")
	@Test
	void irpGetFormSuccess() {
		when(irpService.getIRPReviewForm(any())).thenReturn(JSON_RESPONSE_GOOD);
		ResponseEntity<JSONResponse<String>> resp = controller.irpReviewFormGet(1L);
		Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Post success - IRPReviewController")
	@Test
	void irpPostFormSuccess() {
		when(irpService.postIRPReviewForm(any(), any())).thenReturn(JSON_RESPONSE_GOOD);
		ResponseEntity<JSONResponse<String>> resp = controller.irpReviewFormPost(1L, new IRPReviewFormRequest());
		Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@DisplayName("Patch success - IRPReviewController")
	@Test
	void irpPatchFormSuccess() {
		when(irpService.patchIRPReviewForm(any(), any(), any())).thenReturn(JSON_RESPONSE_GOOD);
		ResponseEntity<JSONResponse<String>> resp = controller.irpReviewFormPatch(1L, 1L, new IRPReviewFormRequest());
		Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

}
