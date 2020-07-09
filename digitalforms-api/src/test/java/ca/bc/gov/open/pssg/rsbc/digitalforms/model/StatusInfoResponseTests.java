package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * IRP Status Info Response Object Tests
 * 
 * @author sivakaruna
 *
 */
public class StatusInfoResponseTests {

	@Test
	public void testObj() {

		IRPInfo info = new IRPInfo("effectiveDate", "driversLicenceSeizedYN", "surnameNm", "iRPStatus", "cancelledYN");

		QueryInfoResponse infoResponse = new QueryInfoResponse(info);

		Assertions.assertEquals("cancelledYN", infoResponse.getIRPInfo().getCancelledYN());
		Assertions.assertEquals("driversLicenceSeizedYN", infoResponse.getIRPInfo().getDriversLicenceSeizedYN());
		Assertions.assertEquals("effectiveDate", infoResponse.getIRPInfo().getEffectiveDate());
		Assertions.assertEquals("iRPStatus", infoResponse.getIRPInfo().getIRPStatus());
		Assertions.assertEquals("surnameNm", infoResponse.getIRPInfo().getSurnameNm());

		info.setCancelledYN("Y");
		info.setDriversLicenceSeizedYN("Y");
		info.setEffectiveDate("date");
		info.setIRPStatus("iRP");
		info.setSurnameNm("name");

		infoResponse.setIRPInfo(info);

		Assertions.assertEquals("Y", infoResponse.getIRPInfo().getCancelledYN());
		Assertions.assertEquals("Y", infoResponse.getIRPInfo().getDriversLicenceSeizedYN());
		Assertions.assertEquals("date", infoResponse.getIRPInfo().getEffectiveDate());
		Assertions.assertEquals("iRP", infoResponse.getIRPInfo().getIRPStatus());
		Assertions.assertEquals("name", infoResponse.getIRPInfo().getSurnameNm());
	}

}
