package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Application Form Put and Patch Response Object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "applicationId", "createdTime", "updatedTime" })
public class ApplicationIdResponse {

	@JsonProperty("applicationId")
	private String applicationId;

	@JsonProperty("updatedTime")
	private String updatedTime;

	@JsonProperty("createdTime")
	private String createdTime;

	public ApplicationIdResponse(String applicationId, String createdTime, String updatedTime) {
		this.applicationId = applicationId;
		this.updatedTime = updatedTime;
		this.createdTime = createdTime;
	}

	@JsonProperty("applicationId")
	public String getApplicationId() {
		return applicationId;
	}

	@JsonProperty("applicationId")
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@JsonProperty("updatedTime")
	public String getUpdatedTime() {
		return updatedTime;
	}

	@JsonProperty("updatedTime")
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	@JsonProperty("createdTime")
	public String getCreatedTime() {
		return createdTime;
	}

	@JsonProperty("createdTime")
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}