package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * 
 * To be removed once updates to VIPS ORDS have been completed to 
 * support changes to the Prohibition Status operation. 
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "noticeType", "effectiveDate", "reviewFormSubmitted", "reviewCreated", "originalCause",
		"surnameNm" })
public class MockProhibitionStatus {

	@JsonProperty("noticeType")
	private String noticeType;
	@JsonProperty("effectiveDate")
	private String effectiveDate;
	@JsonProperty("reviewFormSubmitted")
	private String reviewFormSubmitted;
	@JsonProperty("reviewCreated")
	private String reviewCreated;
	@JsonProperty("originalCause")
	private String originalCause;
	@JsonProperty("surnameNm")
	private String surnameNm;

	@JsonProperty("noticeType")
	public String getNoticeType() {
		return noticeType;
	}

	@JsonProperty("noticeType")
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	@JsonProperty("effectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@JsonProperty("effectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("reviewFormSubmitted")
	public String getReviewFormSubmitted() {
		return reviewFormSubmitted;
	}

	@JsonProperty("reviewFormSubmitted")
	public void setReviewFormSubmitted(String reviewFormSubmitted) {
		this.reviewFormSubmitted = reviewFormSubmitted;
	}

	@JsonProperty("reviewCreated")
	public String getReviewCreated() {
		return reviewCreated;
	}

	@JsonProperty("reviewCreated")
	public void setReviewCreated(String reviewCreated) {
		this.reviewCreated = reviewCreated;
	}

	@JsonProperty("originalCause")
	public String getOriginalCause() {
		return originalCause;
	}

	@JsonProperty("originalCause")
	public void setOriginalCause(String originalCause) {
		this.originalCause = originalCause;
	}

	@JsonProperty("surnameNm")
	public String getSurnameNm() {
		return surnameNm;
	}

	@JsonProperty("surnameNm")
	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}

}