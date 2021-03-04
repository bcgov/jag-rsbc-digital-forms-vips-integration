package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

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
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewInfoWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.TimeSlotWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.SavedTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlots;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ScheduleReviewService;

/**
 * 
 * Schedule Review Controller Tests.
 * 
 * @author shaunmillargov
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class ScheduleReviewControllerTests {

	private final String TEST_APPLICATION_NOTICE_NUMBER_SUCCESS = "1";
	private final String TEST_APPLICATION_NOTICE_NUMBER_ERROR = "2";
	private final String CORRELATION_ID = "correlationId";
	private final String REVIEW_DATE = "2018-06-29 00:00:00 -07:00";
	private final String NOTICE_TYPE_SUCCESS = "IRP";
	private final String NOTICE_TYPE_ERROR = "ADP";
	private final String REVIEW_TYPE = "WRIT";
	private final String SUCCESS_STATUS = "success";
	private final String SUCCESS_CODE = "0";
	private final String ERROR_STATUS = "error";
	private TimeSlot timeSlot = new TimeSlot("2018-06-29 09:00:00 -07:00", "2018-06-29 09:30:00 -07:00");

	@Mock
	private ScheduleReviewService service;

	@InjectMocks
	private ScheduleReviewController controller = new ScheduleReviewController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReviewTimeSlotResponse response = new ReviewTimeSlotResponse();
		response.setReviewId("123");
		when(service.getAvailableTimeSlots(NOTICE_TYPE_SUCCESS, REVIEW_TYPE, REVIEW_DATE, CORRELATION_ID)).thenReturn(
				TimeSlotResponse.successResponse(new AvailableTimeSlotResponse(), SUCCESS_CODE, SUCCESS_STATUS));
		when(service.getAvailableTimeSlots(NOTICE_TYPE_ERROR, REVIEW_TYPE, REVIEW_DATE, CORRELATION_ID))
				.thenReturn(TimeSlotResponse.errorResponse(ERROR_STATUS));
		when(service.postSelectedReviewTime(TEST_APPLICATION_NOTICE_NUMBER_SUCCESS, timeSlot, CORRELATION_ID))
				.thenReturn(SavedTimeSlotResponse.successResponse(response, SUCCESS_CODE, SUCCESS_STATUS));
		when(service.postSelectedReviewTime(TEST_APPLICATION_NOTICE_NUMBER_ERROR, timeSlot, CORRELATION_ID))
				.thenReturn(SavedTimeSlotResponse.errorResponse(ERROR_STATUS));
	}

	@DisplayName("availableTimeSlotsGet - Success")
	@Test
	void availableTimeSlotsGetSuccess() {
		ResponseEntity<JSONResponse<TimeSlots>> resp = controller.availableTimeSlotsGet(NOTICE_TYPE_SUCCESS,
				REVIEW_TYPE, REVIEW_DATE, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@DisplayName("availableTimeSlotsGet - Not found")
	@Test
	void availableTimeSlotsGetNotFound() {
		ResponseEntity<JSONResponse<TimeSlots>> resp = controller.availableTimeSlotsGet(NOTICE_TYPE_ERROR, REVIEW_TYPE,
				REVIEW_DATE, CORRELATION_ID);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

	@DisplayName("selectedReviewTimePost - Success")
	@Test
	void selectedReviewTimePostSuccess() throws DigitalFormsException {
		ResponseEntity<JSONResponse<ReviewInfoWrapper>> resp = controller
				.selectedReviewTimePost(TEST_APPLICATION_NOTICE_NUMBER_SUCCESS, CORRELATION_ID, new TimeSlotWrapper(timeSlot));
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertEquals("123", resp.getBody().getData().getReviewInfo().getReviewId());
	}

	@DisplayName("selectedReviewTimePost - Error")
	@Test
	void selectedReviewTimePostError() throws DigitalFormsException {
		ResponseEntity<JSONResponse<ReviewInfoWrapper>> resp = controller
				.selectedReviewTimePost(TEST_APPLICATION_NOTICE_NUMBER_ERROR, CORRELATION_ID, new TimeSlotWrapper(timeSlot));
		Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

}
