package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentStatusResponse;

/**
 * 
 * Payment Received Object
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "transactionInfo" })
public class PaymentTransaction {

	@JsonProperty("transactionInfo")
	private TransactionInfo transactionInfo;
	
	public PaymentTransaction () {}
	
	public PaymentTransaction(TransactionInfo transInfo) {
		this.transactionInfo = transInfo; 
	}
	
	public PaymentTransaction(DigitalFormPaymentStatusResponse response) {
		this.transactionInfo = new TransactionInfo(response.getPaymentCardTypeTxt(), response.getPaymentAmt(),
				response.getReceiptNumberTxt(), response.getPaymentDtm());
	}

	@JsonProperty("transactionInfo")
	public TransactionInfo getTransactionInfo() {
		return transactionInfo;
	}

	@JsonProperty("transactionInfo")
	public void setTransactionInfo(TransactionInfo transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

}
