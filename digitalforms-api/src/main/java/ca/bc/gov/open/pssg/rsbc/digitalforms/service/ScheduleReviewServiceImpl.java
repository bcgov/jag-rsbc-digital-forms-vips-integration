package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger logger = LoggerFactory.getLogger(ScheduleReviewServiceImpl.class);

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ConfigProperties properties;

	@Override
	public TimeSlotResponse getAvailableTimeSlots(String noticeTypeCd, String reviewTypeCd, String reviewDate,
			String correlationId) {
		logger.info("Processing get available time slots request");

		return reviewService.getAvailableTimeSlots(properties.getOrdsUserGuid(), correlationId, noticeTypeCd,
				reviewDate, reviewTypeCd);
	}

	@Override
	public SavedTimeSlotResponse postSelectedReviewTime(String noticeNumber, TimeSlot timeSlot, String correlationId) {
		logger.info("Processing post selected review time request");

		ReviewTimeSlotRequest request = new ReviewTimeSlotRequest();
		request.setReviewEndDtm(timeSlot.getReviewEndDtm());
		request.setReviewStartDtm(timeSlot.getReviewStartDtm());

		return reviewService.saveTimeSlot(properties.getOrdsUserGuid(), correlationId, noticeNumber, request);
	}

}
