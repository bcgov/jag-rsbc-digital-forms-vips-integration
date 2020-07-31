package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "startTm", "endTm" })
public class TimeSlot {

	@JsonProperty("startTm")
	private String startTm;
	@JsonProperty("endTm")
	private String endTm;

	public TimeSlot(String startTm, String endTm) {
		this.startTm = startTm; 
		this.endTm = endTm;
	}

	@JsonProperty("startTm")
	public String getStartTm() {
		return startTm;
	}

	@JsonProperty("startTm")
	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}

	@JsonProperty("endTm")
	public String getEndTm() {
		return endTm;
	}

	@JsonProperty("endTm")
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
}
