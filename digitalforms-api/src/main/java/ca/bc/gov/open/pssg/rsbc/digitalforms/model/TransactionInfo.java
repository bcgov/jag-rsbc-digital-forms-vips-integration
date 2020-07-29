package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Supporting object for the PaymentTransRequest. 
 * 
 * Needs refining once the business determines what needs to be registered in VIPS after payment received. 
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
	public String getPaymentAmount() {
		return paymentAmount;
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
