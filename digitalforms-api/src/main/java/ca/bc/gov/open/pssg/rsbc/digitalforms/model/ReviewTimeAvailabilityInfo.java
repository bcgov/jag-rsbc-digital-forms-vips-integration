package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Review time availability info object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "unavailableReason", "timeSlots" })
public class ReviewTimeAvailabilityInfo {

	@JsonProperty("unavailableReason")
	private String unavailableReason;

	@JsonProperty("timeSlots")
	private List<ReviewTimeSlot> timeSlots = new ArrayList<>();

	@JsonProperty("unavailableReason")
	public String getUnavailableReason() {
		return unavailableReason;
	}

	@JsonProperty("unavailableReason")
	public void setUnavailableReason(String unavailableReason) {
		this.unavailableReason = unavailableReason;
	}

	@JsonProperty("timeSlots")
	public List<ReviewTimeSlot> getTimeSlots() {
		return timeSlots;
	}

	@JsonProperty("timeSlots")
	public void setTimeSlots(List<ReviewTimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

}
