package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.bc.gov.open.jagvipsclient.disclosure.DocumentInfo;

/**
 * 
 * Document wrapper object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "document" })
public class DocumentWrapper {

	public DocumentWrapper() {
	}
	
	public DocumentWrapper(DocumentInfo document) {
		this.document = document;
	}

	@JsonProperty("document")
	private DocumentInfo document;

	@JsonProperty("document")
	public DocumentInfo getDocument() {
		return document;
	}

	@JsonProperty("document")
	public void setDocument(DocumentInfo document) {
		this.document = document;
	}

}
