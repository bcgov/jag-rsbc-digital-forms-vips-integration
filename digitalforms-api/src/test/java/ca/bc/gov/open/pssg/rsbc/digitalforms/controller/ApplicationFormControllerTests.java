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
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;

/**
 * 
 * Form Submission Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ApplicationFormControllerTests {

	private final String JSON_RESPONSE_GOOD = "IRP review form";

	@Mock
	private ApplicationFormService service;

	@InjectMocks
	private ApplicationFormController controller = new ApplicationFormController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - FormSubmissionController")
	@Test
	void irpGetFormSuccess() {
		when(service.getApplicationForm(any(), any())).thenReturn(null);
		ResponseEntity<JSONResponse<ApplicationResponse>> resp = controller.reviewFormGet("abc", "abc");
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Post success - FormSubmissionController")
	@Test
	void irpPostFormSuccess() {
		when(service.postApplicationForm(any(), any())).thenReturn(null);
		ResponseEntity<JSONResponse<ApplicationResponse>> resp = controller.reviewFormPost("abc",
				new ApplicationFormData());
		Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@DisplayName("Patch success - FormSubmissionController")
	@Test
	void irpPatchFormSuccess() {
		when(service.patchApplicationForm(any(), any(), any())).thenReturn(null);
		ResponseEntity<JSONResponse<ApplicationResponse>> resp = controller.reviewFormPatch("abc", "abc",
				new ApplicationFormData());
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

}
