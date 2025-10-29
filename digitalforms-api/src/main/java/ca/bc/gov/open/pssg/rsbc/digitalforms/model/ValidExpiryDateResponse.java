package ca.bc.gov.open.pssg.rsbc.digitalforms.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.jagvipsclient.validation.VipsValidExpiryDateResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "expiry_date"})
public class ValidExpiryDateResponse {

    @JsonProperty("expiry_date")
    private String expiryDate;

    public ValidExpiryDateResponse(VipsValidExpiryDateResponse data) {
        this.expiryDate = data.getExpiryDate();
    }

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}
