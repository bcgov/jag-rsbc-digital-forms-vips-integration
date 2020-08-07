package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionStatus;
import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.QueryServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;


/**
 * 
 * Query Service Controller Tests. 
 * 
 * @author shaunmillargov
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class QueryServiceControllerTests {

	private final String NOTICE_NUMBER = "1";
	private final String NOTICE_NUMBER_NOT_FOUND = "2";
	private final String ORIGINAL_CAUSE = "IRP3";
	private final String CORRELATION_ID = "correlationId";

	@MockBean
	private QueryServiceImpl service;

	private QueryServiceController controller;

	@BeforeEach
	public void init() throws Exception {
		
		controller = new QueryServiceController(service);
		
		ProhibitionStatus status = new ProhibitionStatus();
		status.setEffectiveDt("2018-06-20 00:00:00 -07:00");
		status.setNoticeTypeCd("IRP");
		status.setOriginalCause("IRP3");
		status.setReceiptNumberTxt("12345");
		status.setReviewCreatedYn("Y");
		status.setReviewEndDtm("2018-06-29 00:00:00 -07:00");
		status.setReviewStartDtm("2018-06-25 00:00:00 -07:00");
		status.setReviewFormSubmittedYn("Y");
		status.setSurnameNm("Smith"); 

		// good
		when(service.getProhibitionStatus(NOTICE_NUMBER, CORRELATION_ID)).thenReturn(new VipsProhibitionStatusResponse(
				status, DigitalFormsConstants.ORDS_SUCCESS_CD, DigitalFormsConstants.JSON_RESPONSE_SUCCESS));

		// not found
		when(service.getProhibitionStatus(NOTICE_NUMBER_NOT_FOUND, CORRELATION_ID))
				.thenReturn(new VipsProhibitionStatusResponse(status, DigitalFormsConstants.ORDS_FAILURE_CD,
						DigitalFormsConstants.JSON_RESPONSE_FAIL));
	}

	@DisplayName("Get success status code - QueryServiceController")
	@Test
	void getReturns200() throws Exception {
		ResponseEntity<JSONResponse<ProhibitionStatusResponse>> resp = controller.getProhibitionInfo(NOTICE_NUMBER, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());		
	}

	@DisplayName("Get response value success - QueryServiceController")
	@Test
	void getReturnsSuccess() throws Exception {
		ResponseEntity<JSONResponse<ProhibitionStatusResponse>> resp = controller.getProhibitionInfo(NOTICE_NUMBER, CORRELATION_ID);
		Assertions.assertEquals(ORIGINAL_CAUSE, resp.getBody().getData().getStatus().getOriginalCause());
	}

	@DisplayName("Get fail status code - QueryServiceController")
	@Test
	void getReturns404() throws Exception {
		ResponseEntity<JSONResponse<ProhibitionStatusResponse>> resp = controller.getProhibitionInfo(NOTICE_NUMBER_NOT_FOUND, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
		
		// check that the error object too has the proper status code. 
		Assertions.assertEquals(404, resp.getBody().getError().getHttpStatus());
	}

}
