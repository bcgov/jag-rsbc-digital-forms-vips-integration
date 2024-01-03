package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponseTimeSlotsInner;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.ReviewService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.SavedTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlotResponse;

/**
 * 
 * Schedule Review Service Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ScheduleReviewServiceImplTests {

	@Mock
	private ReviewService service;

	@Mock
	private ConfigProperties properties;

	@InjectMocks
	private ScheduleReviewService serviceImpl = new ScheduleReviewServiceImpl();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(properties.getOrdsUserGuid()).thenReturn("user");
	}

	@DisplayName("Post success - ScheduleReviewService")
	@Test
	void saveTimeSlotSuccess() {
		when(service.saveTimeSlot(any(), any(), any(), any()))
				.thenReturn(SavedTimeSlotResponse.successResponse(new ReviewTimeSlotResponse(), "0", "success"));
		SavedTimeSlotResponse resp = serviceImpl.postSelectedReviewTime("1", new TimeSlot("startTm", "endTm"),
				"correlationId");
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Get success - ScheduleReviewService")
	@Test
	void getAvailableTimeSlotsSuccess() {
		AvailableTimeSlotResponse response = new AvailableTimeSlotResponse();
		List<AvailableTimeSlotResponseTimeSlotsInner> timeSlotList = new ArrayList<>();
		AvailableTimeSlotResponseTimeSlotsInner timeSlot = new AvailableTimeSlotResponseTimeSlotsInner();
		timeSlot.setReviewEndDtm("reviewEndDtm");
		timeSlot.setReviewStartDtm("reviewStartDtm");
		timeSlotList.add(timeSlot);
		response.setTimeSlots(timeSlotList);
		response.setStatusCode("0");
		response.setStatusMessage("statusMessage");
		when(service.getAvailableTimeSlots(any(), any(), any(), any(), any()))
				.thenReturn(TimeSlotResponse.successResponse(response, "0", "success"));
		TimeSlotResponse resp = serviceImpl.getAvailableTimeSlots("1", "IRP", "reviewDate", "correlationId");
		Assertions.assertEquals(0, resp.getRespCode());
	}

	@DisplayName("Post error - ScheduleReviewService")
	@Test
	void saveTimeSlotError() {
		when(service.saveTimeSlot(any(), any(), any(), any()))
				.thenReturn(SavedTimeSlotResponse.errorResponse("Post error"));
		SavedTimeSlotResponse resp = serviceImpl.postSelectedReviewTime("1", new TimeSlot("startTm", "endTm"),
				"correlationId");
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Post error", resp.getRespMsg());

	}

	@DisplayName("Get error - ScheduleReviewService")
	@Test
	void getAvailableTimeSlotsError() {
		when(service.getAvailableTimeSlots(any(), any(), any(), any(), any()))
				.thenReturn(TimeSlotResponse.errorResponse("Get error"));
		TimeSlotResponse resp = serviceImpl.getAvailableTimeSlots("1", "IRP", "reviewDate", "correlationId");
		Assertions.assertEquals(-1, resp.getRespCode());
		Assertions.assertEquals("Get error", resp.getRespMsg());
	}
}
