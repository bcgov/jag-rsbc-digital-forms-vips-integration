package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentStatusResponse;

/**
 *
 * Represents the Payment service Response
 *
 * @author sivakaruna
 * 
 */
@JacksonXmlRootElement(localName = "paymentResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaymentResponse {

	@JsonProperty("paymentStatus")
	private DigitalFormPaymentStatusResponse paymentStatus;

	@JsonProperty("respCode")
	private int respCode;

	@JsonProperty("respMsg")
	private String respMsg;

	@JsonProperty("updatedTime")
	private String updatedTime;

	private PaymentResponse(DigitalFormPaymentStatusResponse paymentStatus, int respCode, String respMsg) {
		this.paymentStatus = paymentStatus;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private PaymentResponse(String updatedTime, int respCode, String respMsg) {
		this.updatedTime = updatedTime;
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	private PaymentResponse(int respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public DigitalFormPaymentStatusResponse getPaymentStatus() {
		return paymentStatus;
	}

	public int getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public static PaymentResponse errorResponse(String errorMessage) {
		return new PaymentResponse(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, errorMessage);
	}

	public static PaymentResponse successResponse(String updatedTime, String respCodeStr, String respMsg) {
		return new PaymentResponse(updatedTime, Integer.parseInt(respCodeStr), respMsg);
	}

	public static PaymentResponse successStatusResponse(DigitalFormPaymentStatusResponse status, String respCodeStr,
			String respMsg) {
		return new PaymentResponse(status, Integer.parseInt(respCodeStr), respMsg);
	}
}
