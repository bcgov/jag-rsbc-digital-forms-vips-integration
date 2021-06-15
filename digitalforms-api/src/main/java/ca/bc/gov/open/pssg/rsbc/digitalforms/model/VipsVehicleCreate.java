package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * VipsVehicleCreate - mimics 'vips_vehicle_create_obj'
 * 
 * @author smillar
 *
 */
public class VipsVehicleCreate {
	
	//here

	@JsonProperty("vehicleIdentificationNo") //35
	private String vehicleIdentificationNo = null;

	@JsonProperty("registrationNo") // 15 -R
	private String registrationNo = null;

	@JsonProperty("licencePlateNo") // 10
	private String licencePlateNo = null;
	
	@JsonProperty("lpJurisdictionCd") // 10
	private String lpJurisdictionCd = null;
	
	@JsonProperty("lpDecalValidYy") // 4 
	private Long lpDecalValidYy = null;
	
	@JsonProperty("vehicleTypeCd") // 50 
	private String vehicleTypeCd = null;
	
	@JsonProperty("vehicleMakeTxt") // 50 
	private String vehicleMakeTxt = null;
	
	@JsonProperty("vehicleModelTxt") // 50 
	private String vehicleModelTxt = null;
	
	@JsonProperty("vehicleColourTxt") // 50
	private String vehicleColourTxt = null;
	
	@JsonProperty("vehicleStyleTxt") // 50
	private String vehicleStyleTxt = null;
	
	@JsonProperty("manufacturedYy") // 4
	private Long manufacturedYy = null;
	
	@JsonProperty("commercialMotorCarrierId") // 15
	private String commercialMotorCarrierId = null;
	
	@JsonProperty("nscJurisdictionTxt") // 20
	private String nscJurisdictionTxt = null;

//	@JsonProperty("jurisdictionCd") // 10 -R
//	private String jurisdictionCd = null;

	public String getVehicleIdentificationNo() {
		return vehicleIdentificationNo;
	}

	public void setVehicleIdentificationNo(String vehicleIdentificationNo) {
		this.vehicleIdentificationNo = vehicleIdentificationNo;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getLicencePlateNo() {
		return licencePlateNo;
	}

	public void setLicencePlateNo(String licencePlateNo) {
		this.licencePlateNo = licencePlateNo;
	}

	public String getLpJurisdictionCd() {
		return lpJurisdictionCd;
	}

	public void setLpJurisdictionCd(String lpJurisdictionCd) {
		this.lpJurisdictionCd = lpJurisdictionCd;
	}

	public Long getLpDecalValidYy() {
		return lpDecalValidYy;
	}

	public void setLpDecalValidYy(Long lpDecalValidYy) {
		this.lpDecalValidYy = lpDecalValidYy;
	}

	public String getVehicleTypeCd() {
		return vehicleTypeCd;
	}

	public void setVehicleTypeCd(String vehicleTypeCd) {
		this.vehicleTypeCd = vehicleTypeCd;
	}

	public String getVehicleMakeTxt() {
		return vehicleMakeTxt;
	}

	public void setVehicleMakeTxt(String vehicleMakeTxt) {
		this.vehicleMakeTxt = vehicleMakeTxt;
	}

	public String getVehicleModelTxt() {
		return vehicleModelTxt;
	}

	public void setVehicleModelTxt(String vehicleModelTxt) {
		this.vehicleModelTxt = vehicleModelTxt;
	}

	public String getVehicleColourTxt() {
		return vehicleColourTxt;
	}

	public void setVehicleColourTxt(String vehicleColourTxt) {
		this.vehicleColourTxt = vehicleColourTxt;
	}

	public String getVehicleStyleTxt() {
		return vehicleStyleTxt;
	}

	public void setVehicleStyleTxt(String vehicleStyleTxt) {
		this.vehicleStyleTxt = vehicleStyleTxt;
	}

	public Long getManufacturedYy() {
		return manufacturedYy;
	}

	public void setManufacturedYy(Long manufacturedYy) {
		this.manufacturedYy = manufacturedYy;
	}

	public String getCommercialMotorCarrierId() {
		return commercialMotorCarrierId;
	}

	public void setCommercialMotorCarrierId(String commercialMotorCarrierId) {
		this.commercialMotorCarrierId = commercialMotorCarrierId;
	}

	public String getNscJurisdictionTxt() {
		return nscJurisdictionTxt;
	}

	public void setNscJurisdictionTxt(String nscJurisdictionTxt) {
		this.nscJurisdictionTxt = nscJurisdictionTxt;
	}

//	public String getJurisdictionCd() {
//		return jurisdictionCd;
//	}
//
//	public void setJurisdictionCd(String jurisdictionCd) {
//		this.jurisdictionCd = jurisdictionCd;
//	}

}
