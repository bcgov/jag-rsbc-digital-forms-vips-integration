package ca.bc.gov.open.pssg.rsbc.digitalforms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.ReviewService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.SavedTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlotResponse;

/**
 * 
 * Schedule a review service operations implementation
 * 
 * @author sivakaruna
 *
 */
@Service
public class ScheduleReviewServiceImpl implements ScheduleReviewService {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ConfigProperties properties;

	@Override
	public TimeSlotResponse getAvailableTimeSlots(String noticeTypeCd, String reviewTypeCd, String reviewDate,
			String correlationId) {

		return reviewService.getAvailableTimeSlots(properties.getOrdsUserGuid(), correlationId, noticeTypeCd, reviewDate, reviewTypeCd);
	}

	@Override
	public SavedTimeSlotResponse postSelectedReviewTime(String noticeNumber, TimeSlot timeSlot, String correlationId) {

		ReviewTimeSlotRequest request = new ReviewTimeSlotRequest();
		request.setReviewEndDtm(timeSlot.getEndTm());
		request.setReviewStartDtm(timeSlot.getStartTm());

		return reviewService.saveTimeSlot(properties.getOrdsUserGuid(), correlationId, noticeNumber, request);
	}

}
