package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * VipsRegistrationCreate - mimics 'vips_registration_create_obj'
 * 
 * @author smillar
 *
 */
public class VipsRegistrationCreate {
	
	@JsonProperty("registrationRoleCd") //10 -R
	private String registrationRoleCd = null;
	
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
	private VipsLicenceCreate vipsLicenceObj = new VipsLicenceCreate();

	@JsonProperty("vipsAddressArray") // -R
	private List<VipsAddressCreateObj> vipsAddressArray = new ArrayList<VipsAddressCreateObj>();

	/**
	 * @return the registrationRoleCd
	 */
	public String getRegistrationRoleCd() {
		return registrationRoleCd;
	}

	/**
	 * @param registrationRoleCd the registrationRoleCd to set
	 */
	public void setRegistrationRoleCd(String registrationRoleCd) {
		this.registrationRoleCd = registrationRoleCd;
	}

	/**
	 * @return the firstGivenNm
	 */
	public String getFirstGivenNm() {
		return firstGivenNm;
	}

	/**
	 * @param firstGivenNm the firstGivenNm to set
	 */
	public void setFirstGivenNm(String firstGivenNm) {
		this.firstGivenNm = firstGivenNm;
	}

	/**
	 * @return the secondGivenNm
	 */
	public String getSecondGivenNm() {
		return secondGivenNm;
	}

	/**
	 * @param secondGivenNm the secondGivenNm to set
	 */
	public void setSecondGivenNm(String secondGivenNm) {
		this.secondGivenNm = secondGivenNm;
	}

	/**
	 * @return the thirdGivenNm
	 */
	public String getThirdGivenNm() {
		return thirdGivenNm;
	}

	/**
	 * @param thirdGivenNm the thirdGivenNm to set
	 */
	public void setThirdGivenNm(String thirdGivenNm) {
		this.thirdGivenNm = thirdGivenNm;
	}

	/**
	 * @return the surnameNm
	 */
	public String getSurnameNm() {
		return surnameNm;
	}

	/**
	 * @param surnameNm the surnameNm to set
	 */
	public void setSurnameNm(String surnameNm) {
		this.surnameNm = surnameNm;
	}

	/**
	 * @return the organizationNm
	 */
	public String getOrganizationNm() {
		return organizationNm;
	}

	/**
	 * @param organizationNm the organizationNm to set
	 */
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
	 * @return the vipsLicenceCreateObj
	 */
	public VipsLicenceCreate getVipsLicenceObj() {
		return vipsLicenceObj;
	}

	/**
	 * @param vipsLicenceCreateObj the vipsLicenceCreateObj to set
	 */
	public void setVipsLicenceCreateObj(VipsLicenceCreate vipsLicenceObj) {
		this.vipsLicenceObj = vipsLicenceObj;
	}

	/**
	 * @return the vipsAddressCreateArray
	 */
	@JsonProperty("vipsAddressArray") 
	public List<VipsAddressCreateObj> getVipsAddressArray() {
		return vipsAddressArray;
	}

	/**
	 * @param vipsAddressCreateArray the vipsAddressCreateArray to set
	 */
	@JsonProperty("vipsAddressArray") 
	public void setVipsAddressCreateArray(List<VipsAddressCreateObj> vipsAddressArray) {
		this.vipsAddressArray = vipsAddressArray;
	}
}
