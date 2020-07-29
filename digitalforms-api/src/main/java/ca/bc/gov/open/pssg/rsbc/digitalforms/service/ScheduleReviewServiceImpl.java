package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewTimeSlot;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewTimeAvailabilityInfo;

/**
 * 
 * Schedule a review service operations implementation
 * 
 * @author sivakaruna
 *
 */
@Service
public class ScheduleReviewServiceImpl implements ScheduleReviewService {

	@Override
	public ReviewTimeAvailabilityInfo getAvailableTimeSlots(String noticeTypeCd, String reviewTypeCd,
			String reviewDate) {
		// TODO Service to be written
		// Dummy return value
		ReviewTimeAvailabilityInfo dummy = new ReviewTimeAvailabilityInfo();
		ReviewTimeSlot dummySlot = new ReviewTimeSlot();
		dummySlot.setEndTm("endTime");
		dummySlot.setStartTm("startTime");
		List<ReviewTimeSlot> dummySlotList = new ArrayList<>();
		dummySlotList.add(dummySlot);
		dummy.setTimeSlots(dummySlotList);
		return dummy;
	}

	@Override
	public boolean postSelectedReviewTime(String noticeNumber, ReviewTimeSlot timeSlot) {
		// TODO Service to be written
		return true;
	}

}
