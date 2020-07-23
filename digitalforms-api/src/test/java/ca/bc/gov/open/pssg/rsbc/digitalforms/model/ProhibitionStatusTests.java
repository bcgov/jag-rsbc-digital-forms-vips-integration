package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionStatus;

/**
 * 
 * Prohibition Status Object Tests
 * 
 * @author shaunmillargov
 *
 */
public class ProhibitionStatusTests {

	@Test
	public void testObj() {

		ProhibitionStatus status = new ProhibitionStatus();
		status.setEffectiveDt("2018-06-20 00:00:00 -07:00");
		status.setNoticeTypeCd("IRP");
		status.setOriginalCause("IRP3");
		status.setReceiptNumberTxt("12345");
		status.setReviewCreatedYn("Y");
		status.setReviewEndDtm("2018-06-29 00:00:00 -07:00");
		status.setReviewStartDtm("2018-06-25 00:00:00 -07:00");
		status.setReviewFormSubmittedYn("Y");
		status.setSurnameNm("Smith"); 

		Assertions.assertEquals("2018-06-20 00:00:00 -07:00", status.getEffectiveDt());
		Assertions.assertEquals("IRP", status.getNoticeTypeCd());
		Assertions.assertEquals("IRP3",  status.getOriginalCause());
		Assertions.assertEquals("12345", status.getReceiptNumberTxt());
		Assertions.assertEquals("Y", status.getReviewCreatedYn());
		Assertions.assertEquals("2018-06-29 00:00:00 -07:00", status.getReviewEndDtm());
		Assertions.assertEquals("2018-06-25 00:00:00 -07:00", status.getReviewStartDtm());
		Assertions.assertEquals("Y", status.getReviewFormSubmittedYn());
		Assertions.assertEquals("Smith", status.getSurnameNm());
		
	}

}

