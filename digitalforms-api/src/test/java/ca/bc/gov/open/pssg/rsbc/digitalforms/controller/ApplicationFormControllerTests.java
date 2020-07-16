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
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationIdResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;

/**
 * 
 * Application Form Controller Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ApplicationFormControllerTests {

	@Mock
	private ApplicationFormService service;

	@InjectMocks
	private ApplicationFormController controller = new ApplicationFormController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	// TODO Need to update all unit tests
	
	@DisplayName("Get success - ApplicationFormController")
	@Test
	void getFormSuccess() {
		when(service.getApplicationForm(any(), any()))
				.thenReturn(ApplicationResponse.successResponseGet(null, "1", null));
		ResponseEntity<JSONResponse<ApplicationInfoResponse>> resp = controller.applicationFormGet("abc", "abc");
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	// Need to update
	@DisplayName("Post success - ApplicationFormController")
	@Test
	void postFormSuccess() {
		when(service.postApplicationForm(any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponseGet(null, "1", null));
		ResponseEntity<JSONResponse<ApplicationIdResponse>> resp = controller.applicationFormPost("abc", "abc",
				new ApplicationFormData());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	// Need to update
	@DisplayName("Patch success - ApplicationFormController")
	@Test
	void patchFormSuccess() {
		when(service.patchApplicationForm(any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponseGet(null, "1", null));
		ResponseEntity<JSONResponse<ApplicationIdResponse>> resp = controller.applicationFormPatch("abc", "abc",
				new ApplicationFormData());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	// Need to update
	@DisplayName("Get error - ApplicationFormController")
	@Test
	void getFormError() {
		when(service.getApplicationForm(any(), any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoResponse>> resp = controller.applicationFormGet("abc", "abc");
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	// Need to update
	@DisplayName("Post error - ApplicationFormController")
	@Test
	void postFormError() {
		when(service.postApplicationForm(any(), any(), any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationIdResponse>> resp = controller.applicationFormPost("abc", "abc",
				new ApplicationFormData());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	// Need to update
	@DisplayName("Patch error - ApplicationFormController")
	@Test
	void patchFormError() {
		when(service.patchApplicationForm(any(), any(), any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationIdResponse>> resp = controller.applicationFormPatch("abc", "abc",
				new ApplicationFormData());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

}
