package ca.bc.gov.open.pssg.rsbc.digitalforms.config;

import ca.bc.gov.open.pssg.rsbc.digitalforms.dao.VipswsDaoImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

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
	private String vipsApiendpoint;
	private String vipsApiuniversalid;
	private String vipsApiuserdisplayname;
	private String vipsApiguid;

	public String getVipsApiendpoint() {
		return vipsApiendpoint;
	}

	public void setVipsApiendpoint(String vipsApiendpoint) {
		this.vipsApiendpoint = vipsApiendpoint;
	}

	public String getVipsApiuniversalid() {
		return vipsApiuniversalid;
	}

	public void setVipsApiuniversalid(String vipsApiuniversalid) {
		this.vipsApiuniversalid = vipsApiuniversalid;
	}

	public String getVipsApiuserdisplayname() {
		return vipsApiuserdisplayname;
	}

	public void setVipsApiuserdisplayname(String vipsApiuserdisplayname) {
		this.vipsApiuserdisplayname = vipsApiuserdisplayname;
	}

	public String getVipsApiguid() {
		return vipsApiguid;
	}

	public void setVipsApiguid(String vipsApiguid) {
		this.vipsApiguid = vipsApiguid;
	}

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
