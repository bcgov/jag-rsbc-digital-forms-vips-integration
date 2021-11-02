package ca.bc.gov.open.pssg.rsbc.digitalforms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PaymentTransaction;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentResponse;

/**
 * 
 * Payment Service Implementation
 * 
 * @author shaunmillargov
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment.PaymentService paymentService;

	@Override
	public PaymentResponse setReviewPaid(String applicationId, String correlationId, PaymentTransaction request)
			throws DigitalFormsException {
		logger.info("Processing set review paid request");

		DigitalFormPaymentPatchRequest ordsRequest = new DigitalFormPaymentPatchRequest();
		ordsRequest.setPaymentAmt(request.getTransactionInfo().getPaymentAmount());
		ordsRequest.setPaymentCardTypeTxt(request.getTransactionInfo().getPaymentCardType());
		ordsRequest.setPaymentDtm(request.getTransactionInfo().getPaymentDate());
		ordsRequest.setReceiptNumberTxt(request.getTransactionInfo().getReceiptNumberTxt());

		return paymentService.patchPaymentReceipt(applicationId, ordsRequest, correlationId);

	}

	@Override
	public PaymentResponse getReviewPaymentStatus(String applicationId, String correlationId) {
		logger.info("Processing get payment status request");

		return paymentService.getPaymentStatus(applicationId, correlationId);
	}

}
