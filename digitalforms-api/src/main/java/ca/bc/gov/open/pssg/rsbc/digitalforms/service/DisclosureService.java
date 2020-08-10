package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentDisclosureInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;

/**
 * 
 * Disclosure Service operations Interface
 * 
 * @author sivakaruna
 *
 */
public interface DisclosureService {

	public JSONResponse<DocumentWrapper> getDisclosureDocument(String documentId, String correlationId); 

	public JSONResponse<Boolean> setDisclosureSent(String noticeNumber, String correlationId, DocumentDisclosureInfo disclosureInfo);

}
