package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VipsDocumentResponse {

    @JsonProperty("document_id")
    private String documentId;
    private int httpStatus; 
    private String message; 

    public VipsDocumentResponse() {
    }

    public VipsDocumentResponse(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    
    public int getHttpStatus() {
        return httpStatus;
    }
    
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "VipsDocumentResponse{" +
                "documentId='" + documentId + '\'' +
                '}';
    }
}
