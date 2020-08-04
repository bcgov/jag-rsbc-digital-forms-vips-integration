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
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationIdResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoWrapper;
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

	@DisplayName("Get success - ApplicationFormController")
	@Test
	void getFormSuccess() throws DigitalFormsException {
		when(service.getApplicationForm(any()))
				.thenReturn(ApplicationResponse.successResponseGet(new DigitalFormGetResponse(), "0", null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>>> resp = controller.applicationFormGet("guid", "correlationId");
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Post success - ApplicationFormController")
	@Test
	void postFormSuccess() throws DigitalFormsException {
		when(service.postApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePost("guid", "0", null, null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller.applicationFormPost("IRP", "noticeNo", "correlationId",
				new ApplicationInfoWrapper<>(new ApplicationFormData()));
		Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@DisplayName("Patch success - ApplicationFormController")
	@Test
	void patchFormSuccess() throws DigitalFormsException {
		when(service.patchApplicationForm(any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePatch("guid", "0", null, null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller.applicationFormPatch("IRP", "guid", "correlationId",
				new ApplicationInfoWrapper<>(new ApplicationFormData()));
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Get error - ApplicationFormController")
	@Test
	void getFormError() throws DigitalFormsException {
		when(service.getApplicationForm(any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>>> resp = controller.applicationFormGet("guid", "correlationId");
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	@DisplayName("Post form type error - ApplicationFormController")
	@Test
	void postFormTypeError() throws DigitalFormsException {
		when(service.postApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePost("guid", "1", null, null));
		Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("invalid", "noticeNo", "correlationId", new ApplicationInfoWrapper<>(new ApplicationFormData()));
		});
	}

	@DisplayName("Post error - ApplicationFormController")
	@Test
	void postFormError() throws DigitalFormsException {
		when(service.postApplicationForm(any(), any(), any(), any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller.applicationFormPost("IRP", "guid", "correlationId",
				new ApplicationInfoWrapper<>(new ApplicationFormData()));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@DisplayName("Patch error - ApplicationFormController")
	@Test
	void patchFormError() throws DigitalFormsException {
		when(service.patchApplicationForm(any(), any(), any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller.applicationFormPatch("IRP", "guid", "correlationId",
				new ApplicationInfoWrapper<>(new ApplicationFormData()));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

}
