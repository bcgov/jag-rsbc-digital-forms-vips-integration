package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * VipsImpoundmentCreate - mimics 'vips_impoundment_create_obj'
 * 
 * @author smillar
 *
 */
public class VipsImpoundmentCreate {

	@JsonProperty("policeDetatchmentId") //15 -R
	private Long policeDetatchmentId = null;

	@JsonProperty("impoundLotOperatorId") //15
	private Long impoundLotOperatorId = null;
	
	//@JsonProperty("jurisdictionCd") //10 -R
	//private String jurisdictionCd = null;
	
	@JsonProperty("noticeSubjectCd") //10 -R
	private String noticeSubjectCd = null;

	@JsonProperty("noticeTypeCd") //10 -R
	private String noticeTypeCd = null;
	
	@JsonProperty("impoundmentNoticeNo") //21 -R
	private String impoundmentNoticeNo = null;
	
	@JsonProperty("impoundmentDt") 
	private Date impoundmentDt = null;

	@JsonProperty("dlJurisdictionCd") // 100
	private String dlJurisdictionCd = null;
	  
	@JsonProperty("seizureLocationTxt") // 4000
	private String seizureLocationTxt = null;
	
	@JsonProperty("driverLicenceNo") // 20 
	private String driverLicenceNo = null;
	
	@JsonProperty("policeOfficerNo") // 30 
	private String policeOfficerNo = null;
	
	@JsonProperty("policeOfficerNm") // 100 
	private String policeOfficerNm = null;
	
	@JsonProperty("policeFileNo") // 15 -R 
	private String policeFileNo = null;
	
	@JsonProperty("projectedReleaseDt") 
	private Date projectedReleaseDt = null;

	@JsonProperty("originalCauseCds") 
	private List<String> originalCauseCds = null; // 10 -R 

	public Long getPoliceDetatchmentId() {
		return policeDetatchmentId;
	}

	public void setPoliceDetatchmentId(Long policeDetatchmentId) {
		this.policeDetatchmentId = policeDetatchmentId;
	}

	public Long getImpoundLotOperatorId() {
		return impoundLotOperatorId;
	}

	public void setImpoundLotOperatorId(Long impoundLotOperatorId) {
		this.impoundLotOperatorId = impoundLotOperatorId;
	}

//	public String getJurisdictionCd() {
//		return jurisdictionCd;
//	}
//
//	public void setJurisdictionCd(String jurisdictionCd) {
//		this.jurisdictionCd = jurisdictionCd;
//	}

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

	public String getImpoundmentNoticeNo() {
		return impoundmentNoticeNo;
	}

	public void setImpoundmentNoticeNo(String impoundmentNoticeNo) {
		this.impoundmentNoticeNo = impoundmentNoticeNo;
	}

	public Date getImpoundmentDt() {
		return impoundmentDt;
	}

	public void setImpoundmentDt(Date impoundmentDt) {
		this.impoundmentDt = impoundmentDt;
	}

//	public Date getImpoundmentReleaseDt() {
//		return impoundmentReleaseDt;
//	}
//
//	public void setImpoundmentReleaseDt(Date impoundmentReleaseDt) {
//		this.impoundmentReleaseDt = impoundmentReleaseDt;
//	}

	public String getDlJurisdictionCd() {
		return dlJurisdictionCd;
	}

	public void setDlJurisdictionCd(String dlJurisdictionCd) {
		this.dlJurisdictionCd = dlJurisdictionCd;
	}

	public String getSeizureLocationTxt() {
		return seizureLocationTxt;
	}

	public void setSeizureLocationTxt(String seizureLocationTxt) {
		this.seizureLocationTxt = seizureLocationTxt;
	}

	public String getDriverLicenceNo() {
		return driverLicenceNo;
	}

	public void setDriverLicenceNo(String driverLicenceNo) {
		this.driverLicenceNo = driverLicenceNo;
	}

	public String getPoliceOfficerNo() {
		return policeOfficerNo;
	}

	public void setPoliceOfficerNo(String policeOfficerNo) {
		this.policeOfficerNo = policeOfficerNo;
	}

	public String getPoliceOfficerNm() {
		return policeOfficerNm;
	}

	public void setPoliceOfficerNm(String policeOfficerNm) {
		this.policeOfficerNm = policeOfficerNm;
	}
	
	public String getPoliceFileNo() {
		return policeFileNo;
	}

	public void setPoliceFileNo(String policeFileNo) {
		this.policeFileNo = policeFileNo;
	}

//	public Date getClaimedDt() {
//		return claimedDt;
//	}
//
//	public void setClaimedDt(Date claimedDt) {
//		this.claimedDt = claimedDt;
//	}

	public Date getProjectedReleaseDt() {
		return projectedReleaseDt;
	}

	public void setProjectedReleaseDt(Date projectedReleaseDt) {
		this.projectedReleaseDt = projectedReleaseDt;
	}

	public List<String> getOriginalCauseCds() {
		if (null == this.originalCauseCds) {
			this.originalCauseCds = new ArrayList<String>();
		}
		return originalCauseCds;
	}

	public void setOriginalCauseCds(List<String> originalCauseCds) {
		this.originalCauseCds = originalCauseCds;
	}

}
