package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.bc.gov.open.pssg.rsbc.digitalforms.util.TimeDateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * VipsImpoundObj - mimics 'vips_impoundment_obj'
 * 
 * @author smillar
 *
 */
public class VipsImpoundObj {
	
	@JsonProperty("impoundmentId") //15
	private Long impoundmentId = null;
	
	@JsonProperty("vehicleId") //15
	private Long vehicleId = null;

	@JsonProperty("policeDetatchmentId") //15
	private Long policeDetatchmentId = null;

	@JsonProperty("impoundLotOperatorId") //15
	private Long impoundLotOperatorId = null;
		
	//@JsonProperty("jurisdictionCd") //10
	//private String jurisdictionCd = null;
	
	@JsonProperty("noticeSubjectCd") //10
	private String noticeSubjectCd = null;

	@JsonProperty("noticeTypeCd") //10
	private String noticeTypeCd = null;
	
	@JsonProperty("impoundmentNoticeNo") //12
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
	
	@JsonProperty("policeFileNo") // 15 
	private String policeFileNo = null;
	
	@JsonProperty("projectedReleaseDt") 
	private Date projectedReleaseDt = null;
	
	@JsonProperty("icbcTransmissionCd") 
	private String icbcTransmissionCd = null;
	
	@JsonProperty("cancelled")
	private Boolean cancelled; 

	@JsonProperty("originalCauseCds") 
	private List<String> originalCauseCds = null;  
	
	@JsonProperty("vehicle") 
	private VipsVehicleObj vipsVehicleObj = new VipsVehicleObj();
	
	@JsonProperty("registrations") 
	private List<VipsRegistrationObj> vipsRegistrationArr = new ArrayList<VipsRegistrationObj>();
	
	@JsonProperty("documents") 
	private List<VipsDocumentObj> vipsDocumentArr = new ArrayList<VipsDocumentObj>();
	
	public Long getImpoundmentId() {
		return impoundmentId;
	}

	public void setImpoundmentId(Long impoundmentId) {
		this.impoundmentId = impoundmentId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VipsVehicleObj getVipsVehicleObj() {
		return vipsVehicleObj;
	}

	public void setVipsVehicleObj(VipsVehicleObj vipsVehicleObj) {
		this.vipsVehicleObj = vipsVehicleObj;
	}

	public List<VipsRegistrationObj> getVipsRegistrationArr() {
		return vipsRegistrationArr;
	}

	public void setVipsRegistrationArr(List<VipsRegistrationObj> vipsRegistrationArr) {
		this.vipsRegistrationArr = vipsRegistrationArr;
	}

	public List<VipsDocumentObj> getVipsDocumentArr() {
		return vipsDocumentArr;
	}

	public void setVipsDocumentArr(List<VipsDocumentObj> vipsDocumentArr) {
		this.vipsDocumentArr = vipsDocumentArr;
	}

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

	/**
	 * @return the impoundmentDt
	 */
	public String getImpoundmentDt() {
		if ( null != impoundmentDt) {
			return TimeDateUtils.getISO8601FromDate(impoundmentDt);
		} else {
			return null;
		}
	}

	/**
	 * @param impoundmentDt the impoundmentDt to set
	 */
	public void setImpoundmentDt(Date impoundmentDt) {
		this.impoundmentDt = impoundmentDt;
	}

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

	/**
	 * @return the policeFileNo
	 */
	public String getPoliceFileNo() {
		return policeFileNo;
	}

	/**
	 * @param policeFileNo the policeFileNo to set
	 */
	public void setPoliceFileNo(String policeFileNo) {
		this.policeFileNo = policeFileNo;
	}

	public String getProjectedReleaseDt() {
		if ( null != projectedReleaseDt) {
			return TimeDateUtils.getISO8601FromDate(projectedReleaseDt);
		} else {
			return null;
		}
	}

	public void setProjectedReleaseDt(Date projectedReleaseDt) {
		this.projectedReleaseDt = projectedReleaseDt;
	}

	public String getIcbcTransmissionCd() {
		return icbcTransmissionCd;
	}

	public void setIcbcTransmissionCd(String icbcTransmissionCd) {
		this.icbcTransmissionCd = icbcTransmissionCd;
	}

	
	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
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
