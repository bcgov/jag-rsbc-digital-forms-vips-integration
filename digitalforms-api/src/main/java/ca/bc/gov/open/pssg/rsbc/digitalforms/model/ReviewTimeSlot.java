package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Review Time slot object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "startTm", "endTm" })
public class ReviewTimeSlot {

	@JsonProperty("startTm")
	private String startTm;

	@JsonProperty("endTm")
	private String endTm;

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
