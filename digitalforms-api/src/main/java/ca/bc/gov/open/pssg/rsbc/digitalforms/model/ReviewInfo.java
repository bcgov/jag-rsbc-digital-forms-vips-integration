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
//@JsonPropertyOrder({ "reviewId", "reviewStartDtm", "reviewEndDtm" })
@JsonPropertyOrder({
"applicationId",
"status",
"reviewStartDtm",
"reviewEndDtm",
"receiptNumberTxt",
"reviewId"
})
public class ReviewInfo {

	@JsonProperty("applicationId")
	private String applicationId;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("reviewStartDtm")
	private String reviewStartDtm;
	
	@JsonProperty("reviewEndDtm")
	private String reviewEndDtm;
	
	@JsonProperty("receiptNumberTxt")
	private String receiptNumberTxt;
	
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

	@JsonProperty("reviewId")
	public String getReviewId() {
		return reviewId;
	}

	@JsonProperty("reviewId")
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@JsonProperty("applicationId")
	public String getApplicationId() {
		return applicationId;
	}

	@JsonProperty("applicationId")
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
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

	@JsonProperty("receiptNumberTxt")
	public String getReceiptNumberTxt() {
		return receiptNumberTxt;
	}

	@JsonProperty("receiptNumberTxt")
	public void setReceiptNumberTxt(String receiptNumberTxt) {
		this.receiptNumberTxt = receiptNumberTxt;
	}
	

}
