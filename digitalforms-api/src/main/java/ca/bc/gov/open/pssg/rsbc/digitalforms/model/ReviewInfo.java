package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.SavedTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;

/**
 * 
 * Review Info object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "reviewId", "reviewStartDtm", "reviewEndDtm" })
public class ReviewInfo {

	@JsonProperty("reviewId")
	private String reviewId;

	@JsonUnwrapped
	private TimeSlot timeSlot;

	public ReviewInfo() {
	}

	public ReviewInfo(SavedTimeSlotResponse data) {
		this.reviewId = data.getReviewId();
		this.timeSlot = new TimeSlot(data.getReviewStartDtm(), data.getReviewEndDtm());
	}

	public ReviewInfo(String reviewId, TimeSlot timeSlot) {
		this.reviewId = reviewId;
		this.timeSlot = timeSlot;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

}
