package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransaction;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;

/**
 * 
 * @author shaunmillargov
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentService paymentService;

	@Override
	public PaymentResponse setReviewPaid(String irpNoticeNumber, PaymentTransaction request) {
		DigitalFormPaymentPatchRequest ordsRequest = new DigitalFormPaymentPatchRequest();
		ordsRequest.setPaymentAmt(request.getTransactionInfo().getPaymentAmount());
		ordsRequest.setPaymentCardTypeTxt(request.getTransactionInfo().getPaymentCardType());
		ordsRequest.setPaymentDtm(request.getTransactionInfo().getPaymentDate());
		ordsRequest.setReceiptNumberTxt(request.getTransactionInfo().getReceiptNumberTxt());

		return paymentService.patchPaymentReceipt(irpNoticeNumber, ordsRequest);

	}

	@Override
	public PaymentResponse getReviewPaymentStatus(String noticeNumber) {
		return paymentService.getPaymentStatus(noticeNumber);
	}

}
