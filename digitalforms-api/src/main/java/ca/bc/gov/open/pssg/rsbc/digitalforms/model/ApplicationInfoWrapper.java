package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Application form operations wrapper object
 * 
 * @author sivakaruna
 *
 */
public class ApplicationInfoWrapper<T> {
	
	@JsonProperty("applicationInfo")
	@Valid
	private T applicationInfo;
	
	public ApplicationInfoWrapper () {}
	
	public ApplicationInfoWrapper(T applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

	public T getApplicationInfo() {
		return applicationInfo;
	}

	public void setApplicationInfo(T applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

}
