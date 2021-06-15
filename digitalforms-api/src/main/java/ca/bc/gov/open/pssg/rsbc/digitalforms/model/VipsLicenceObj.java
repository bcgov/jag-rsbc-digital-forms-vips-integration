package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.Date;

import ca.bc.gov.open.pssg.rsbc.digitalforms.util.TimeDateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * VipsLicenceObj - mimics 'vips_licence_obj'
 * 
 * @author smillar
 *
 */
public class VipsLicenceObj {

	@JsonProperty("licenceId") // 15
	private Long licenceId = null;

	@JsonProperty("driverLicenceNo") // 20
	private String driverLicenceNo = null;

	@JsonProperty("birthDt")
	private Date birthDt = null;

	@JsonProperty("dlJurisdictionCd") // 1
	private String dlJurisdictionCd = null;
	
	public Long getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Long licenceId) {
		this.licenceId = licenceId;
	}

	public String getDriverLicenceNo() {
		return driverLicenceNo;
	}

	public void setDriverLicenceNo(String driverLicenceNo) {
		this.driverLicenceNo = driverLicenceNo;
	}

	public String getBirthDt() {
		if ( null !=  birthDt) {
			return TimeDateUtils.getISO8601FromDate(birthDt);
		} else {
			return null;
		}
	}
	
//	/**
//	 * @return the birthDt
//	 */
//	public Date getBirthDt() {
//		return birthDt;
//	}

	public void setBirthDt(Date birthDt) {
		this.birthDt = birthDt;
	}

	public String getDlJurisdictionCd() {
		return dlJurisdictionCd;
	}

	public void setDlJurisdictionCd(String dlJurisdictionCd) {
		this.dlJurisdictionCd = dlJurisdictionCd;
	}

}
