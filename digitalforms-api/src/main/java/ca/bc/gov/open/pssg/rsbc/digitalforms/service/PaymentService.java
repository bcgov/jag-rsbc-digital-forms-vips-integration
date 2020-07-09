package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransRequest;

/**
 * 
 * IRP Payment Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface PaymentService {

	// ref: 2.17.5
	public boolean setReviewPaid(Long noticeNumber, PaymentTransRequest request);
	
	// ref: 2.7.3
	public PaymentStatusResponse getReviewPaymentStatus(Long noticeNumber);
	
}




