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

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormDataPatch;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormDataPost;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationIdResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application.ApplicationResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ApplicationFormService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

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

	private ApplicationFormDataPost formRequestPost;
	
	private ApplicationFormDataPatch formRequest;
	

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		//POST Request

		formRequestPost = new ApplicationFormDataPost();

		formRequestPost.setFirstGivenNm("firstGivenNm");
		formRequestPost.setEmail("email");
		formRequestPost.setFaxNo("faxNo");
		formRequestPost.setManualEntryYN("Y");
		formRequestPost.setNoticeSubjectCd("PERS");
		formRequestPost.setPhoneNo("phoneNo");
		formRequestPost.setPresentationTypeCd("ORAL");
		formRequestPost.setReviewRoleTypeCd("APPNT");
		formRequestPost.setSecondGivenNm("secondGivenNm");
		formRequestPost.setSurnameNm("surnameNm");

		formRequestPost.setFormData("formData");
		
		//PATCH request
		
		formRequest = new ApplicationFormDataPatch();

		formRequest.setFirstGivenNm("firstGivenNm");
		formRequest.setEmail("email");
		formRequest.setFaxNo("faxNo");
		formRequest.setManualEntryYN("Y");
		formRequest.setNoticeSubjectCd("PERS");
		formRequest.setPhoneNo("phoneNo");
		formRequest.setPresentationTypeCd("ORAL");
		formRequest.setReviewRoleTypeCd("APPNT");
		formRequest.setSecondGivenNm("secondGivenNm");
		formRequest.setSurnameNm("surnameNm");

		formRequest.setFormData("formData");
	}

	@DisplayName("Get success - ApplicationFormController")
	@Test
	void getFormSuccess() throws DigitalFormsException {
		when(service.getApplicationForm(any(), any()))
				.thenReturn(ApplicationResponse.successResponseGet(new DigitalFormGetResponse(), "0", null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>>> resp = controller
				.applicationFormGet("guid", "correlationId");
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Post success - ApplicationFormController")
	@Test
	void postFormSuccess() throws DigitalFormsException {
		when(service.postApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePost("guid", "0", null, null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller
				.applicationFormPost("IRP", "noticeNo", "correlationId", new ApplicationInfoWrapper<>(formRequestPost));
		Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@DisplayName("Patch success - ApplicationFormController")
	@Test
	void patchFormSuccess() throws DigitalFormsException {
		when(service.patchApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePatch("guid", "0", null, null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller
				.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formRequest));
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Get error - ApplicationFormController")
	@Test
	void getFormError() throws DigitalFormsException {
		when(service.getApplicationForm(any(), any())).thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationInfoResponse>>> resp = controller
				.applicationFormGet("guid", "correlationId");
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	@DisplayName("Post form type error - ApplicationFormController")
	@Test
	void postFormTypeError() throws DigitalFormsException {
		when(service.postApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePost("guid", "1", null, null));
		Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("invalid", "noticeNo", "correlationId",
					new ApplicationInfoWrapper<>(new ApplicationFormDataPost()));
		});
	}

	@DisplayName("Post null validation error - ApplicationFormController")
	@Test
	void postNullValidationError() throws DigitalFormsException {
		DigitalFormsException e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("IRP", "noticeNo", "correlationId",
					new ApplicationInfoWrapper<>(new ApplicationFormDataPost()));
		});

		Assertions.assertEquals(DigitalFormsConstants.MISSING_REQUEST_BODY_ERROR, e.getMessage());
	}

	@DisplayName("Post field validation error - ApplicationFormController")
	@Test
	void postFieldValidationError() throws DigitalFormsException {

		ApplicationFormDataPost formData = new ApplicationFormDataPost();

		formData.setFirstGivenNm("firstGivenNm");
		formData.setEmail("email");
		formData.setFaxNo("faxNo");
		formData.setManualEntryYN("manualEntryYN");
		formData.setNoticeSubjectCd("PERS");
		formData.setPhoneNo("phoneNo");
		formData.setPresentationTypeCd("ORAL");
		formData.setReviewRoleTypeCd("APPNT");
		formData.setSecondGivenNm("secondGivenNm");
		formData.setSurnameNm("surnameNm");
		formData.setFormData("formData");

		DigitalFormsException e;
		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("IRP", "noticeNo", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(
				String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR, DigitalFormsConstants.MANUAL_ENTRY_TEXT),
				e.getMessage());

		formData.setReviewRoleTypeCd("reviewRoleTypeCd");

		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("IRP", "noticeNo", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
				DigitalFormsConstants.REVIEW_ROLE_TYPE_TEXT), e.getMessage());

		formData.setPresentationTypeCd("presentationTypeCd");

		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("IRP", "noticeNo", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
				DigitalFormsConstants.PRESENTATION_TYPE_TEXT), e.getMessage());

		formData.setNoticeSubjectCd("noticeSubjectCd");

		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPost("IRP", "noticeNo", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(
				String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR, DigitalFormsConstants.NOTICE_SUBJECT_TEXT),
				e.getMessage());

	}

	@DisplayName("Post error - ApplicationFormController")
	@Test
	void postFormError() throws DigitalFormsException {
		when(service.postApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller
				.applicationFormPost("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formRequestPost));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

	@DisplayName("Patch null validation success - ApplicationFormController")
	@Test
	void patchNullValidationSuccess() throws DigitalFormsException {

		ApplicationFormDataPatch formData = new ApplicationFormDataPatch();

		formData.setFirstGivenNm("firstGivenNm");
		formData.setEmail("email");
		formData.setFaxNo("faxNo");
		formData.setPhoneNo("phoneNo");
		formData.setSecondGivenNm("secondGivenNm");
		formData.setSurnameNm("surnameNm");
		formData.setFormData("formData");

		when(service.patchApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.successResponsePatch("guid", "0", null, null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller
				.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formData));
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("Patch field validation error - ApplicationFormController")
	@Test
	void patchFieldValidationError() throws DigitalFormsException {

		ApplicationFormDataPatch formData = new ApplicationFormDataPatch();

		formData.setFirstGivenNm("firstGivenNm");
		formData.setEmail("email");
		formData.setFaxNo("faxNo");
		formData.setPhoneNo("phoneNo");
		formData.setSecondGivenNm("secondGivenNm");
		formData.setSurnameNm("surnameNm");
		formData.setFormData("formData");

		formData.setManualEntryYN("manualEntryYN");
		DigitalFormsException e;
		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(
				String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR, DigitalFormsConstants.MANUAL_ENTRY_TEXT),
				e.getMessage());

		formData.setReviewRoleTypeCd("reviewRoleTypeCd");

		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
				DigitalFormsConstants.REVIEW_ROLE_TYPE_TEXT), e.getMessage());

		formData.setPresentationTypeCd("presentationTypeCd");

		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
				DigitalFormsConstants.PRESENTATION_TYPE_TEXT), e.getMessage());

		formData.setNoticeSubjectCd("noticeSubjectCd");

		e = Assertions.assertThrows(DigitalFormsException.class, () -> {
			controller.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formData));
		});

		Assertions.assertEquals(
				String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR, DigitalFormsConstants.NOTICE_SUBJECT_TEXT),
				e.getMessage());

	}

	@DisplayName("Patch error - ApplicationFormController")
	@Test
	void patchFormError() throws DigitalFormsException {
		when(service.patchApplicationForm(any(), any(), any(), any()))
				.thenReturn(ApplicationResponse.errorResponse(null));
		ResponseEntity<JSONResponse<ApplicationInfoWrapper<ApplicationIdResponse>>> resp = controller
				.applicationFormPatch("IRP", "guid", "correlationId", new ApplicationInfoWrapper<>(formRequest));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

}
