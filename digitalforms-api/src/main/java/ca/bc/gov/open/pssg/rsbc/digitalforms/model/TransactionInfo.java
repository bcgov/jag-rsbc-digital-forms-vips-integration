package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

/**
 * 
 * Payment Transaction Information object. 
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "paymentCardType", "paymentAmount", "receiptNumberTxt", "paymentDate" })
public class TransactionInfo {

	@JsonProperty("paymentCardType")
	private String paymentCardType;
	
	@JsonProperty("paymentAmount")
	private String paymentAmount;
	
	@JsonProperty("receiptNumberTxt")
	private String receiptNumberTxt;
	
	@JsonProperty("paymentDate")
	private String paymentDate;
	
	public TransactionInfo(String paymentCardType, String paymentAmount, String receiptNumberTxt, String paymentDate) {
		this.paymentCardType = paymentCardType; 
		this.paymentAmount = paymentAmount;
		this.receiptNumberTxt = receiptNumberTxt;
		this.paymentDate = paymentDate; 
	}

	@JsonProperty("paymentCardType")
	public String getPaymentCardType() {
		return paymentCardType;
	}

	@JsonProperty("paymentCardType")
	public void setPaymentCardType(String paymentCardType) {
		this.paymentCardType = paymentCardType;
	}

	@JsonProperty("paymentAmount")
	public String getPaymentAmount() throws DigitalFormsException {
		try {
			// Format payment amount to 2 decimal places
			return String.format("%.2f", Double.valueOf(paymentAmount));
		} catch (NumberFormatException e) {
			throw new DigitalFormsException(DigitalFormsConstants.PAYMENT_FORMAT_ERROR, HttpStatus.BAD_REQUEST);
		}
	}

	@JsonProperty("paymentAmount")
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@JsonProperty("receiptNumberTxt")
	public String getReceiptNumberTxt() {
		return receiptNumberTxt;
	}

	@JsonProperty("receiptNumberTxt")
	public void setReceiptNumberTxt(String receiptNumberTxt) {
		this.receiptNumberTxt = receiptNumberTxt;
	}

	@JsonProperty("paymentDate")
	public String getPaymentDate() {
		return paymentDate;
	}

	@JsonProperty("paymentDate")
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
