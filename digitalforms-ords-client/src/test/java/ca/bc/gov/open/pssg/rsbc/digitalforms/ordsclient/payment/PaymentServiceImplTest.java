package ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.payment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.DigitalFormsOrdsClientConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.PaymentApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentPatchResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.DigitalFormPaymentStatusResponse;

/**
 * Payment service tests
 *
 * @author sivakaruna
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentServiceImplTest {

	public static final String API_EXCEPTION = "api exception";
	public static final String SUCCESS_RESPONSE = "success";
	public static final String ERROR_RESPONSE = "error";

	private PaymentService service;

	@Mock
	private PaymentApi paymentApiMock;

	@BeforeAll
	public void setup() throws ApiException {
		MockitoAnnotations.initMocks(this);

		DigitalFormPaymentPatchResponse paymentOrdsResponseSuccess = new DigitalFormPaymentPatchResponse();
		paymentOrdsResponseSuccess.setStatusCode(String.valueOf(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD));
		paymentOrdsResponseSuccess.setStatusMessage(SUCCESS_RESPONSE);

		DigitalFormPaymentPatchResponse paymentOrdsResponseError = new DigitalFormPaymentPatchResponse();
		paymentOrdsResponseError.setStatusCode(String.valueOf(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD));
		paymentOrdsResponseError.setStatusMessage(ERROR_RESPONSE);

		DigitalFormPaymentStatusResponse paymentStatusOrdsResponseSuccess = new DigitalFormPaymentStatusResponse();
		paymentStatusOrdsResponseSuccess
				.setStatusCode(String.valueOf(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD));
		paymentStatusOrdsResponseSuccess.setStatusMessage(SUCCESS_RESPONSE);
		paymentStatusOrdsResponseSuccess.setPaymentAmt("10");
		paymentStatusOrdsResponseSuccess.setPaymentCardTypeTxt("VISA");
		paymentStatusOrdsResponseSuccess.setPaymentDtm("101209");
		paymentStatusOrdsResponseSuccess.setReceiptNumberTxt("10");

		DigitalFormPaymentStatusResponse paymentStatusOrdsResponseError = new DigitalFormPaymentStatusResponse();
		paymentStatusOrdsResponseError
				.setStatusCode(String.valueOf(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD));
		paymentStatusOrdsResponseError.setStatusMessage(ERROR_RESPONSE);

		Mockito.when(paymentApiMock.digitalFormProhibitionApplicationIdPaymentPatch(eq("1"), any()))
				.thenReturn(paymentOrdsResponseSuccess);
		Mockito.when(paymentApiMock.digitalFormProhibitionApplicationIdPaymentPatch(eq("2"), any()))
				.thenThrow(new ApiException(ERROR_RESPONSE));
		Mockito.when(paymentApiMock.digitalFormProhibitionApplicationIdPaymentStatusGet(eq("1")))
				.thenReturn(paymentStatusOrdsResponseSuccess);
		Mockito.when(paymentApiMock.digitalFormProhibitionApplicationIdPaymentStatusGet(eq("2")))
				.thenThrow(new ApiException(ERROR_RESPONSE));

		service = new PaymentServiceImpl(paymentApiMock);
	}

	@Test
	public void patchSuccess() {
		PaymentResponse response = service.patchPaymentReceipt("1", new DigitalFormPaymentPatchRequest(), "correlationId");

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void patchException() {
		PaymentResponse response = service.patchPaymentReceipt("2", new DigitalFormPaymentPatchRequest(), "correlationId");

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

	@Test
	public void getSuccess() {
		PaymentResponse response = service.getPaymentStatus("1", "correlationId");

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_SUCCESS_CD, response.getRespCode());
		Assertions.assertEquals(SUCCESS_RESPONSE, response.getRespMsg());
	}

	@Test
	public void getException() {
		PaymentResponse response = service.getPaymentStatus("2", "correlationId");

		Assertions.assertEquals(DigitalFormsOrdsClientConstants.SERVICE_FAILURE_CD, response.getRespCode());
		Assertions.assertEquals(ERROR_RESPONSE, response.getRespMsg());
	}

}
