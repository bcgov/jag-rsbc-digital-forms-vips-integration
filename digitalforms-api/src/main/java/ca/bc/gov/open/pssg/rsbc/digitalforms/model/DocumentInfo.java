package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Document object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "mimeType", "document" })
public class DocumentInfo {

	@JsonProperty("mimeType")
	private String mimeType;
	@JsonProperty("document")
	private String document;
	
	public DocumentInfo(String mimeType, String document) {
		this.mimeType = mimeType;
		this.document = document;
	}

	@JsonProperty("mimeType")
	public String getMimeType() {
		return mimeType;
	}

	@JsonProperty("mimeType")
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@JsonProperty("document")
	public String getDocument() {
		return document;
	}

	@JsonProperty("document")
	public void setDocument(String document) {
		this.document = document;
	}

}
