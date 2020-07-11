package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.text.MessageFormat;

/**
 *
 * Represents the Application Info
 *
 * @author sivakaruna
 *
 */
@JacksonXmlRootElement(localName = "applicationInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prohibitionNoticeNo", "noticeSubjectCd", "noticeTypeCd", "reviewApplnTypeCd",
		"presentationTypeCd", "reviewRoleTypeCd", "firstGivenNm", "secondGivenNm", "surnameNm", "phoneNo", "faxNo",
		"email", "manualEntryYN" })
public class ApplicationInfo {

	@JsonProperty("prohibitionNoticeNo")
	private String prohibitionNoticeNo;
	@JsonProperty("noticeSubjectCd")
	private String noticeSubjectCd;
	@JsonProperty("noticeTypeCd")
	private String noticeTypeCd;
	@JsonProperty("reviewApplnTypeCd")
	private String reviewApplnTypeCd;
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

	@JsonProperty("prohibitionNoticeNo")
	public String getProhibitionNoticeNo() {
		return prohibitionNoticeNo;
	}

	@JsonProperty("prohibitionNoticeNo")
	public void setProhibitionNoticeNo(String prohibitionNoticeNo) {
		this.prohibitionNoticeNo = prohibitionNoticeNo;
	}

	@JsonProperty("noticeSubjectCd")
	public String getNoticeSubjectCd() {
		return noticeSubjectCd;
	}

	@JsonProperty("noticeSubjectCd")
	public void setNoticeSubjectCd(String noticeSubjectCd) {
		this.noticeSubjectCd = noticeSubjectCd;
	}

	@JsonProperty("noticeTypeCd")
	public String getNoticeTypeCd() {
		return noticeTypeCd;
	}

	@JsonProperty("noticeTypeCd")
	public void setNoticeTypeCd(String noticeTypeCd) {
		this.noticeTypeCd = noticeTypeCd;
	}

	@JsonProperty("reviewApplnTypeCd")
	public String getReviewApplnTypeCd() {
		return reviewApplnTypeCd;
	}

	@JsonProperty("reviewApplnTypeCd")
	public void setReviewApplnTypeCd(String reviewApplnTypeCd) {
		this.reviewApplnTypeCd = reviewApplnTypeCd;
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

	@Override
	public String toString() {
		return MessageFormat.format(
				"ApplicationInfo :  prohibitionNoticeNo[{0}], noticeSubjectCd[{1}], noticeTypeCd[{2}], "
						+ "reviewApplnTypeCd[{3}], presentationTypeCd[{4}], reviewRoleTypeCd[{5}], firstGivenNm[{6}], "
						+ "secondGivenNm[{7}], surnameNm[{8}], phoneNo[{9}], faxNo[{10}], email[{11}], "
						+ "manualEntryYN[{12}], formXml[{13}]]",
				this.prohibitionNoticeNo, this.noticeSubjectCd, this.noticeTypeCd, this.reviewApplnTypeCd,
				this.presentationTypeCd, this.reviewRoleTypeCd, this.firstGivenNm, this.secondGivenNm, this.surnameNm,
				this.phoneNo, this.faxNo, this.email, this.manualEntryYN);
	}

}
