package ca.bc.gov.open.pssg.rsbc.digitalforms.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "applicationId", "formExists"})
public class ApplicationExistsResponse {

    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("formExists")
    private String formExists;

    public ApplicationExistsResponse(String applicationId, String formExists) {
        this.applicationId = applicationId;
        this.formExists = formExists;
    }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getFormExists() { return formExists; }
    public void setFormExists(String formExists) { this.formExists = formExists; }
}
