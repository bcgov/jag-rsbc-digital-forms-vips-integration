package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.jagvipsclient.prohibition.ProhibitionStatus;
import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPStatusInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;


/**
 * 
 * IRP Query Service Controller Tests. 
 * 
 * @author shaunmillargov
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class IRPQueryServiceControllerTests {

	private final Long IRP_TEST_ID = 1L;
	private final String JSON_RESPONSE_GOOD = "N";

	@MockBean
	private IRPQueryServiceImpl irpService;

	private IRPQueryServiceController controller;

	@BeforeEach
	public void init() {
		controller = new IRPQueryServiceController(irpService);
		
		ProhibitionStatus goodStatus = new ProhibitionStatus();
		goodStatus.setResultMessage(DigitalFormsConstants.JSON_RESPONSE_SUCCESS);
		goodStatus.setResultCode(Integer.toString(DigitalFormsConstants.ORDS_SUCCESS_CD));
		goodStatus.setEffectiveDate("2018-06-20 00:00:00 -07:00");
		goodStatus.setDriverLicenceSeizedYn("Y");
		goodStatus.setDriverLastName("PATEL");
		goodStatus.setReviewStatus("INP");
		goodStatus.setCancelledYn("N");
		VipsProhibitionStatusResponse goodResp = VipsProhibitionStatusResponse.successResponse(goodStatus, "0", "success");
		
		when(irpService.getIRP(1L)).thenReturn(goodResp);
	}

	// Test irpGet for 200 returned on success.
	// TODO - update when fully functional
	@Test
	void irpGetReturns200() {
		ResponseEntity<JSONResponse<IRPStatusInfoResponse>> resp = controller.irpGet(IRP_TEST_ID);
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	// Test irpGet for proper JSON reponse on success.
	// TODO - update when fully functional
	@Test
	void irpGetReturnsSuccess() {
		ResponseEntity<JSONResponse<IRPStatusInfoResponse>> resp = controller.irpGet(1L);
		Assertions.assertEquals(JSON_RESPONSE_GOOD, resp.getBody().getData().getIRPInfo().getCancelledYN());
	}

	// Test irpGet for IRP not found.
	// TODO - update when fully functional
	@Test
	void irpGetReturnsNotFound() {
		// test for not found
	}

	// Test irpGet for exception state.
	// TODO - update when fully functional
	@Test
	void irpGetReturnException() {
		// test for exception 
	}

}
