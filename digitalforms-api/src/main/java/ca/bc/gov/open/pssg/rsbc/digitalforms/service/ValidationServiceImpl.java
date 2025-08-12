package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.jagvipsclient.validation.VipsValidTimeframeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class ValidationServiceImpl implements ValidationService {
	
	private final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);
	
	@Autowired
	private ca.bc.gov.open.jagvipsclient.validation.ValidationService validationService;

	@Override
	public VipsValidTimeframeResponse withinTimeFrame(String startDate, BigDecimal intervalDays) {
		
		logger.info("Processing within timeframe request");
		return validationService.getWithinTimeframe(startDate, intervalDays);
		
	}
	
}




