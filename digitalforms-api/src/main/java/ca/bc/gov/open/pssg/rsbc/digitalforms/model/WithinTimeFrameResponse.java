package ca.bc.gov.open.pssg.rsbc.digitalforms.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "valid"})
public class WithinTimeFrameResponse {

    @JsonProperty("valid")
    private String valid;

    public WithinTimeFrameResponse(String valid) {
        this.valid = valid;
    }

    public String getValid() { return valid; }
    public void setValid(String valid) { this.valid = valid; }

}
