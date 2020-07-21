package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;

/**
 * 
 * Supporting object for the IRPStatusInfoResponse.  
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "effectiveDate", "driversLicenceSeizedYN", "surnameNm", "iRPStatus", "cancelledYN" })
public class IRPInfo {

	@JsonProperty("effectiveDate")
	private String effectiveDate;
	@JsonProperty("driversLicenceSeizedYN")
	private String driversLicenceSeizedYN;
	@JsonProperty("surnameNm")
	private String surnameNm;
	@JsonProperty("iRPStatus")
	private String iRPStatus;
	@JsonProperty("cancelledYN")
	private String cancelledYN;
	
	public IRPInfo(String effectiveDate, String driversLicenceSeizedYN, String surnameNm, String iRPStatus, String cancelledYN) {
		this.effectiveDate = effectiveDate; 
		this.driversLicenceSeizedYN = driversLicenceSeizedYN;
		this.surnameNm = surnameNm;
		this.iRPStatus = iRPStatus;
		this.cancelledYN = cancelledYN;
	}

	/*
	 * public IRPInfo(VipsProhibitionStatusResponse ordsResp) { this.effectiveDate =
	 * ordsResp.getStatus().getEffectiveDate(); this.driversLicenceSeizedYN =
	 * ordsResp.getStatus().getDriverLicenceSeizedYn(); this.surnameNm =
	 * ordsResp.getStatus().getDriverLastName(); this.iRPStatus =
	 * ordsResp.getStatus().getReviewStatus(); this.cancelledYN =
	 * ordsResp.getStatus().getCancelledYn(); }
	 */

	@JsonProperty("effectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@JsonProperty("effectiveDate")
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@JsonProperty("driversLicenceSeizedYN")
	public String getDriversLicenceSeizedYN() {
		return driversLicenceSeizedYN;
	}

	@JsonProperty("driversLicenceSeizedYN")
	public void setDriversLicenceSeizedYN(String driversLicenceSeizedYN) {
		this.driversLicenceSeizedYN = driversLicenceSeizedYN;
	}

	@JsonProperty("surnameNm")
	public String getSurnameNm() {
		return surnameNm;
	}

	@JsonProperty("surnameNm")
	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}

	@JsonProperty("iRPStatus")
	public String getIRPStatus() {
		return iRPStatus;
	}

	@JsonProperty("iRPStatus")
	public void setIRPStatus(String iRPStatus) {
		this.iRPStatus = iRPStatus;
	}

	@JsonProperty("cancelledYN")
	public String getCancelledYN() {
		return cancelledYN;
	}

	@JsonProperty("cancelledYN")
	public void setCancelledYN(String cancelledYN) {
		this.cancelledYN = cancelledYN;
	}

}
