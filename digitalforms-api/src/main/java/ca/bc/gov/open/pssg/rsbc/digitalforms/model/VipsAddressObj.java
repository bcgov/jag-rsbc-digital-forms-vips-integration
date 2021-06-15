package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * VipsAddressObj - mimics 'vips_address_obj'
 * 
 * @author smillar
 *
 */
public class VipsAddressObj {
	
	@JsonProperty("registrationId") // ?  
	private Long registrationId = null;
	
	@JsonProperty("registrationAddressSeqNo") // ?  
	private Long registrationAddressSeqNo = null;
	
	@JsonProperty("registrationAddressTypeCd") // 10  
	private String registrationAddressTypeCd = null;
	
	@JsonProperty("addressFirstLineTxt") // 30  
	private String addressFirstLineTxt = null;
	
	@JsonProperty("addressSecondLineTxt") // 30  
	private String addressSecondLineTxt = null;
	
	@JsonProperty("addressThirdLineTxt") // 30  
	private String addressThirdLineTxt = null;
	
	@JsonProperty("additionalDeliveryLineTxt") // 100
	private String additionalDeliveryLineTxt = null;
	
	@JsonProperty("provinceCd") // 2
	private String provinceCd = null;
	
	@JsonProperty("cityNm") // 35
	private String cityNm = null;

	@JsonProperty("countryNm") // 3
	private String countryNm = null;
	
	@JsonProperty("postalCodeTxt") // 10 
	private String postalCodeTxt = null;

	/**
	 * @return the registrationId
	 */
	public Long getRegistrationId() {
		return registrationId;
	}

	/**
	 * @param registrationId the registrationId to set
	 */
	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}

	/**
	 * @return the registrationAddressSeqNo
	 */
	public Long getRegistrationAddressSeqNo() {
		return registrationAddressSeqNo;
	}

	/**
	 * @param registrationAddressSeqNo the registrationAddressSeqNo to set
	 */
	public void setRegistrationAddressSeqNo(Long registrationAddressSeqNo) {
		this.registrationAddressSeqNo = registrationAddressSeqNo;
	}

	/**
	 * @return the registrationAddressTypeCd
	 */
	public String getRegistrationAddressTypeCd() {
		return registrationAddressTypeCd;
	}

	/**
	 * @param registrationAddressTypeCd the registrationAddressTypeCd to set
	 */
	public void setRegistrationAddressTypeCd(String registrationAddressTypeCd) {
		this.registrationAddressTypeCd = registrationAddressTypeCd;
	}

	/**
	 * @return the addressFirstLineTxt
	 */
	public String getAddressFirstLineTxt() {
		return addressFirstLineTxt;
	}

	/**
	 * @param addressFirstLineTxt the addressFirstLineTxt to set
	 */
	public void setAddressFirstLineTxt(String addressFirstLineTxt) {
		this.addressFirstLineTxt = addressFirstLineTxt;
	}

	/**
	 * @return the addressSecondLineTxt
	 */
	public String getAddressSecondLineTxt() {
		return addressSecondLineTxt;
	}

	/**
	 * @param addressSecondLineTxt the addressSecondLineTxt to set
	 */
	public void setAddressSecondLineTxt(String addressSecondLineTxt) {
		this.addressSecondLineTxt = addressSecondLineTxt;
	}

	/**
	 * @return the addressThirdLineTxt
	 */
	public String getAddressThirdLineTxt() {
		return addressThirdLineTxt;
	}

	/**
	 * @param addressThirdLineTxt the addressThirdLineTxt to set
	 */
	public void setAddressThirdLineTxt(String addressThirdLineTxt) {
		this.addressThirdLineTxt = addressThirdLineTxt;
	}

	/**
	 * @return the additionalDeliveryLineTxt
	 */
	public String getAdditionalDeliveryLineTxt() {
		return additionalDeliveryLineTxt;
	}

	/**
	 * @param additionalDeliveryLineTxt the additionalDeliveryLineTxt to set
	 */
	public void setAdditionalDeliveryLineTxt(String additionalDeliveryLineTxt) {
		this.additionalDeliveryLineTxt = additionalDeliveryLineTxt;
	}

	/**
	 * @return the provinceCd
	 */
	public String getProvinceCd() {
		return provinceCd;
	}

	/**
	 * @param provinceCd the provinceCd to set
	 */
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}

	/**
	 * @return the cityNm
	 */
	public String getCityNm() {
		return cityNm;
	}

	/**
	 * @param cityNm the cityNm to set
	 */
	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	/**
	 * @return the countryNm
	 */
	public String getCountryNm() {
		return countryNm;
	}

	/**
	 * @param countryNm the countryNm to set
	 */
	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}

	/**
	 * @return the postalCodeTxt
	 */
	public String getPostalCodeTxt() {
		return postalCodeTxt;
	}

	/**
	 * @param postalCodeTxt the postalCodeTxt to set
	 */
	public void setPostalCodeTxt(String postalCodeTxt) {
		this.postalCodeTxt = postalCodeTxt;
	}
	
}
