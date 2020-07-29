package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * Payment Trans Request Object Tests
 * 
 * @author sivakaruna
 *
 */
public class PaymentTransRequestTests {

	@Test
	public void testObj() {
		
		PaymentTransRequest paymentTransRequest = new PaymentTransRequest();

		paymentTransRequest.setTransactionInfo(new TransactionInfo("cardType", "totalPrice", "receiptNumberTxt", "paymentDate"));

		Assertions.assertEquals("cardType", paymentTransRequest.getTransactionInfo().getPaymentCardType());
		Assertions.assertEquals("totalPrice", paymentTransRequest.getTransactionInfo().getPaymentAmount());
		Assertions.assertEquals("receiptNumberTxt", paymentTransRequest.getTransactionInfo().getReceiptNumberTxt());
		Assertions.assertEquals("paymentDate", paymentTransRequest.getTransactionInfo().getPaymentDate());
		

		paymentTransRequest.getTransactionInfo().setPaymentCardType("type");
		paymentTransRequest.getTransactionInfo().setPaymentAmount("price");
		paymentTransRequest.getTransactionInfo().setReceiptNumberTxt("receipt");
		paymentTransRequest.getTransactionInfo().setPaymentDate("date");

		Assertions.assertEquals("type", paymentTransRequest.getTransactionInfo().getPaymentCardType());
		Assertions.assertEquals("price", paymentTransRequest.getTransactionInfo().getPaymentAmount());
		Assertions.assertEquals("receipt", paymentTransRequest.getTransactionInfo().getReceiptNumberTxt());
		Assertions.assertEquals("date", paymentTransRequest.getTransactionInfo().getPaymentDate());

	}

}
