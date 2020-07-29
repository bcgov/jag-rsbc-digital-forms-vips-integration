package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;

/**
 * 
 * IRP Payment Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface PaymentService {

	// ref: 2.17.5
	public PaymentResponse setReviewPaid(String noticeNumber, PaymentTransRequest request);
	
	// ref: 2.7.3
	public PaymentResponse getReviewPaymentStatus(String noticeNumber);
	
}




