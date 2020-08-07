package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionService;
import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;

/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class QueryServiceImpl implements QueryService {
	
	private final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);
	
	@Autowired
	private ProhibitionService prohibService;

	@Override
	public VipsProhibitionStatusResponse getProhibitionStatus(String noticeNumber, String correlationId) {
		
		logger.info("Processing get prohibition info request");
		return prohibService.getVipsProhibitionStatus(noticeNumber, correlationId);
		
	}
	
}




