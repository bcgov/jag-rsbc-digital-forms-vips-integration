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

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPStatusInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryServiceImpl;


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
	private final IRPStatusInfoResponse JSON_RESPONSE_OBJECT = new IRPStatusInfoResponse(new IRPInfo( "01/02/2020", "0123456", "Rothschild:", "Decided", "N")); 
	private final String JSON_RESPONSE_GOOD = "N";

	@MockBean
	private IRPQueryServiceImpl irpService;

	private IRPQueryServiceController controller;

	@BeforeEach
	public void init() {
		controller = new IRPQueryServiceController(irpService);
		when(irpService.getIRP(1L)).thenReturn(JSON_RESPONSE_OBJECT);
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
