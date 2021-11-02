package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Strings;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

/**
 * 
 * Review application form object
 * 
 * @author sivakaruna
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "noticeSubjectCd", "presentationTypeCd", "reviewRoleTypeCd", "firstGivenNm", "secondGivenNm",
		"surnameNm", "phoneNo", "faxNo", "email", "manualEntryYN", "formData" })
public class ApplicationFormDataPatch {

	@Size(min=1)
	@JsonProperty("noticeSubjectCd")
	private String noticeSubjectCd;

	@Size(min=1)
	@JsonProperty("presentationTypeCd")
	private String presentationTypeCd;

	@Size(min=1)
	@JsonProperty("reviewRoleTypeCd")
	private String reviewRoleTypeCd;

	@Size(min=1, max=35)
	@JsonProperty("firstGivenNm")
	private String firstGivenNm;

	@Size(min=1, max=35)
	@JsonProperty("secondGivenNm")
	private String secondGivenNm;

	@Size(min=1, max=35)
	@JsonProperty("surnameNm")
	private String surnameNm;
	 
	@Size(min=1, max=10)
	@JsonProperty("phoneNo")
	private String phoneNo;

	@Size(min=1, max=10)
	@JsonProperty("faxNo")
	private String faxNo;

	@Size(min=1, max=200)
	@JsonProperty("email")
	private String email;

	@Size(min=1, max=1)
	@JsonProperty("manualEntryYN")
	private String manualEntryYN;

	@Size(min=1)
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

	/**
	 * Method to validate application form data
	 * @throws DigitalFormsException
	 */
	public void validateRequiredFields() throws DigitalFormsException {
		if (Strings.isNullOrEmpty(this.noticeSubjectCd) || Strings.isNullOrEmpty(this.presentationTypeCd)
				|| Strings.isNullOrEmpty(this.reviewRoleTypeCd) || Strings.isNullOrEmpty(this.firstGivenNm)
				|| Strings.isNullOrEmpty(this.surnameNm) || Strings.isNullOrEmpty(this.phoneNo)
				|| Strings.isNullOrEmpty(this.manualEntryYN) || Strings.isNullOrEmpty(this.formData)) {
			throw new DigitalFormsException(DigitalFormsConstants.MISSING_REQUEST_BODY_ERROR, HttpStatus.NOT_FOUND);
		}
	}
}
