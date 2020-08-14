package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.jagvipsclient.disclosure.DisclosureResponse;
import ca.bc.gov.open.jagvipsclient.disclosure.DocumentInfo;
import ca.bc.gov.open.jagvipsclient.prohibition.DocumentDisclosureInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DisclosureWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.DisclosureServiceImpl;

/**
 * 
 * Disclosure Controller Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class DisclosureControllerTests {

	private final String IRP_TEST_NOTICE_NUMBER_SUCCESS = "1";
	private final String IRP_TEST_NOTICE_NUMBER_ERROR = "2";
	private final String DOCUMENT_ID_SUCCESS = "1";
	private final String DOCUMENT_ID_ERROR = "2";
	private final DocumentDisclosureInfo DISCLOSURE = new DocumentDisclosureInfo("123", "2018-06-29 00:00:00 -07:00");
	private final String CORRELATION_ID = "correlationId";
	private final String SUCCESS_STATUS = "success";
	private final String SUCCESS_CODE = "0";
	private final String ERROR_STATUS = "error";

	@MockBean
	private DisclosureServiceImpl disclosureService;

	@InjectMocks
	private DisclosureController controller = new DisclosureController();

	@BeforeEach
	public void init() throws DigitalFormsException {
		MockitoAnnotations.initMocks(this);

		when(disclosureService.getDisclosureDocument(DOCUMENT_ID_SUCCESS, CORRELATION_ID)).thenReturn(DisclosureResponse
				.successDocumentResponse(new DocumentInfo("mimeType", "document"), SUCCESS_CODE, SUCCESS_STATUS));
		when(disclosureService.getDisclosureDocument(DOCUMENT_ID_ERROR, CORRELATION_ID))
				.thenReturn(DisclosureResponse.errorResponse(ERROR_STATUS));

		when(disclosureService.setDisclosureSent(IRP_TEST_NOTICE_NUMBER_SUCCESS, CORRELATION_ID, DISCLOSURE))
				.thenReturn(DisclosureResponse.successResponse("updatedTime", SUCCESS_CODE, SUCCESS_STATUS));
		when(disclosureService.setDisclosureSent(IRP_TEST_NOTICE_NUMBER_ERROR, CORRELATION_ID, DISCLOSURE))
				.thenReturn(DisclosureResponse.errorResponse(ERROR_STATUS));
	}

	@DisplayName("getDisclosureDocument - Get success")
	@Test
	void getDisclosureDocumentSuccess() {
		ResponseEntity<JSONResponse<DocumentWrapper>> resp = controller.getDisclosureDocument(DOCUMENT_ID_SUCCESS,
				CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("getDisclosureDocument - Get error")
	@Test
	void getDisclosureDocumentError() {
		ResponseEntity<JSONResponse<DocumentWrapper>> resp = controller.getDisclosureDocument(DOCUMENT_ID_ERROR,
				CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	@DisplayName("setDisclosureSent - Patch success")
	@Test
	void setDisclosureSentSuccess() throws DigitalFormsException {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setDisclosureSent(IRP_TEST_NOTICE_NUMBER_SUCCESS,
				CORRELATION_ID, new DisclosureWrapper(DISCLOSURE));
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertTrue(resp.getBody().getData().booleanValue());
	}

	@DisplayName("setDisclosureSent - Patch error")
	@Test
	void setDisclosureSentError() throws DigitalFormsException {
		ResponseEntity<JSONResponse<Boolean>> resp = controller.setDisclosureSent(IRP_TEST_NOTICE_NUMBER_ERROR,
				CORRELATION_ID, new DisclosureWrapper(DISCLOSURE));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
	}

}
