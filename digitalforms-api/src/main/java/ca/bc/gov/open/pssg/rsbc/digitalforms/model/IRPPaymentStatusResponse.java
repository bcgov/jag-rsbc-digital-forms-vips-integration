package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * IRP Payment Status Response Object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "amountOwing" })
public class IRPPaymentStatusResponse {

	@JsonProperty("amountOwing")
	private String amountOwing;
	
	public IRPPaymentStatusResponse(String amountOwing) {
		this.amountOwing = amountOwing;
	}
	
	@JsonProperty("amountOwing")
	public String getAmountOwing() {
		return amountOwing;
	}

	@JsonProperty("amountOwing")
	public void setAmountOwing(String amountOwing) {
		this.amountOwing = amountOwing;
	}

}