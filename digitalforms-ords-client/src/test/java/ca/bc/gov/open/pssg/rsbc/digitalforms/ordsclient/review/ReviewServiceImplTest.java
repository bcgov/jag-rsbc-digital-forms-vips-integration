package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.AvailableTimeSlotsApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponseTimeSlots;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotResponse;

/**
 * Review service tests
 *
 * @author sivakaruna
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	public static final String SUCCESS_RESPONSE = "success";
	public static final String ERROR_RESPONSE = "error";

	private ReviewService service;

	@Mock
	private AvailableTimeSlotsApi timeSlotApiMock;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);

		AvailableTimeSlotResponseTimeSlots timeSlot = new AvailableTimeSlotResponseTimeSlots();
		timeSlot.setReviewEndDtm("2018-05-01 10:30:00 -07:00");
		timeSlot.setReviewStartDtm("2018-05-01 09:30:00 -07:00");
		List<AvailableTimeSlotResponseTimeSlots> timeSlots = new ArrayList<>();
		timeSlots.add(timeSlot);
		AvailableTimeSlotResponse timeSlotResponseSuccess = new AvailableTimeSlotResponse();
		timeSlotResponseSuccess.setStatusCode(String.valueOf(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD));
		timeSlotResponseSuccess.setStatusMessage(SUCCESS_RESPONSE);
		timeSlotResponseSuccess.setTimeSlots(timeSlots);

		ReviewTimeSlotResponse reviewTimeSlotResponseSuccess = new ReviewTimeSlotResponse();
		reviewTimeSlotResponseSuccess.setStatusCode(String.valueOf(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD));
		reviewTimeSlotResponseSuccess.setStatusMessage(SUCCESS_RESPONSE);
		reviewTimeSlotResponseSuccess.setReviewEndDtm("2018-05-01 10:30:00 -07:00");
		reviewTimeSlotResponseSuccess.setReviewStartDtm("2018-05-01 09:30:00 -07:00");

		Mockito.when(timeSlotApiMock
				.digitalFormAvailableTimeSlotReviewDateNoticeTypeCdReviewTypeCdAuthGuidCorrelationGuidGet(any(), any(),
						eq("IRP"), any(), any()))
				.thenReturn(timeSlotResponseSuccess);
		Mockito.when(timeSlotApiMock
				.digitalFormAvailableTimeSlotReviewDateNoticeTypeCdReviewTypeCdAuthGuidCorrelationGuidGet(any(), any(),
						eq("ADP"), any(), any()))
				.thenThrow(new ApiException(ERROR_RESPONSE));
		Mockito.when(timeSlotApiMock.digitalFormProhibitionNoticeNoReviewScheduleAuthGuidCorrelationGuidPost(any(),
				any(), eq("1"), any())).thenReturn(reviewTimeSlotResponseSuccess);
		Mockito.when(timeSlotApiMock.digitalFormProhibitionNoticeNoReviewScheduleAuthGuidCorrelationGuidPost(any(),
				any(), eq("2"), any())).thenThrow(new ApiException(ERROR_RESPONSE));

		service = new ReviewServiceImpl(timeSlotApiMock);
	}

	@Test
	public void patchSuccess() {
		SavedTimeSlotResponse response = service.saveTimeSlot("authGuid", "correlationId", "1",
				new ReviewTimeSlotRequest());

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void patchException() {
		SavedTimeSlotResponse response = service.saveTimeSlot("authGuid", "correlationId", "2",
				new ReviewTimeSlotRequest());

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

	@Test
	public void getSuccess() {
		TimeSlotResponse response = service.getAvailableTimeSlots("authGuid", "correlationId", "IRP",
				"2018-05-01 00:00:00 -00:00", "reviewTypeCd");

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void getException() {
		TimeSlotResponse response = service.getAvailableTimeSlots("authGuid", "correlationId", "ADP",
				"2018-05-01 00:00:00 -00:00", "reviewTypeCd");

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

}
