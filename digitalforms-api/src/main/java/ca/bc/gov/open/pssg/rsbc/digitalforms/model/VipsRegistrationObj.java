package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the complex type, vips_registration_obj. 
 * 
 * @author smillar
 *
 */
public class VipsRegistrationObj {
	
	@JsonProperty("registrationId") //15 
	private Long registrationId = null;
	
	@JsonProperty("registrationRoleCd") //10 
	private String registrationRoleCd = null;
	
	@JsonProperty("vehicleId") //15 
	private Long vehicleId = null;
	
	@JsonProperty("licenceId") //15 
	private Long licenceId = null;

	@JsonProperty("firstGivenNm") // 25 
	private String firstGivenNm = null;

	@JsonProperty("secondGivenNm") // 25
	private String secondGivenNm = null;
	
	@JsonProperty("thirdGivenNm") // 25
	private String thirdGivenNm = null;
	
	@JsonProperty("surnameNm") // 25
	private String surnameNm = null;
	
	@JsonProperty("organizationNm") // 100 
	private String organizationNm = null;
	
	@JsonProperty("icbcEnterpriseId") // 50 
	private String icbcEnterpriseId = null;
	
	@JsonProperty("dataSourceCd") // 10 
	private String dataSourceCd = null;
	
	@JsonProperty("vipsLicenceObj") // -R
	private VipsLicenceObj vipsLicenceObj = new VipsLicenceObj();
	
	@JsonProperty("vipsAddressArray") 
	private List<VipsAddressObj> vipsAddressArray = new ArrayList<VipsAddressObj>();

	public  VipsRegistrationObj(){}
	
	public Long getRegistrationId() {
		return registrationId;
	}


	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}


	public String getRegistrationRoleCd() {
		return registrationRoleCd;
	}


	public void setRegistrationRoleCd(String registrationRoleCd) {
		this.registrationRoleCd = registrationRoleCd;
	}


	public Long getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}


	public Long getLicenceId() {
		return licenceId;
	}


	public void setLicenceId(Long licenceId) {
		this.licenceId = licenceId;
	}


	public String getFirstGivenNm() {
		return firstGivenNm;
	}


	public void setFirstGivenNm(String firstGivenNm) {
		this.firstGivenNm = firstGivenNm;
	}


	public String getSecondGivenNm() {
		return secondGivenNm;
	}


	public void setSecondGivenNm(String secondGivenNm) {
		this.secondGivenNm = secondGivenNm;
	}


	public String getThirdGivenNm() {
		return thirdGivenNm;
	}


	public void setThirdGivenNm(String thirdGivenNm) {
		this.thirdGivenNm = thirdGivenNm;
	}


	public String getSurnameNm() {
		return surnameNm;
	}


	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}


	public String getOrganizationNm() {
		return organizationNm;
	}


	public void setOrganizationNm(String organizationNm) {
		this.organizationNm = organizationNm;
	}


	/**
	 * @return the icbcEnterpriseId
	 */
	public String getIcbcEnterpriseId() {
		return icbcEnterpriseId;
	}


	/**
	 * @param icbcEnterpriseId the icbcEnterpriseId to set
	 */
	public void setIcbcEnterpriseId(String icbcEnterpriseId) {
		this.icbcEnterpriseId = icbcEnterpriseId;
	}


	/**
	 * @return the dataSourceCd
	 */
	public String getDataSourceCd() {
		return dataSourceCd;
	}


	/**
	 * @param dataSourceCd the dataSourceCd to set
	 */
	public void setDataSourceCd(String dataSourceCd) {
		this.dataSourceCd = dataSourceCd;
	}

	/**
	 * @return the vipsLicenceObj
	 */
	public VipsLicenceObj getVipsLicenceObj() {
		return vipsLicenceObj;
	}

	/**
	 * @param vipsLicenceObj the vipsLicenceObj to set
	 */
	public void setVipsLicenceObj(VipsLicenceObj vipsLicenceObj) {
		this.vipsLicenceObj = vipsLicenceObj;
	}

	/**
	 * @return the vipsAddressArray
	 */
	public List<VipsAddressObj> getVipsAddressArray() {
		return vipsAddressArray;
	}


	/**
	 * @param vipsAddressArray the vipsAddressArray to set
	 */
	public void setVipsAddressArray(List<VipsAddressObj> vipsAddressArray) {
		this.vipsAddressArray = vipsAddressArray;
	}
	
}
