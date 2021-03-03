package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.jagvipsclient.prohibition.DocumentDisclosureInfo;
import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionStatus;
import ca.bc.gov.open.jagvipsclient.prohibition.ReviewInfo;
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
	private final String CORRELATION_ID = "correlationId";
	private final String NOTICE_SERVED_DATE = "2018-06-20 00:00:00 -07:00";
	private final String NOTICE_TYPE_CD = "UL";
	private final String ORIGINAL_CAUSE_CD = "IRP3";
	private final String ANSWER_Y = "Y"; private final String ANSWER_N = "N";
	private final String SURNAME = "Gordon";
	private final String DISCLOSURE_DOC_ID = "456";
	private final String DISCLOSED_DTM = "2019-01-02 17:30:00 -08:00";
	private final String APPLICATION_ID = "123456";
	private final String REVIEW_START_DTM = "2021-01-02 17:30:00 -08:00";
	private final String REVIEW_END_DTM = "2021-01-02 19:30:00 -08:00";
	private final String REVIEW_STATUS = "complete-success";
	private final String RECEIPT_NUM_TXT = "567";
	private final String REVIEW_ID = "9990";
	

	@MockBean
	private QueryServiceImpl service;

	private QueryServiceController controller;

	@BeforeEach
	public void init() throws Exception {
		
		controller = new QueryServiceController(service);
		
		ProhibitionStatus status = new ProhibitionStatus();
		status.setNoticeServedDt(NOTICE_SERVED_DATE);
		status.setNoticeTypeCd(NOTICE_TYPE_CD);
		status.setOriginalCause(ORIGINAL_CAUSE_CD);
		status.setReviewCreatedYn(ANSWER_Y);
		status.setReviewFormSubmittedYn(ANSWER_Y);
		status.setSurnameNm(SURNAME); 
		status.setDriverLicenceSeized(ANSWER_N);
		
		List<DocumentDisclosureInfo> dl = new ArrayList<>();
		DocumentDisclosureInfo disclosure = new DocumentDisclosureInfo(
				DISCLOSURE_DOC_ID,
				DISCLOSED_DTM);
		dl.add(disclosure);
		status.setDisclosure(dl);
		
		List<ReviewInfo> rl = new ArrayList<>();
		ReviewInfo review = new ReviewInfo(
				APPLICATION_ID, 
				REVIEW_STATUS, 
				REVIEW_START_DTM, 
				REVIEW_END_DTM, 
				RECEIPT_NUM_TXT, 
				REVIEW_ID);
		rl.add(review);
		status.setReviews(rl);
		
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

	@DisplayName("Get response values success - QueryServiceController")
	@Test
	void getReturnsSuccess() throws Exception {
		ResponseEntity<JSONResponse<ProhibitionStatusResponse>> resp = controller.getProhibitionInfo(NOTICE_NUMBER, CORRELATION_ID);
		
		Assertions.assertEquals(NOTICE_SERVED_DATE, resp.getBody().getData().getStatus().getNoticeServedDt());
		Assertions.assertEquals(NOTICE_TYPE_CD, resp.getBody().getData().getStatus().getNoticeTypeCd());
		Assertions.assertEquals(ORIGINAL_CAUSE_CD, resp.getBody().getData().getStatus().getOriginalCause());
		Assertions.assertEquals(ANSWER_Y, resp.getBody().getData().getStatus().getReviewCreatedYn());
		Assertions.assertEquals(ANSWER_Y, resp.getBody().getData().getStatus().getReviewFormSubmittedYn());
		Assertions.assertEquals(SURNAME, resp.getBody().getData().getStatus().getSurnameNm());
		Assertions.assertEquals(ANSWER_N, resp.getBody().getData().getStatus().getDriverLicenceSeizedYn());
		
		// disclosures block
		Assertions.assertEquals(DISCLOSED_DTM, resp.getBody().getData().getStatus().getDisclosure().get(0).getDisclosedDtm());
		Assertions.assertEquals(DISCLOSURE_DOC_ID, resp.getBody().getData().getStatus().getDisclosure().get(0).getDocumentId());
		
		// reviews block
		Assertions.assertEquals(REVIEW_START_DTM, resp.getBody().getData().getStatus().getReviews().get(0).getReviewStartDtm());
		Assertions.assertEquals(REVIEW_END_DTM, resp.getBody().getData().getStatus().getReviews().get(0).getReviewEndDtm());
		Assertions.assertEquals(APPLICATION_ID, resp.getBody().getData().getStatus().getReviews().get(0).getApplicationId());
		Assertions.assertEquals(RECEIPT_NUM_TXT, resp.getBody().getData().getStatus().getReviews().get(0).getReceiptNumberTxt());
		Assertions.assertEquals(REVIEW_ID, resp.getBody().getData().getStatus().getReviews().get(0).getReviewId());
		Assertions.assertEquals(REVIEW_STATUS, resp.getBody().getData().getStatus().getReviews().get(0).getStatus());
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
