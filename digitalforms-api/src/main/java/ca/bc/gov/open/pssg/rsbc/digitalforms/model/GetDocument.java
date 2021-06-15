package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request params for get document API call
 */
public class GetDocument {

    public GetDocument() {
    }

    @JsonProperty("b64")
    private Boolean documentInB64;

    @JsonProperty("url")
    private Boolean documentAsUrl;

    public Boolean getDocumentInB64() {
        return documentInB64;
    }

    public void setDocumentInB64(Boolean documentInB64) {
        this.documentInB64 = documentInB64;
    }

    public Boolean getDocumentAsUrl() {
        return documentAsUrl;
    }

    public void setDocumentAsUrl(Boolean documentAsUrl) {
        this.documentAsUrl = documentAsUrl;
    }

    @Override
    public String toString() {
        return "GetDocument{" +
                "documentInB64=" + documentInB64 +
                ", documentAsUrl=" + documentAsUrl +
                '}';
    }
}
