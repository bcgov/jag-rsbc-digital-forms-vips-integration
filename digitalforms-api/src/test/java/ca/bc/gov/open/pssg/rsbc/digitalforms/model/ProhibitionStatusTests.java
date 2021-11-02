package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

		Status status = new Status();
		status.setNoticeServedDt("2018-06-20 00:00:00 -07:00");
		status.setNoticeTypeCd("IRP");
		status.setOriginalCause("IRP3");
		status.setReviewCreatedYn("Y");
		status.setReviewFormSubmittedYn("Y");
		status.setSurnameNm("Smith"); 
		status.setDriverLicenceSeizedYn("N");
		
		Assertions.assertTrue(status.getDisclosure().isEmpty());
		
		List<DocumentDisclosureInfo> disclosure = new ArrayList<>();
		disclosure.add(new DocumentDisclosureInfo("123", "2018-06-20 00:00:00 -07:00"));
		status.setDisclosure(disclosure);

		Assertions.assertEquals("2018-06-20 00:00:00 -07:00", status.getNoticeServedDt());
		Assertions.assertEquals("IRP", status.getNoticeTypeCd());
		Assertions.assertEquals("IRP3",  status.getOriginalCause());
		Assertions.assertEquals("Y", status.getReviewCreatedYn());
		Assertions.assertEquals("Y", status.getReviewFormSubmittedYn());
		Assertions.assertEquals("Smith", status.getSurnameNm());
		Assertions.assertEquals("N", status.getDriverLicenceSeizedYn());
		Assertions.assertEquals("123", status.getDisclosure().get(0).getDocumentId());
		Assertions.assertEquals("2018-06-20 00:00:00 -07:00", status.getDisclosure().get(0).getDisclosedDtm());

		Assertions.assertTrue(status.getReviews().isEmpty());
		
		
		ReviewInfo  revInfo = new ReviewInfo(
				"bb71037c-f87b-0444-e054-00144ff95452",
				"in_progress",
				"2021-03-10 09:30:00 -07:00",
				"2021-03-10 10:00:00 -07:00",
				"1234",
				"5676767");
		
		status.getReviews().add(revInfo);
		
		Assertions.assertEquals("bb71037c-f87b-0444-e054-00144ff95452", status.getReviews().get(0).getApplicationId());
		Assertions.assertEquals("in_progress", status.getReviews().get(0).getStatus());
		Assertions.assertEquals("2021-03-10 09:30:00 -07:00", status.getReviews().get(0).getReviewStartDtm());
		Assertions.assertEquals("2021-03-10 10:00:00 -07:00", status.getReviews().get(0).getReviewEndDtm());
		Assertions.assertEquals("1234", status.getReviews().get(0).getReceiptNumberTxt());
		Assertions.assertEquals("5676767", status.getReviews().get(0).getReviewId());
		
	}

}

