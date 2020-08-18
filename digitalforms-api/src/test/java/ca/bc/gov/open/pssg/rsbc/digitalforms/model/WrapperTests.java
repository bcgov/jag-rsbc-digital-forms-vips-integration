package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ca.bc.gov.open.jagvipsclient.disclosure.DocumentInfo;
import ca.bc.gov.open.jagvipsclient.prohibition.DocumentDisclosureInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlot;

/**
 * 
 * Wrapper Object Tests
 * 
 * @author sivakaruna
 *
 */
public class WrapperTests {

	@Test
	public void testDocumentWrapper() {

		DocumentWrapper wrapper = new DocumentWrapper();
		DocumentInfo info = new DocumentInfo("mimeType", "document");
		wrapper.setDocument(info);

		Assertions.assertEquals("mimeType", wrapper.getDocument().getMimeType());
		Assertions.assertEquals("document", wrapper.getDocument().getDocument());
	}

	@Test
	public void testApplicationInfoWrapper() {

		ApplicationInfoWrapper<ApplicationIdResponse> idWrapper = new ApplicationInfoWrapper<>();
		ApplicationIdResponse idResponse = new ApplicationIdResponse("applicationId", "createdTime", "updatedTime");
		idWrapper.setApplicationInfo(idResponse);

		Assertions.assertEquals("applicationId", idWrapper.getApplicationInfo().getApplicationId());
	}

	@Test
	public void testDisclosureWrapper() {

		DisclosureWrapper wrapper = new DisclosureWrapper();
		DocumentDisclosureInfo info = new DocumentDisclosureInfo("documentId", "disclosedDtm");
		wrapper.setDisclosure(info);

		Assertions.assertEquals("documentId", wrapper.getDisclosure().getDocumentId());
	}

	@Test
	public void testTimeSlotWrapper() {

		TimeSlotWrapper wrapper = new TimeSlotWrapper();
		TimeSlot timeSlot = new TimeSlot("reviewStartDtm", "reviewEndDtm");
		wrapper.setTimeSlot(timeSlot);

		Assertions.assertEquals("reviewStartDtm", wrapper.getTimeSlot().getReviewStartDtm());
	}

}
