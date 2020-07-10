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
@JsonPropertyOrder({ "totalPrice", "cardType" })
public class TransactionInfo {

	@JsonProperty("totalPrice")
	private String totalPrice;
	
	@JsonProperty("cardType")
	private String cardType;
	
	public TransactionInfo(String cardType, String totalPrice) {
		this.cardType = cardType; 
		this.totalPrice = totalPrice;
	}

	@JsonProperty("totalPrice")
	public String getTotalPrice() {
		return totalPrice;
	}

	@JsonProperty("totalPrice")
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@JsonProperty("cardType")
	public String getCardType() {
		return cardType;
	}

	@JsonProperty("cardType")
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
