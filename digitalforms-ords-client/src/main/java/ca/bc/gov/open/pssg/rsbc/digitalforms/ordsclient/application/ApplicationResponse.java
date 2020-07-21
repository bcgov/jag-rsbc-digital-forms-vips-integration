package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormGetResponse;

/**
 *
 * Represents the Application Response
 *
 * @author sivakaruna
 *
 */
@JacksonXmlRootElement(localName = "applicationResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApplicationResponse {

	@JsonProperty("applicationId")
	private String applicationId;
	
	@JsonProperty("applicationInfo")
	private DigitalFormGetResponse applicationInfo;
	
	@JsonProperty("respCode")
	private int respCode;
	
	@JsonProperty("respMsg")
	private String respMsg;
	
	@JsonProperty("updatedTime")
	private String updatedTime;
	
	@JsonProperty("createdTime")
	private String createdTime;

	private ApplicationResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private ApplicationResponse(String applicationId, int respCode, String respMsg) {
		this(respCode, respMsg);
		this.applicationId = applicationId;
	}

	private ApplicationResponse(DigitalFormGetResponse applicationInfo, int respCode, String respMsg) {
		this(respCode, respMsg);
		this.applicationInfo = applicationInfo;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public DigitalFormGetResponse getApplicationInfo() {
		return applicationInfo;
	}

	public int getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public static ApplicationResponse errorResponse(String errorMessage) {
		return new ApplicationResponse(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static ApplicationResponse successResponseGet(DigitalFormGetResponse applicationInfo, String respCodeStr,
			String respMsg) {

		return new ApplicationResponse(applicationInfo, Integer.parseInt(respCodeStr), respMsg);
	}

	public static ApplicationResponse successResponsePost(String applicationId, String respCodeStr, String respMsg,
			String createdTime) {

		ApplicationResponse response = new ApplicationResponse(applicationId, Integer.parseInt(respCodeStr), respMsg);
		response.setCreatedTime(createdTime);
		return response;
	}

	public static ApplicationResponse successResponsePatch(String applicationId, String respCodeStr, String respMsg,
			String updatedTime) {

		ApplicationResponse response = new ApplicationResponse(applicationId, Integer.parseInt(respCodeStr), respMsg);
		response.setUpdatedTime(updatedTime);
		return response;
	}
}
