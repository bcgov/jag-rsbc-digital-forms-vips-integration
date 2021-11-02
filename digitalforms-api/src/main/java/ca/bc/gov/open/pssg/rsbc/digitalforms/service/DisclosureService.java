package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.jagvipsclient.disclosure.DisclosureResponse;
import ca.bc.gov.open.jagvipsclient.prohibition.DocumentDisclosureInfo;

/**
 * 
 * Disclosure Service operations Interface
 * 
 * @author sivakaruna
 *
 */
public interface DisclosureService {

	public DisclosureResponse getDisclosureDocument(String documentId, String correlationId);

	public DisclosureResponse setDisclosureSent(String correlationId, DocumentDisclosureInfo disclosureInfo);

}
