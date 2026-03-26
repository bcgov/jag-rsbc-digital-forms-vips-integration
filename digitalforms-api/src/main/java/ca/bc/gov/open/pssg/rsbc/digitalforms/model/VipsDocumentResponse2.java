package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VipsDocumentResponse2 {
    
    @JsonProperty("resp")
    private String resp;
    
    @JsonProperty("documentId")
    private String documentId;

    @JsonProperty("error")
    private ErrorDetails error;

    // Getters and Setters
    public String getDocumentId() {
  		return documentId;
  	}

  	public void setDocumentId(String documentId) {
  		this.documentId = documentId;
  	}
  	
    public String getResp() {
        return resp;
    }

	public void setResp(String resp) {
        this.resp = resp;
    }

    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }
}
