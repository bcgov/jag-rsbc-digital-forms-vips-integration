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

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;

/**
 * 
 * Application Form Service Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ApplicationFormServiceImplTests {

	@Mock
	private ApplicationService service;

	@InjectMocks
	private ApplicationFormService serviceImpl = new ApplicationFormServiceImpl();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Get success - ApplicationFormController")
	@Test
	void getFormSuccess() throws DigitalFormsException {
		when(service.getApplication(any()))
				.thenReturn(ApplicationResponse.successResponseGet(new DigitalFormGetResponse(), "1", null));
		ApplicationResponse resp = serviceImpl.getApplicationForm("IRP", "guid");
		Assertions.assertEquals(1, resp.getRespCode());
	}

	@DisplayName("Post success - ApplicationFormController")
	@Test
	void postFormSuccess() throws DigitalFormsException {
		when(service.postApplication(any()))
				.thenReturn(ApplicationResponse.successResponsePost("guid", "1", null, null));
		ApplicationResponse resp = serviceImpl.postApplicationForm("IRP", "noticeNo", new ApplicationFormData());
		Assertions.assertEquals(1, resp.getRespCode());
	}

	@DisplayName("Patch success - ApplicationFormController")
	@Test
	void patchFormSuccess() throws DigitalFormsException {
		when(service.patchApplication(any(), any()))
				.thenReturn(ApplicationResponse.successResponsePatch("guid", "1", null, null));
		ApplicationResponse resp = serviceImpl.patchApplicationForm("IRP", "guid", new ApplicationFormData());
		Assertions.assertEquals(1, resp.getRespCode());
	}

	@DisplayName("Get error - ApplicationFormController")
	@Test
	void getFormError() throws DigitalFormsException {
		when(service.getApplication(any())).thenReturn(ApplicationResponse.errorResponse("Get error"));
		ApplicationResponse resp = serviceImpl.getApplicationForm("IRP", "guid");
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Get error", resp.getRespMsg());

	}

	@DisplayName("Post error - ApplicationFormController")
	@Test
	void postFormError() throws DigitalFormsException {
		when(service.postApplication(any())).thenReturn(ApplicationResponse.errorResponse("Post error"));
		ApplicationResponse resp = serviceImpl.postApplicationForm("IRP", "noticeNo", new ApplicationFormData());
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Post error", resp.getRespMsg());
	}

	@DisplayName("Patch error - ApplicationFormController")
	@Test
	void patchFormError() throws DigitalFormsException {
		when(service.patchApplication(any(), any())).thenReturn(ApplicationResponse.errorResponse("Patch error"));
		ApplicationResponse resp = serviceImpl.patchApplicationForm("IRP", "guid", new ApplicationFormData());
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Patch error", resp.getRespMsg());
	}

}
