package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotRequest;

/**
 * 
 * Collection of services for accessing Digital forms payment related data.
 * 
 * @author sivakaruna
 *
 */
public interface ReviewService {

	TimeSlotResponse getAvailableTimeSlots(String authGuid, String correlationId, String noticeTypeCd, String reviewDate, String reviewTypeCd);
	
	SavedTimeSlotResponse saveTimeSlot(String authGuid, String correlationId, String applicationId, ReviewTimeSlotRequest request); 
	
}
