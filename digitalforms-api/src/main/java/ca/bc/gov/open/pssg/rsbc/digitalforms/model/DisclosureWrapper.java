package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ca.bc.gov.open.jagvipsclient.prohibition.DocumentDisclosureInfo;

/**
 * 
 * Disclosure wrapper object
 * 
 * @author sivakaruna
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "disclosure" })
public class DisclosureWrapper {

	public DisclosureWrapper() {
	}

	public DisclosureWrapper(DocumentDisclosureInfo disclosure) {
		this.disclosure = disclosure;
	}

	@JsonProperty("disclosure")
	private DocumentDisclosureInfo disclosure;

	@JsonProperty("disclosure")
	public DocumentDisclosureInfo getDisclosure() {
		return disclosure;
	}

	@JsonProperty("disclosure")
	public void setDisclosure(DocumentDisclosureInfo disclosure) {
		this.disclosure = disclosure;
	}

}
