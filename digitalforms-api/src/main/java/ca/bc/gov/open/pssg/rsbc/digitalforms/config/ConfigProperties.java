package ca.bc.gov.open.pssg.rsbc.digitalforms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * Externalized configuration for easy access to properties
 * 
 * @author shaunmillargov
 *
 */
@ConfigurationProperties(prefix = "digitalforms")
public class ConfigProperties {

	private String basicAuthUser;
	private String basicAuthPassword;
	private String serviceApiVersion; 
	private boolean serviceSwaggerEnabled;
	private String ordsUserGuid; 

	public String getBasicAuthUser() {
		return basicAuthUser;
	}

	public void setBasicAuthUser(String basicAuthUser) {
		this.basicAuthUser = basicAuthUser;
	}

	public String getBasicAuthPassword() {
		return basicAuthPassword;
	}

	public void setBasicAuthPassword(String basicAuthPassword) {
		this.basicAuthPassword = basicAuthPassword;
	}

	public String getServiceApiVersion() {
		return serviceApiVersion;
	}

	public void setServiceApiVersion(String serviceApiVersion) {
		this.serviceApiVersion = serviceApiVersion;
	}

	public boolean isServiceSwaggerEnabled() {
		return serviceSwaggerEnabled;
	}

	public void setServiceSwaggerEnabled(boolean serviceSwaggerEnabled) {
		this.serviceSwaggerEnabled = serviceSwaggerEnabled;
	}

	public String getOrdsUserGuid() {
		return ordsUserGuid;
	}

	public void setOrdsUserGuid(String ordsUserGuid) {
		this.ordsUserGuid = ordsUserGuid;
	}
	
}
