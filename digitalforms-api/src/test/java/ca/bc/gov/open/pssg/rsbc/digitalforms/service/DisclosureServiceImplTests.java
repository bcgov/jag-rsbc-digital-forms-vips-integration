package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import ca.bc.gov.open.jagvipsclient.disclosure.DisclosureResponse;
import ca.bc.gov.open.jagvipsclient.disclosure.DisclosureService;
import ca.bc.gov.open.jagvipsclient.disclosure.DocumentInfo;
import ca.bc.gov.open.jagvipsclient.prohibition.DocumentDisclosureInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;

/**
 * 
 * Disclosure Service Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class DisclosureServiceImplTests {

	private final String DOCUMENT_ID_SUCCESS = "1";
	private final String DOCUMENT_ID_ERROR = "2";
	private final DocumentDisclosureInfo DISCLOSURE = new DocumentDisclosureInfo("123", "2018-06-29 00:00:00 -07:00");
	private final String CORRELATION_ID = "correlationId";
	private final String SUCCESS_STATUS = "success";
	private final String SUCCESS_CODE = "0";
	private final String ERROR_STATUS = "error";

	@Mock
	private DisclosureService disclosureService;

	@Mock
	private ConfigProperties properties;

	@InjectMocks
	private ca.bc.gov.open.pssg.rsbc.digitalforms.service.DisclosureService serviceImpl = new DisclosureServiceImpl();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(disclosureService.setDisclosureSent(any(), any(), any(), any(), eq("123")))
				.thenReturn(DisclosureResponse.successResponse("updatedTime", SUCCESS_CODE, SUCCESS_STATUS));
		when(disclosureService.setDisclosureSent(any(), any(), any(), any(), eq("234")))
				.thenReturn(DisclosureResponse.errorResponse(ERROR_STATUS));
		when(disclosureService.getDisclosureDocument(eq(DOCUMENT_ID_SUCCESS), any(), eq(CORRELATION_ID)))
				.thenReturn(DisclosureResponse.successDocumentResponse(new DocumentInfo("mimeType", "document"),
						SUCCESS_CODE, SUCCESS_STATUS));
		when(disclosureService.getDisclosureDocument(eq(DOCUMENT_ID_ERROR), any(), eq(CORRELATION_ID)))
				.thenReturn(DisclosureResponse.errorResponse(ERROR_STATUS));

		when(properties.getOrdsUserGuid()).thenReturn("user");

	}

	@DisplayName("Get success - DisclosureService")
	@Test
	void getDisclosureDocumentSuccess() throws DigitalFormsException {
		DisclosureResponse resp = serviceImpl.getDisclosureDocument(DOCUMENT_ID_SUCCESS, CORRELATION_ID);
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Get error - DisclosureService")
	@Test
	void getDisclosureDocumentError() throws DigitalFormsException {
		DisclosureResponse resp = serviceImpl.getDisclosureDocument(DOCUMENT_ID_ERROR, CORRELATION_ID);
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals(ERROR_STATUS, resp.getRespMsg());

	}

	@DisplayName("Patch success - DisclosureService")
	@Test
	void setDisclosureSentSuccess() throws DigitalFormsException {
		DisclosureResponse resp = serviceImpl.setDisclosureSent("123", DISCLOSURE);
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Patch error - DisclosureService")
	@Test
	void setDisclosureSentError() throws DigitalFormsException {
		DisclosureResponse resp = serviceImpl.setDisclosureSent("234", DISCLOSURE);
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals(ERROR_STATUS, resp.getRespMsg());
	}

}
