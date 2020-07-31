package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlotResponse;

/**
 * 
 * Schedule a review service operations interface
 * 
 * @author sivakaruna
 *
 */
public interface ScheduleReviewService {
	
	public TimeSlotResponse getAvailableTimeSlots(String noticeTypeCd, String reviewTypeCd, String reviewDate, String correlationId);
	
	public boolean postSelectedReviewTime(String noticeNumber, TimeSlot timeSlot);

}
