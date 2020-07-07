package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPPaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPPaymentTransRequest;

/**
 * 
 * IRP Payment Service Interface 
 * 
 * @author shaunmillargov
 *
 */
public interface IRPPaymentService {

	// ref: 2.17.5
	public boolean setIRPReviewPaid(Long irpNoticeNumber, IRPPaymentTransRequest request);
	
	// ref: 2.7.3
	public IRPPaymentStatusResponse getIRPReviewPaymentStatus(Long irpNoticeNumber);
	
}




