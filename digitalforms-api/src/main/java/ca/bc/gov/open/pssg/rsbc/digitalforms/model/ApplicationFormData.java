package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Review Form Request object
 * 
 * @author sivakaruna
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "noticeSubjectCd", "presentationTypeCd", "reviewRoleTypeCd", "firstGivenNm", "secondGivenNm",
		"surnameNm", "phoneNo", "faxNo", "email", "manualEntryYN", "formData" })
public class ApplicationFormData {

	@JsonProperty("noticeSubjectCd")
	private String noticeSubjectCd;
	@JsonProperty("presentationTypeCd")
	private String presentationTypeCd;
	@JsonProperty("reviewRoleTypeCd")
	private String reviewRoleTypeCd;
	@JsonProperty("firstGivenNm")
	private String firstGivenNm;
	@JsonProperty("secondGivenNm")
	private String secondGivenNm;
	@JsonProperty("surnameNm")
	private String surnameNm;
	@JsonProperty("phoneNo")
	private String phoneNo;
	@JsonProperty("faxNo")
	private String faxNo;
	@JsonProperty("email")
	private String email;
	@JsonProperty("manualEntryYN")
	private String manualEntryYN;
	@JsonProperty("formData")
	private String formData;

	@JsonProperty("noticeSubjectCd")
	public String getNoticeSubjectCd() {
		return noticeSubjectCd;
	}

	@JsonProperty("noticeSubjectCd")
	public void setNoticeSubjectCd(String noticeSubjectCd) {
		this.noticeSubjectCd = noticeSubjectCd;
	}

	@JsonProperty("presentationTypeCd")
	public String getPresentationTypeCd() {
		return presentationTypeCd;
	}

	@JsonProperty("presentationTypeCd")
	public void setPresentationTypeCd(String presentationTypeCd) {
		this.presentationTypeCd = presentationTypeCd;
	}

	@JsonProperty("reviewRoleTypeCd")
	public String getReviewRoleTypeCd() {
		return reviewRoleTypeCd;
	}

	@JsonProperty("reviewRoleTypeCd")
	public void setReviewRoleTypeCd(String reviewRoleTypeCd) {
		this.reviewRoleTypeCd = reviewRoleTypeCd;
	}

	@JsonProperty("firstGivenNm")
	public String getFirstGivenNm() {
		return firstGivenNm;
	}

	@JsonProperty("firstGivenNm")
	public void setFirstGivenNm(String firstGivenNm) {
		this.firstGivenNm = firstGivenNm;
	}

	@JsonProperty("secondGivenNm")
	public String getSecondGivenNm() {
		return secondGivenNm;
	}

	@JsonProperty("secondGivenNm")
	public void setSecondGivenNm(String secondGivenNm) {
		this.secondGivenNm = secondGivenNm;
	}

	@JsonProperty("surnameNm")
	public String getSurnameNm() {
		return surnameNm;
	}

	@JsonProperty("surnameNm")
	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}

	@JsonProperty("phoneNo")
	public String getPhoneNo() {
		return phoneNo;
	}

	@JsonProperty("phoneNo")
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@JsonProperty("faxNo")
	public String getFaxNo() {
		return faxNo;
	}

	@JsonProperty("faxNo")
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("manualEntryYN")
	public String getManualEntryYN() {
		return manualEntryYN;
	}

	@JsonProperty("manualEntryYN")
	public void setManualEntryYN(String manualEntryYN) {
		this.manualEntryYN = manualEntryYN;
	}

	@JsonProperty("formData")
	public String getFormData() {
		return formData;
	}

	@JsonProperty("formData")
	public void setFormData(String formData) {
		this.formData = formData;
	}

}
