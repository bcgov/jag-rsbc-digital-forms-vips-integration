package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 
 * VipsLicenceCreate - mimics 'vips_licence_create_obj'
 * 
 * @author smillar
 *
 */
public class VipsLicenceCreate {

	@JsonProperty("driverLicenceNo") // 20
	private String driverLicenceNo = null;

	@JsonProperty("birthDt")
	private Date birthDt = null;

	@JsonProperty("dlJurisdictionCd") // 10
	private String dlJurisdictionCd = null;

	public String getDriverLicenceNo() {
		return driverLicenceNo;
	}

	public void setDriverLicenceNo(String driverLicenceNo) {
		this.driverLicenceNo = driverLicenceNo;
	}

	public Date getBirthDt() {
		return birthDt;
	}

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
