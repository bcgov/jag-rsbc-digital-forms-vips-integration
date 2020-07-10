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

	private ApplicationResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private ApplicationResponse(String applicationId, int respCode, String respMsg) {
		this(respCode, respMsg);
		this.applicationId = applicationId;
	}

	private ApplicationResponse(ApplicationInfo applicationInfo, int respCode, String respMsg) {
		this(respCode, respMsg);
		this.applicationInfo = applicationInfo;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationInfo getApplicationInfo() {
		return applicationInfo;
	}

	public void setApplicationInfo(ApplicationInfo applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

	public int getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public static ApplicationResponse errorResponse(String errorMessage) {
		return new ApplicationResponse(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static ApplicationResponse successResponse(String applicationIdStr, String respCodeStr, String respMsg) {

		return new ApplicationResponse(applicationIdStr, Integer.parseInt(respCodeStr), respMsg);
	}

	public static ApplicationResponse successResponseWithInfo(ApplicationInfo applicationInfo, String respCodeStr,
			String respMsg) {

		return new ApplicationResponse(applicationInfo, Integer.parseInt(respCodeStr), respMsg);
	}

	@Override
	public String toString() {
		return MessageFormat.format(
				"ApplicationResponse: applicationId [{0}], applicationInfo [{1}], respCode [{2}], respMsg [{3}]",
				this.applicationId, this.applicationInfo, this.respCode, this.respMsg);
	}

}
