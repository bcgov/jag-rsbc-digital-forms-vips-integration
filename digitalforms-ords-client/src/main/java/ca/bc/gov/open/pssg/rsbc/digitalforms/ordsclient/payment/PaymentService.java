package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchRequest;

/**
 * 
 * Collection of services for accessing Digital forms payment related data.
 * 
 * @author sivakaruna
 *
 */
public interface PaymentService {

	PaymentResponse getPaymentStatus(String applicationId, String correlationId);

	PaymentResponse patchPaymentReceipt(String applicationId, DigitalFormPaymentPatchRequest request, String correlationId);

}
