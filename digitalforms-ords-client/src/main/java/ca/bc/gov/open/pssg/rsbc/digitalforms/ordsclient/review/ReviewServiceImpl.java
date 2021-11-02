package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.AvailableTimeSlotsApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotResponse;

/**
 * 
 * Collection of services for scheduling a review.
 * 
 * @author shaunmillargov
 *
 */
public class ReviewServiceImpl implements ReviewService {

	private final AvailableTimeSlotsApi availableTimeSlotsApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ReviewServiceImpl(AvailableTimeSlotsApi availableTimeSlotsApi) {
		this.availableTimeSlotsApi = availableTimeSlotsApi;
	}

	@Override
	public TimeSlotResponse getAvailableTimeSlots(String authGuid, String correlationId, String noticeTypeCd,
			String reviewDate, String reviewTypeCd) {

		try {
			AvailableTimeSlotResponse response = this.availableTimeSlotsApi
					.digitalFormAvailableTimeSlotReviewDateNoticeTypeCdReviewTypeCdAuthGuidCorrelationGuidGet(authGuid,
							correlationId, noticeTypeCd, reviewDate, reviewTypeCd);

			logger.info("Processed Get Available Time Slots request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return TimeSlotResponse.successResponse(response, response.getStatusCode(), response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Review Service threw an exception: " + ex.getMessage(), ex);
			return TimeSlotResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public SavedTimeSlotResponse saveTimeSlot(String authGuid, String correlationId, String applicationId,
			ReviewTimeSlotRequest request) {

		try {

			ReviewTimeSlotResponse response = this.availableTimeSlotsApi
					.digitalFormProhibitionApplicationIdReviewScheduleAuthGuidCorrelationGuidPost(authGuid, correlationId,
							applicationId, request);

			logger.info("Processed Save Time Slot request: ORDS returned code: {} and message: {} ",
					response.getStatusCode(), response.getStatusMessage());

			return SavedTimeSlotResponse.successResponse(response, response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Review Service threw an exception: " + ex.getMessage(), ex);
			return SavedTimeSlotResponse.errorResponse(ex.getMessage());
		}
	}

}
