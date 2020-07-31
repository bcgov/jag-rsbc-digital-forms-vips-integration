package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;

/**
 * 
 * Payment Trans Request Object Tests
 * 
 * @author sivakaruna
 *
 */
public class PaymentTransactionTests {

	@Test
	public void testObj() throws DigitalFormsException {
		
		PaymentTransaction paymentTransRequest = new PaymentTransaction();

		paymentTransRequest.setTransactionInfo(new TransactionInfo("cardType", "30.12", "receiptNumberTxt", "paymentDate"));

		Assertions.assertEquals("cardType", paymentTransRequest.getTransactionInfo().getPaymentCardType());
		Assertions.assertEquals("30.12", paymentTransRequest.getTransactionInfo().getPaymentAmount());
		Assertions.assertEquals("receiptNumberTxt", paymentTransRequest.getTransactionInfo().getReceiptNumberTxt());
		Assertions.assertEquals("paymentDate", paymentTransRequest.getTransactionInfo().getPaymentDate());
		

		paymentTransRequest.getTransactionInfo().setPaymentCardType("VISA");
		paymentTransRequest.getTransactionInfo().setPaymentAmount("10.4");
		paymentTransRequest.getTransactionInfo().setReceiptNumberTxt("123");
		paymentTransRequest.getTransactionInfo().setPaymentDate("date");

		Assertions.assertEquals("VISA", paymentTransRequest.getTransactionInfo().getPaymentCardType());
		Assertions.assertEquals("10.40", paymentTransRequest.getTransactionInfo().getPaymentAmount());
		Assertions.assertEquals("123", paymentTransRequest.getTransactionInfo().getReceiptNumberTxt());
		Assertions.assertEquals("date", paymentTransRequest.getTransactionInfo().getPaymentDate());

	}
	
	@Test
	public void testError() {
		PaymentTransaction paymentTransRequest = new PaymentTransaction();
		paymentTransRequest
		.setTransactionInfo(new TransactionInfo("cardType", "paymentAmount", "receiptNumberTxt", "paymentDate"));
		Assertions.assertThrows(DigitalFormsException.class, () -> {
			paymentTransRequest.getTransactionInfo().getPaymentAmount();
		});

	}

}
