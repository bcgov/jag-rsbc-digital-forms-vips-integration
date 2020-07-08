package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * IRP Payment Trans Request Object Tests
 * 
 * @author sivakaruna
 *
 */
public class IRPPaymentTransRequestTests {

	@Test
	public void testObj() {
		IRPPaymentTransRequest irpPaymentTransRequest = new IRPPaymentTransRequest();

		irpPaymentTransRequest.setTransactionInfo(new TransactionInfo("cardType", "totalPrice"));

		Assertions.assertEquals("cardType", irpPaymentTransRequest.getTransactionInfo().getCardType());
		Assertions.assertEquals("totalPrice", irpPaymentTransRequest.getTransactionInfo().getTotalPrice());

		irpPaymentTransRequest.getTransactionInfo().setCardType("type");
		irpPaymentTransRequest.getTransactionInfo().setTotalPrice("price");

		Assertions.assertEquals("type", irpPaymentTransRequest.getTransactionInfo().getCardType());
		Assertions.assertEquals("price", irpPaymentTransRequest.getTransactionInfo().getTotalPrice());

	}

}
