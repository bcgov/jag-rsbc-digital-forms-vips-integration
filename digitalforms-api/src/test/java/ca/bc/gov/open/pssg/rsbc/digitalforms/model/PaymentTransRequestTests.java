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

		paymentTransRequest.setTransactionInfo(new TransactionInfo("cardType", "totalPrice"));

		Assertions.assertEquals("cardType", paymentTransRequest.getTransactionInfo().getCardType());
		Assertions.assertEquals("totalPrice", paymentTransRequest.getTransactionInfo().getTotalPrice());

		paymentTransRequest.getTransactionInfo().setCardType("type");
		paymentTransRequest.getTransactionInfo().setTotalPrice("price");

		Assertions.assertEquals("type", paymentTransRequest.getTransactionInfo().getCardType());
		Assertions.assertEquals("price", paymentTransRequest.getTransactionInfo().getTotalPrice());

	}

}
