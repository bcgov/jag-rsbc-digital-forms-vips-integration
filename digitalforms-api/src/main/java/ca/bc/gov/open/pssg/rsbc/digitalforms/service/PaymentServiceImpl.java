package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransRequest;

/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public boolean setReviewPaid(Long irpNoticeNumber, PaymentTransRequest request) {
		// TODO Service to be built out here
		return true;
	}

	@Override
	public PaymentStatusResponse getReviewPaymentStatus(Long noticeNumber) {
		// TODO Service to be built out here
		return new PaymentStatusResponse("1");
	}
	
}




