package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.AvailableTimeSlotsApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponse;

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

			return TimeSlotResponse.successResponse(response, response.getStatusCode(), response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Review Service threw an exception: " + ex.getMessage(), ex);
			return TimeSlotResponse.errorResponse(ex.getMessage());
		}
	}
}
