package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * VipsProhibitionCreate - mimics 'vips_prohibition_create_obj'
 * 
 * @author smillar
 *
 */
public class VipsProhibitionCreate {

	@JsonProperty("prohibitionNoticeNo") //12 -R
	private String prohibitionNoticeNo = null;

	@JsonProperty("policeDetatchmentId") //15 -R
	private Long policeDetatchmentId = null;

	@JsonProperty("driverLicenceSeizedYN") //1 -R
	private String driverLicenceSeizedYN = null;

	@JsonProperty("noticeSubjectCd") //10 -R
	private String noticeSubjectCd = null;

	@JsonProperty("noticeTypeCd") //10 -R
	private String noticeTypeCd = null;

	@JsonProperty("noticeServedDt") // -R
	private Date noticeServedDt = null;

	@JsonProperty("streetDetailsTxt") //4000
	private String streetDetailsTxt = null;

	@JsonProperty("incidentDtm") 
	private Date incidentDtm = null;

	@JsonProperty("policeFileNo") //15
	private String policeFileNo = null;

	@JsonProperty("effectiveDt") // -R
	private Date effectiveDt = null;
	
	@JsonProperty("expiryDt") 
	private Date expiryDt = null;

	@JsonProperty("policeOfficerNm") //60
	private String policeOfficerNm = null;
	
	@JsonProperty("policeOfficerNo") //30
	private String policeOfficerNo = null;

	@JsonProperty("tempDlEffectiveDt")
	private Date tempDlEffectiveDt = null;

	@JsonProperty("tempDlExpiryDt")
	private Date tempDlExpiryDt = null;
	
	@JsonProperty("cityNm") //30
	private String cityNm = null;
	
	@JsonProperty("provinceCd") //2
	private String provinceCd = null;
	
	@JsonProperty("originalCauseCd") //10 -R
	private String originalCauseCd = null;
	
	@JsonProperty("dreEvaluationCds") 
	private List<String> dreEvaluationCds = new ArrayList<String>();

	public String getProhibitionNoticeNo() {
		return prohibitionNoticeNo;
	}

	public void setProhibitionNoticeNo(String prohibitionNoticeNo) {
		this.prohibitionNoticeNo = prohibitionNoticeNo;
	}

	public Long getPoliceDetatchmentId() {
		return policeDetatchmentId;
	}

	public void setPoliceDetatchmentId(Long policeDetatchmentId) {
		this.policeDetatchmentId = policeDetatchmentId;
	}

	public String getDriverLicenceSeizedYN() {
		return driverLicenceSeizedYN;
	}

	public void setdriverLicenceSeizedYN(String driverLicenceSeizedYN) {
		this.driverLicenceSeizedYN = driverLicenceSeizedYN;
	}

	public String getNoticeSubjectCd() {
		return noticeSubjectCd;
	}

	public void setNoticeSubjectCd(String noticeSubjectCd) {
		this.noticeSubjectCd = noticeSubjectCd;
	}

	public String getNoticeTypeCd() {
		return noticeTypeCd;
	}

	public void setNoticeTypeCd(String noticeTypeCd) {
		this.noticeTypeCd = noticeTypeCd;
	}

	public Date getNoticeServedDt() {
		return noticeServedDt;
	}

	public void setNoticeServedDt(Date noticeServedDt) {
		this.noticeServedDt = noticeServedDt;
	}

	public String getStreetDetailsTxt() {
		return streetDetailsTxt;
	}

	public void setStreetDetailsTxt(String streetDetailsTxt) {
		this.streetDetailsTxt = streetDetailsTxt;
	}

	public Date getIncidentDtm() {
		return incidentDtm;
	}

	public void setIncidentDtm(Date incidentDtm) {
		this.incidentDtm = incidentDtm;
	}

	public String getPoliceFileNo() {
		return policeFileNo;
	}

	public void setPoliceFileNo(String policeFileNo) {
		this.policeFileNo = policeFileNo;
	}

	public Date getEffectiveDt() {
		return effectiveDt;
	}

	public void setEffectiveDt(Date effectiveDt) {
		this.effectiveDt = effectiveDt;
	}

	public Date getExpiryDt() {
		return expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public String getPoliceOfficerNm() {
		return policeOfficerNm;
	}

	public void setPoliceOfficerNm(String policeOfficerNm) {
		this.policeOfficerNm = policeOfficerNm;
	}

	public String getPoliceOfficerNo() {
		return policeOfficerNo;
	}

	public void setPoliceOfficerNo(String policeOfficerNo) {
		this.policeOfficerNo = policeOfficerNo;
	}

	public Date getTempDlEffectiveDt() {
		return tempDlEffectiveDt;
	}

	public void setTempDlEffectiveDt(Date tempDlEffectiveDt) {
		this.tempDlEffectiveDt = tempDlEffectiveDt;
	}

	public Date getTempDlExpiryDt() {
		return tempDlExpiryDt;
	}

	public void setTempDlExpiryDt(Date tempDlExpiryDt) {
		this.tempDlExpiryDt = tempDlExpiryDt;
	}

	public String getCityNm() {
		return cityNm;
	}

	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	public String getProvinceCd() {
		return provinceCd;
	}

	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}

	public String getOriginalCauseCd() {
		return originalCauseCd;
	}

	public void setOriginalCauseCd(String originalCauseCd) {
		this.originalCauseCd = originalCauseCd;
	}

	public List<String> getDreEvaluationCds() {
		return dreEvaluationCds;
	}
	
	public void setDreEvaluations(List<String> dreEvaluationCds) {
		this.dreEvaluationCds = dreEvaluationCds;
	}
	
}
