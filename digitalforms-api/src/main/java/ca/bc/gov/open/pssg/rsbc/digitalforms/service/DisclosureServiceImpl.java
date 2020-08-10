package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentDisclosureInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.DocumentWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;

/**
 * 
 * Disclosure Service operations Implementation
 * 
 * @author sivakaruna
 *
 */
@Service
public class DisclosureServiceImpl implements DisclosureService {

	private final Logger logger = LoggerFactory.getLogger(DisclosureServiceImpl.class);

	
	@Override
	public JSONResponse<DocumentWrapper> getDisclosureDocument(String documentId, String correlationId) {
		// TODO update service method 
		// getDocument(docId, authguid);
		logger.info("Processing get disclosure document request");
		DocumentInfo document = new DocumentInfo("mime", "document");
		return new JSONResponse<>(new DocumentWrapper(document));
	}

	@Override
	public JSONResponse<Boolean> setDisclosureSent(String noticeNumber, String correlationId,
			DocumentDisclosureInfo disclosureInfo) {
		// TODO update service method 
		// Notice no, dtm, authguid, docId
		logger.info("Processing set disclosure document as sent request");
		return new JSONResponse<>(true);
	}

}
