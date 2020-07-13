package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;

import java.text.MessageFormat;

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

	private String applicationId;
	private ApplicationInfo applicationInfo;
	private int respCode;
	private String respMsg;
	private String updatedTime;
	private String createdTime;
	private String formXml;

	private ApplicationResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private ApplicationResponse(String applicationId, int respCode, String respMsg) {
		this(respCode, respMsg);
		this.applicationId = applicationId;
	}

	private ApplicationResponse(ApplicationInfo applicationInfo, int respCode, String respMsg, String formXml) {
		this(respCode, respMsg);
		this.applicationInfo = applicationInfo;
		this.formXml = formXml;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public ApplicationInfo getApplicationInfo() {
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

	public String getFormXml() {
		return formXml;
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

	public static ApplicationResponse successResponseGet(ApplicationInfo applicationInfo, String respCodeStr,
			String respMsg, String formXml) {

		return new ApplicationResponse(applicationInfo, Integer.parseInt(respCodeStr), respMsg, formXml);
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

	@Override
	public String toString() {
		return MessageFormat.format(
				"ApplicationResponse: applicationId [{0}], applicationInfo [{1}], respCode [{2}], "
						+ "respMsg [{3}], createdTime [{4}], updatedTime [{5}], formXml [{6}]",
				this.applicationId, this.applicationInfo, this.respCode, this.respMsg, this.createdTime,
				this.updatedTime, this.formXml);
	}

}
