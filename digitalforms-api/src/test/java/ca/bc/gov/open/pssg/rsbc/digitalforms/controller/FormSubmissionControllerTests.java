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
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewFormRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.FormSubmissionService;

/**
 * 
 * Form Submission Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class FormSubmissionControllerTests {

	private final String JSON_RESPONSE_GOOD = "IRP review form";

	@Mock
	private FormSubmissionService service;

	@InjectMocks
	private FormSubmissionController controller = new FormSubmissionController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - FormSubmissionController")
	@Test
	void irpGetFormSuccess() {
		when(service.getReviewForm(any())).thenReturn(JSON_RESPONSE_GOOD);
		ResponseEntity<JSONResponse<String>> resp = controller.reviewFormGet(1L);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Post success - FormSubmissionController")
	@Test
	void irpPostFormSuccess() {
		when(service.postReviewForm(any(), any())).thenReturn(JSON_RESPONSE_GOOD);
		ResponseEntity<JSONResponse<String>> resp = controller.reviewFormPost(1L, new ReviewFormRequest());
		Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@DisplayName("Patch success - FormSubmissionController")
	@Test
	void irpPatchFormSuccess() {
		when(service.patchReviewForm(any(), any(), any())).thenReturn(JSON_RESPONSE_GOOD);
		ResponseEntity<JSONResponse<String>> resp = controller.reviewFormPatch(1L, 1L, new ReviewFormRequest());
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

}
