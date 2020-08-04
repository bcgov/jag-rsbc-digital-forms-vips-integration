package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.ReviewTimeSlotResponse;

/**
 * 
 * Saved Time Slot Response object
 * 
 * @author shaunmillargov
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "respCode", "respMsg", "reviewStartDtm", "reviewEndDtm" })
public class SavedTimeSlotResponse {

	@JsonProperty("respCode")
	private int respCode;
	
	@JsonProperty("respMsg")
	private String respMsg;
	
	@JsonProperty("reviewStartDtm")
	private String reviewStartDtm;
	
	@JsonProperty("reviewEndDtm")
	private String reviewEndDtm;

	@JsonProperty("respCode")
	public int getRespCode() {
		return respCode;
	}
	
	@JsonProperty("respMsg")
	public String getRespMsg() {
		return respMsg;
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
	
	public SavedTimeSlotResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}
	
	public SavedTimeSlotResponse(ReviewTimeSlotResponse response, String statusCode, String statusMessage) {
		
		this.respCode = Integer.parseInt(statusCode); 
		this.respMsg = statusMessage;
		
		this.reviewStartDtm = response.getReviewStartDtm();
		this.reviewEndDtm = response.getReviewEndDtm();
	}
	
	public static SavedTimeSlotResponse successResponse(ReviewTimeSlotResponse response, String statusCode, String statusMessage) {
		return new SavedTimeSlotResponse(response, statusCode, statusMessage);
	}

	public static SavedTimeSlotResponse errorResponse(String errorMessage) {
		return new SavedTimeSlotResponse(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

}

