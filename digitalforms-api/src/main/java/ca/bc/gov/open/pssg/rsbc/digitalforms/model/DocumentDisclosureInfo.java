package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * Disclosure info object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "documentId", "disclosedDtm" })
public class DocumentDisclosureInfo {

	@JsonProperty("documentId")
	private String documentId;
	
	@JsonProperty("disclosedDtm")
	private String disclosedDtm;
	
	public DocumentDisclosureInfo(String documentId, String disclosedDtm) {
		this.documentId = documentId;
		this.disclosedDtm = disclosedDtm;
	}

	@JsonProperty("documentId")
	public String getDocumentId() {
		return documentId;
	}

	@JsonProperty("documentId")
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	@JsonProperty("disclosedDtm")
	public String getDisclosedDtm() {
		return disclosedDtm;
	}

	@JsonProperty("disclosedDtm")
	public void setDisclosedDtm(String disclosedDtm) {
		this.disclosedDtm = disclosedDtm;
	}

}
