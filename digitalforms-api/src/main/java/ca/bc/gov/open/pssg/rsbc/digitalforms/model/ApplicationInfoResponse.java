package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;


/**
 * 
 * Application Form Get Response Object
 * 
 * @author sivakaruna
 *
 */
@JacksonXmlRootElement(localName = "applicationInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "prohibitionNoticeNo", "noticeTypeCd", "reviewApplnTypeCd" })
public class ApplicationInfoResponse {

	@JsonUnwrapped
	private ApplicationFormData formData;

	@JsonProperty("prohibitionNoticeNo")
	private String prohibitionNoticeNo;
	@JsonProperty("noticeTypeCd")
	private String noticeTypeCd;
	@JsonProperty("reviewApplnTypeCd")
	private String reviewApplnTypeCd;

	public ApplicationInfoResponse(DigitalFormGetResponse response) {
		ApplicationFormData applicationFormData = new ApplicationFormData();
		applicationFormData.setEmail(response.getElectronicAddressTxt());
		applicationFormData.setFaxNo(response.getFaxNo());
		applicationFormData.setFirstGivenNm(response.getFirstGivenNm());
		applicationFormData.setManualEntryYN(response.getManualEntryYn());
		applicationFormData.setNoticeSubjectCd(response.getNoticeSubjectCd());
		applicationFormData.setPhoneNo(response.getPhoneNo());
		applicationFormData.setPresentationTypeCd(response.getPresentationFormatCd());
		applicationFormData.setReviewRoleTypeCd(response.getReviewRoleTypeCd());
		applicationFormData.setSecondGivenNm(response.getSecondGivenNm());
		applicationFormData.setSurnameNm(response.getSurnameNm());
		this.setFormData(applicationFormData);
		this.setNoticeTypeCd(response.getNoticeTypeCd());
		this.setProhibitionNoticeNo(response.getProhibitionNoticeNo());
		this.setReviewApplnTypeCd(response.getReviewApplicationTypeCd());
	}

	public ApplicationFormData getFormData() {
		return formData;
	}

	public void setFormData(ApplicationFormData formData) {
		this.formData = formData;
	}

	@JsonProperty("prohibitionNoticeNo")
	public String getProhibitionNoticeNo() {
		return prohibitionNoticeNo;
	}

	@JsonProperty("prohibitionNoticeNo")
	public void setProhibitionNoticeNo(String prohibitionNoticeNo) {
		this.prohibitionNoticeNo = prohibitionNoticeNo;
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
}
