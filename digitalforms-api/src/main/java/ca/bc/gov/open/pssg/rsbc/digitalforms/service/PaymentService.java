package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransaction;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;

/**
 * 
 * Payment Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface PaymentService {

	// ref: 2.17.5
	public PaymentResponse setReviewPaid(String applicationId, String correlationId, PaymentTransaction request) throws DigitalFormsException;
	
	// ref: 2.7.3
	public PaymentResponse getReviewPaymentStatus(String applicationId, String correlationId);
	
}




