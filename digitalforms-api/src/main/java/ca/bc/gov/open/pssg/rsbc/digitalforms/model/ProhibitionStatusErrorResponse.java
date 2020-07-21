package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status_code", "status_message" })
public class ProhibitionStatusErrorResponse {

	@JsonProperty("status_code")
	private String statusCode;
	@JsonProperty("status_message")
	private String statusMessage;

	@JsonProperty("status_code")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("status_code")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("status_message")
	public String getStatusMessage() {
		return statusMessage;
	}

	@JsonProperty("status_message")
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
