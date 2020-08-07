package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.PaymentApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentStatusResponse;

/**
 * 
 * Collection of services for accessing Digital forms payment related data.
 * 
 * @author sivakaruna
 *
 */
public class PaymentServiceImpl implements PaymentService {

	private final PaymentApi paymentApi;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public PaymentServiceImpl(PaymentApi paymentApi) {
		this.paymentApi = paymentApi;
	}

	@Override
	public PaymentResponse getPaymentStatus(String noticeNo, String correlationId) {
		try {
			DigitalFormPaymentStatusResponse response = this.paymentApi
					.digitalFormProhibitionNoticeNoPaymentStatusGet(noticeNo);

			logger.info("Processed Get Payment Status request: [{}] ORDS returned code: {} and message: {} ",
					correlationId, response.getStatusCode(), response.getStatusMessage());

			return PaymentResponse.successStatusResponse(response, response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Payment Service threw an exception: " + ex.getMessage(), ex);
			return PaymentResponse.errorResponse(ex.getMessage());
		}
	}

	@Override
	public PaymentResponse patchPaymentReceipt(String noticeNo, DigitalFormPaymentPatchRequest request,
			String correlationId) {
		try {
			DigitalFormPaymentPatchResponse response = this.paymentApi
					.digitalFormProhibitionNoticeNoPaymentPatch(noticeNo, request);

			logger.info("Processed Patch Payment Receipt request: [{}] ORDS returned code: {} and message: {} ",
					correlationId, response.getStatusCode(), response.getStatusMessage());

			return PaymentResponse.successResponse(response.getUpdDtm(), response.getStatusCode(),
					response.getStatusMessage());

		} catch (ApiException ex) {
			logger.error("Payment Service threw an exception: " + ex.getMessage(), ex);
			return PaymentResponse.errorResponse(ex.getMessage());
		}
	}
}
