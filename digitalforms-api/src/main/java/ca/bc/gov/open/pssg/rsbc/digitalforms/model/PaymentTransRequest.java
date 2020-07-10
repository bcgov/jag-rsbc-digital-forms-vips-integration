package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Payment Received Object
 * 
 * @author shaunmillargov
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "transactionInfo" })
public class PaymentTransRequest {

	@JsonProperty("transactionInfo")
	private TransactionInfo transactionInfo;
	
	public PaymentTransRequest () {}
	
	public PaymentTransRequest(TransactionInfo transInfo) {
		this.transactionInfo = transInfo; 
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
