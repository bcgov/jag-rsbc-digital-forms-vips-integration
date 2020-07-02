package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "responseMessage", "timeDt" })
public class PingResponse {

	@JsonProperty("responseMessage")
	private String responseMessage;

	@JsonProperty("responseMessage")
	public String getResponseMessage() {
		return responseMessage;
	}

	@JsonProperty("responseMessage")
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@JsonProperty("timeDt")
	public String getTImeDt() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z", or Zulu to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		return df.format(new Date());
	}

}
