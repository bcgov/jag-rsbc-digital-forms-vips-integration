package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * JSON Enveloped Response Type (Variable object payload) 
 * 
 * @author shaunmillargov
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "resp", "data", "error" })
public class JSONResponse<T> {
	
	@JsonProperty("data")
	private T data; 

	@JsonProperty("resp")
	private String resp = "Success"; 
	
	@JsonProperty("error")
	private JSONError error;
	
	public JSONResponse(T data){
		this.data = data; 
	};
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonProperty("resp")
	public String getResp() {
		return resp;
	}

	@JsonProperty("resp")
	public void setResp(String resp) {
		this.resp = resp;
	}

	@JsonProperty("error")
	public JSONError getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(JSONError error) {
		this.resp = "Fail"; 
		this.error = error;
	}

}
