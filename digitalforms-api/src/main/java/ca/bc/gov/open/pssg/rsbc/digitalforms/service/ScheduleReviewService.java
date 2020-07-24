package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewTimeAvailabilityInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewTimeSlot;

/**
 * 
 * Schedule a review service operations interface
 * 
 * @author sivakaruna
 *
 */
public interface ScheduleReviewService {
	
	public ReviewTimeAvailabilityInfo getAvailableTimeSlots(String noticeTypeCd, String reviewTypeCd, String reviewDate);
	
	public boolean postSelectedReviewTime(Long noticeNumber, ReviewTimeSlot timeSlot);

}
