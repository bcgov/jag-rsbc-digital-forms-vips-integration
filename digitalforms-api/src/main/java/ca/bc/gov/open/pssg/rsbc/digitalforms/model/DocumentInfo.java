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
@JsonPropertyOrder({ "mime", "document" })
public class DocumentInfo {

	@JsonProperty("mime")
	private String mime;
	@JsonProperty("document")
	private String document;
	
	public DocumentInfo(String mime, String document) {
		this.mime = mime;
		this.document = document;
	}

	@JsonProperty("mime")
	public String getMime() {
		return mime;
	}

	@JsonProperty("mime")
	public void setMime(String mime) {
		this.mime = mime;
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
