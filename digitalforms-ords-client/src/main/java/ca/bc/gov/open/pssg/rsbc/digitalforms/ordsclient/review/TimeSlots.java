package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timeSlots" })
public class TimeSlots {

	@JsonProperty("timeSlots")
	private List<TimeSlot> timeSlots = null;

	@JsonProperty("timeSlots")
	public List<TimeSlot> getTimeSlots() {
		if (null == this.timeSlots) 
			this.timeSlots = new ArrayList<TimeSlot>();
		return timeSlots;
	}

	@JsonProperty("timeSlots")
	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

}
