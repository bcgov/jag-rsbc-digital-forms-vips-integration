package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;

public class TimeSlotWrapper {

	@JsonProperty("timeSlot")
	private TimeSlot timeSlot;

	public TimeSlotWrapper(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public TimeSlotWrapper() {
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

}
