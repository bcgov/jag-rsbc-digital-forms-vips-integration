package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "responseMessage", "timeDt" })
public class PingResponse {

	@JsonProperty("responseMessage")
	private JSONObject responseMessage;

	@JsonProperty("responseMessage")
	public JSONObject getResponseMessage() {
		return responseMessage;
	}

	@JsonProperty("responseMessage")
	public void setResponseMessage(JSONObject responseMessage) {
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
