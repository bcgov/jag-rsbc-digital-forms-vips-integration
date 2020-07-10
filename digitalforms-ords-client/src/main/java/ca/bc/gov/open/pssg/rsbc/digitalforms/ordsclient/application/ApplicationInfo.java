package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ApplicationInfo {

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("prohibition_notice_no")
	private String prohibitionNoticeNo;

	@JsonProperty("notice_subject_cd")
	private String noticeSubjectCd;

	@JsonProperty("notice_type_cd")
	private String noticeTypeCd;

	@JsonProperty("review_application_type_cd")
	private String reviewApplicationTypeCd;

	@JsonProperty("presentation_format_cd")
	private String presentationFormatCd;

	@JsonProperty("review_role_type_cd")
	private String reviewRoleTypeCd;

	@JsonProperty("first_given_nm")
	private String firstGivenNm;

	@JsonProperty("second_given_nm")
	private String secondGivenNm;

	@JsonProperty("surname_nm")
	private String surnameNm;

	@JsonProperty("phone_no")
	private String phoneNo;

	@JsonProperty("fax_no")
	private String faxNo;

	@JsonProperty("electronic_address_txt")
	private String electronicAddressTxt;

	@JsonProperty("manual_entry_yn")
	private String manualEntryYn;

	@JsonProperty("form_xml")
	private String formXml;

	@JsonProperty("hearing_dt")
	private String hearingDt;

	@JsonProperty("hearing_start_tm")
	private String hearingStartTm;

	@JsonProperty("hearing_end_tm")
	private String hearingEndTm;

	@JsonProperty("application_received_dt")
	private String applicationReceivedDt;

	@JsonProperty("receipt_number_txt")
	private String receiptNumberTxt;

	@JsonProperty("user_id")
	public String getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonProperty("prohibition_notice_no")
	public String getProhibitionNoticeNo() {
		return prohibitionNoticeNo;
	}

	@JsonProperty("prohibition_notice_no")
	public void setProhibitionNoticeNo(String prohibitionNoticeNo) {
		this.prohibitionNoticeNo = prohibitionNoticeNo;
	}

	@JsonProperty("notice_subject_cd")
	public String getNoticeSubjectCd() {
		return noticeSubjectCd;
	}

	@JsonProperty("notice_subject_cd")
	public void setNoticeSubjectCd(String noticeSubjectCd) {
		this.noticeSubjectCd = noticeSubjectCd;
	}

	@JsonProperty("notice_type_cd")
	public String getNoticeTypeCd() {
		return noticeTypeCd;
	}

	@JsonProperty("notice_type_cd")
	public void setNoticeTypeCd(String noticeTypeCd) {
		this.noticeTypeCd = noticeTypeCd;
	}

	@JsonProperty("review_application_type_cd")
	public String getReviewApplicationTypeCd() {
		return reviewApplicationTypeCd;
	}

	@JsonProperty("review_application_type_cd")
	public void setReviewApplicationTypeCd(String reviewApplicationTypeCd) {
		this.reviewApplicationTypeCd = reviewApplicationTypeCd;
	}

	@JsonProperty("presentation_format_cd")
	public String getPresentationFormatCd() {
		return presentationFormatCd;
	}

	@JsonProperty("presentation_format_cd")
	public void setPresentationFormatCd(String presentationFormatCd) {
		this.presentationFormatCd = presentationFormatCd;
	}

	@JsonProperty("review_role_type_cd")
	public String getReviewRoleTypeCd() {
		return reviewRoleTypeCd;
	}

	@JsonProperty("review_role_type_cd")
	public void setReviewRoleTypeCd(String reviewRoleTypeCd) {
		this.reviewRoleTypeCd = reviewRoleTypeCd;
	}

	@JsonProperty("first_given_nm")
	public String getFirstGivenNm() {
		return firstGivenNm;
	}

	@JsonProperty("first_given_nm")
	public void setFirstGivenNm(String firstGivenNm) {
		this.firstGivenNm = firstGivenNm;
	}

	@JsonProperty("second_given_nm")
	public String getSecondGivenNm() {
		return secondGivenNm;
	}

	@JsonProperty("second_given_nm")
	public void setSecondGivenNm(String secondGivenNm) {
		this.secondGivenNm = secondGivenNm;
	}

	@JsonProperty("surname_nm")
	public String getSurnameNm() {
		return surnameNm;
	}

	@JsonProperty("surname_nm")
	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}

	@JsonProperty("phone_no")
	public String getPhoneNo() {
		return phoneNo;
	}

	@JsonProperty("phone_no")
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@JsonProperty("fax_no")
	public String getFaxNo() {
		return faxNo;
	}

	@JsonProperty("fax_no")
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	@JsonProperty("electronic_address_txt")
	public String getElectronicAddressTxt() {
		return electronicAddressTxt;
	}

	@JsonProperty("electronic_address_txt")
	public void setElectronicAddressTxt(String electronicAddressTxt) {
		this.electronicAddressTxt = electronicAddressTxt;
	}

	@JsonProperty("manual_entry_yn")
	public String getManualEntryYn() {
		return manualEntryYn;
	}

	@JsonProperty("manual_entry_yn")
	public void setManualEntryYn(String manualEntryYn) {
		this.manualEntryYn = manualEntryYn;
	}

	@JsonProperty("form_xml")
	public String getFormXml() {
		return formXml;
	}

	@JsonProperty("form_xml")
	public void setFormXml(String formXml) {
		this.formXml = formXml;
	}

	@JsonProperty("hearing_dt")
	public String getHearingDt() {
		return hearingDt;
	}

	@JsonProperty("hearing_dt")
	public void setHearingDt(String hearingDt) {
		this.hearingDt = hearingDt;
	}

	@JsonProperty("hearing_start_tm")
	public String getHearingStartTm() {
		return hearingStartTm;
	}

	@JsonProperty("hearing_start_tm")
	public void setHearingStartTm(String hearingStartTm) {
		this.hearingStartTm = hearingStartTm;
	}

	@JsonProperty("hearing_end_tm")
	public String getHearingEndTm() {
		return hearingEndTm;
	}

	@JsonProperty("hearing_end_tm")
	public void setHearingEndTm(String hearingEndTm) {
		this.hearingEndTm = hearingEndTm;
	}

	@JsonProperty("application_received_dt")
	public String getApplicationReceivedDt() {
		return applicationReceivedDt;
	}

	@JsonProperty("application_received_dt")
	public void setApplicationReceivedDt(String applicationReceivedDt) {
		this.applicationReceivedDt = applicationReceivedDt;
	}

	@JsonProperty("receipt_number_txt")
	public String getReceiptNumberTxt() {
		return receiptNumberTxt;
	}

	@JsonProperty("receipt_number_txt")
	public void setReceiptNumberTxt(String receiptNumberTxt) {
		this.receiptNumberTxt = receiptNumberTxt;
	}

	@Override
	public String toString() {
		return MessageFormat.format(
				"ApplicationInfo : userId[{0}], prohibitionNoticeNo[{1}], noticeSubjectCd[{2}], noticeTypeCd[{3}], "
				+ "reviewApplicationTypeCd[{4}], presentationFormatCd[{5}], reviewRoleTypeCd[{6}], firstGivenNm[{7}], "
				+ "secondGivenNm[{8}], surnameNm[{9}], phoneNo[{10}], faxNo[{11}], electronicAddressTxt[{12}], "
				+ "manualEntryYn[{13}], formXml[{14}], hearingDt[{15}], hearingStartTm[{16}], hearingEndTm[{17}], "
				+ "applicationReceivedDt[{18}], receiptNumberTxt[{19}]]",
				this.userId, this.prohibitionNoticeNo, this.noticeSubjectCd, this.noticeTypeCd,
				this.reviewApplicationTypeCd, this.presentationFormatCd, this.reviewRoleTypeCd, this.firstGivenNm,
				this.secondGivenNm, this.surnameNm, this.phoneNo, this.faxNo, this.electronicAddressTxt,
				this.manualEntryYn, this.formXml, this.hearingDt, this.hearingStartTm, this.hearingEndTm,
				this.applicationReceivedDt, this.receiptNumberTxt);
	}

}
