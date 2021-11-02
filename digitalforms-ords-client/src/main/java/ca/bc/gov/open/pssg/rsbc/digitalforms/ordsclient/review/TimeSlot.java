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
@JsonPropertyOrder({ "reviewStartDtm", "reviewEndDtm" })
public class TimeSlot {

	@JsonProperty("reviewStartDtm")
	private String reviewStartDtm;

	@JsonProperty("reviewEndDtm")
	private String reviewEndDtm;

	public TimeSlot(String reviewStartDtm, String reviewEndDtm) {
		this.reviewStartDtm = reviewStartDtm;
		this.reviewEndDtm = reviewEndDtm;
	}

	@JsonProperty("reviewStartDtm")
	public String getReviewStartDtm() {
		return reviewStartDtm;
	}

	@JsonProperty("reviewStartDtm")
	public void setReviewStartDtm(String reviewStartDtm) {
		this.reviewStartDtm = reviewStartDtm;
	}

	@JsonProperty("reviewEndDtm")
	public String getReviewEndDtm() {
		return reviewEndDtm;
	}

	@JsonProperty("reviewEndDtm")
	public void setReviewEndDtm(String reviewEndDtm) {
		this.reviewEndDtm = reviewEndDtm;
	}
}
