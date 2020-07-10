package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * Payment Status Response Object Tests
 * 
 * @author sivakaruna
 *
 */
public class PaymentStatusResponseTests {

	@Test
	public void testObj() {
		PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse("amountOwing");

		Assertions.assertEquals("amountOwing", paymentStatusResponse.getAmountOwing());

		paymentStatusResponse.setAmountOwing("1");

		Assertions.assertEquals("1", paymentStatusResponse.getAmountOwing());
	}
}
