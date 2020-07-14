package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


/**
 * 
 * Query Service Controller Tests. 
 * 
 * To be completed once update VIPS ORDS /prohibtionInfo working. 
 * 
 * @author shaunmillargov
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class IRPQueryServiceControllerTests {

	//private final Long PROHIBITION_TEST_ID = 1L;
	//private final String JSON_RESPONSE_GOOD = "N";

	//@MockBean
	//private QueryServiceImpl service;

	//private QueryServiceController controller;

	@BeforeEach
	public void init() {
//		controller = new QueryServiceController(service);
//		
//		ProhibitionStatus goodStatus = new ProhibitionStatus();
//		goodStatus.setResultMessage(DigitalFormsConstants.JSON_RESPONSE_SUCCESS);
//		goodStatus.setResultCode(Integer.toString(DigitalFormsConstants.ORDS_SUCCESS_CD));
//		goodStatus.setEffectiveDate("2018-06-20 00:00:00 -07:00");
//		goodStatus.setDriverLicenceSeizedYn("Y");
//		goodStatus.setDriverLastName("PATEL");
//		goodStatus.setReviewStatus("INP");
//		goodStatus.setCancelledYn("N");
//		VipsProhibitionStatusResponse goodResp = VipsProhibitionStatusResponse.successResponse(goodStatus, "0", "success");
//		
//		when(irpService.getProhibitionInfo(1L)).thenReturn(goodResp);
	}

	// Test irpGet for 200 returned on success.
	// TODO - update when fully functional
	@Test
	void irpGetReturns200() {
//		ResponseEntity<JSONResponse<QueryInfoResponse>> resp = controller.irpGet(IRP_TEST_ID);
//		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		
		Assertions.assertTrue(true);
	}

	// Test irpGet for proper JSON reponse on success.
	// TODO - update when fully functional
	@Test
	void irpGetReturnsSuccess() {
//		ResponseEntity<JSONResponse<QueryInfoResponse>> resp = controller.irpGet(1L);
//		Assertions.assertEquals(JSON_RESPONSE_GOOD, resp.getBody().getData().getIRPInfo().getCancelledYN());
		
		Assertions.assertTrue(true);
	}

	// Test irpGet for IRP not found.
	// TODO - update when fully functional
	@Test
	void irpGetReturnsNotFound() {
		// test for not found
		
		Assertions.assertTrue(true);
	}

	// Test irpGet for exception state.
	// TODO - update when fully functional
	@Test
	void irpGetReturnException() {
		// test for exception 
		
		Assertions.assertTrue(true);
	}

}
