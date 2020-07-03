package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;

import java.text.MessageFormat;

/**
 *
 * Represents the VIPS application Response
 *
 * @author sivakaruna
 *
 */
@JacksonXmlRootElement(localName = "vipsApplicationResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApplicationResponse {

    private String applicationId;
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

    public String getApplicationId() { return applicationId; }

    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public int getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public static ApplicationResponse errorResponse(String errorMessage) {
        return new ApplicationResponse(
                DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD,
                errorMessage);
    }

    public static ApplicationResponse successResponse(String applicationIdStr, String respCodeStr, String respMsg) {

        return new ApplicationResponse(applicationIdStr, Integer.parseInt(respCodeStr), respMsg);
    }

    @Override
    public String toString() {
        return MessageFormat.format("VipsApplicationResponse: applicationId [{0}], respCode [{1}], respMsg [{2}]", this.applicationId, this.respCode, this.respMsg);
    }

}
