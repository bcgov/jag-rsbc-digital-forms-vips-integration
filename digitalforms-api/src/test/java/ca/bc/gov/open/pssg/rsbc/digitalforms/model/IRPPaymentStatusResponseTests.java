package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * IRP Payment Status Response Object Tests
 * 
 * @author sivakaruna
 *
 */
public class IRPPaymentStatusResponseTests {

	@Test
	public void testObj() {
		IRPPaymentStatusResponse irpPaymentStatusResponse = new IRPPaymentStatusResponse("amountOwing");

		Assertions.assertEquals("amountOwing", irpPaymentStatusResponse.getAmountOwing());

		irpPaymentStatusResponse.setAmountOwing("1");

		Assertions.assertEquals("1", irpPaymentStatusResponse.getAmountOwing());
	}
}
