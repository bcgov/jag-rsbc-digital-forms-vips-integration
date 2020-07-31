package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

/**
 * 
 * Collection of services for accessing Digital forms payment related data.
 * 
 * @author sivakaruna
 *
 */
public interface ReviewService {

	TimeSlotResponse getAvailableTimeSlots(String authGuid, String correlationId, String noticeTypeCd, String reviewDate, String reviewTypeCd); 
	
}
