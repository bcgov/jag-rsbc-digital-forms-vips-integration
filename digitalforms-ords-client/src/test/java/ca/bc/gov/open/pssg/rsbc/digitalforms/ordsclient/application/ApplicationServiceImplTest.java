package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import static org.mockito.ArgumentMatchers.any;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.ApplicationApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormCreateResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPatchResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPostRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Application service tests
 *
 * @author sivakaruna
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	
	@Mock
	private ApplicationApi applicationApiMock;

	@InjectMocks
	private ApplicationServiceImpl service;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);
		service = new ApplicationServiceImpl(applicationApiMock);
	}

	@DisplayName("Get success - ApplicationServiceImpl")
	@Test
	public void getApplicationSuccess() throws ApiException {

		DigitalFormGetResponse response = new DigitalFormGetResponse();
		response.setNoticeSubjectCd("IRP");
		response.setFirstGivenNm("John");
		response.setSecondGivenNm("Doe");
		response.setStatusCode("1");

		Mockito.when(applicationApiMock.digitalFormGuidGet("guid")).thenReturn(response);

		ApplicationResponse resp = service.getApplication("guid");

		Assertions.assertEquals("IRP", resp.getApplicationInfo().getNoticeSubjectCd());
		Assertions.assertEquals("John", resp.getApplicationInfo().getFirstGivenNm());
		Assertions.assertEquals("Doe", resp.getApplicationInfo().getSecondGivenNm());
		Assertions.assertEquals(1, resp.getRespCode());
	}

	@DisplayName("Post success - ApplicationServiceImpl")
	@Test
	public void postApplicationSuccess() throws ApiException {

		DigitalFormCreateResponse response = new DigitalFormCreateResponse();
		response.setFormObjectGuid("guid");
		response.setStatusCode("1");
		response.setEntDtm("123");

		Mockito.when(applicationApiMock.digitalFormPost(any(DigitalFormPostRequest.class))).thenReturn(response);

		ApplicationResponse resp = service.postApplication(new DigitalFormPostRequest());

		Assertions.assertEquals("guid", resp.getApplicationId());
		Assertions.assertEquals("123", resp.getCreatedTime());
		Assertions.assertEquals(1, resp.getRespCode());
	}

	@DisplayName("Patch success - ApplicationServiceImpl")
	@Test
	public void patchApplicationSuccess() throws ApiException {

		DigitalFormPatchResponse response = new DigitalFormPatchResponse();
		response.setFormObjectGuid("guid");
		response.setStatusCode("1");
		response.setUpdDtm("123");

		Mockito.when(applicationApiMock.digitalFormGuidPatch(any(), (any(DigitalFormPatchRequest.class))))
				.thenReturn(response);

		ApplicationResponse resp = service.patchApplication("guid", new DigitalFormPatchRequest());

		Assertions.assertEquals("guid", resp.getApplicationId());
		Assertions.assertEquals("123", resp.getUpdatedTime());
		Assertions.assertEquals(1, resp.getRespCode());
	}

	@DisplayName("Get error - ApplicationServiceImpl")
	@Test
	public void getApplicationError() throws ApiException {

		Mockito.when(applicationApiMock.digitalFormGuidGet("error")).thenThrow(new ApiException(API_EXCEPTION));

		ApplicationResponse resp = service.getApplication("error");

		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals(API_EXCEPTION, resp.getRespMsg());
	}

	@DisplayName("Post error - ApplicationServiceImpl")
	@Test
	public void postApplicationError() throws ApiException {

		Mockito.when(applicationApiMock.digitalFormPost(any(DigitalFormPostRequest.class)))
				.thenThrow(new ApiException(API_EXCEPTION));

		ApplicationResponse resp = service.postApplication(new DigitalFormPostRequest());

		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals(API_EXCEPTION, resp.getRespMsg());
	}

	@DisplayName("Patch error - ApplicationServiceImpl")
	@Test
	public void patchApplicationError() throws ApiException {

		Mockito.when(applicationApiMock.digitalFormGuidPatch(any(), (any(DigitalFormPatchRequest.class))))
				.thenThrow(new ApiException(API_EXCEPTION));

		ApplicationResponse resp = service.patchApplication("guid", new DigitalFormPatchRequest());

		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals(API_EXCEPTION, resp.getRespMsg());
	}
}
