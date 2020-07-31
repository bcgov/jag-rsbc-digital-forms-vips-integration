package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.AvailableTimeSlotResponseTimeSlots;

/**
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timeSlots", "respCode", "respMsg" })
public class TimeSlotResponse {
	
	@JsonProperty("timeSlots")
	private TimeSlots timeslots;

	@JsonProperty("respCode")
	private int respCode;

	@JsonProperty("respMsg")
	private String respMsg;
		

	@JsonProperty("timeSlots")
	public TimeSlots getTimeslots() {
		return timeslots;
	}

	@JsonProperty("timeSlots")
	public void setTimeslots(TimeSlots timeslots) {
		this.timeslots = timeslots;
	}

	@JsonProperty("respCode")
	public int getRespCode() {
		return respCode;
	}

	@JsonProperty("respCode")
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	@JsonProperty("respMsg")
	public String getRespMsg() {
		return respMsg;
	}

	@JsonProperty("respMsg")
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public TimeSlotResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}
	
	public TimeSlotResponse(AvailableTimeSlotResponse response, String statusCode, String statusMessage) {
		this.respCode = Integer.parseInt(statusCode); 
		this.respMsg = statusMessage; 
		for (AvailableTimeSlotResponseTimeSlots element:  response.getTimeSlots()) {
			TimeSlot ts = new TimeSlot(element.getReviewStartDtm(), element.getReviewEndDtm()); 
			this.timeslots.getTimeSlots().add(ts);
		}
	}

	public static TimeSlotResponse successResponse(AvailableTimeSlotResponse response, String statusCode, String statusMessage) {
		return new TimeSlotResponse(response, statusCode, statusMessage);
	}

	public static TimeSlotResponse errorResponse(String errorMessage) {
		return new TimeSlotResponse(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

}
