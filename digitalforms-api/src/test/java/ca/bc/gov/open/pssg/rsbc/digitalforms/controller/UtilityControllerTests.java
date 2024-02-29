package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PingResponse;
import ca.bc.gov.open.jag.ordsvipsclient.api.HealthApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.jag.ordsvipsclient.api.model.HealthOrdsResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;

/**
 *
 * Utility Controller Tests.
 *
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class UtilityControllerTests {

	private String JSON_RESPONSE_SUCCESS = "{\"VIPS ORDS Health Status\":\"success\",\"DIGITAL FORMS ORDS Health Status\":\"success\"}";

	private String JSON_RESPONSE_ERROR = "{\"message\":\"Message: error";

	@Mock
	private HealthApi api;

	@Mock
	private ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.HealthApi dfApi;

	@InjectMocks
	private UtilityController controller = new UtilityController();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Ping success - UtilityController")
	@Test
	void getPingSuccess() throws DigitalFormsException, ApiException,
			ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException {
		HealthOrdsResponse health = new HealthOrdsResponse();
		ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.HealthOrdsResponse dfHealth = new ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.HealthOrdsResponse();
		health.setStatus("success");
		dfHealth.setStatus("success");
		when(api.health()).thenReturn(health);
		when(dfApi.health()).thenReturn(dfHealth);
		ResponseEntity<PingResponse> resp = controller.getPing();
		Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
		Assertions.assertEquals(JSON_RESPONSE_SUCCESS, resp.getBody().getResponseMessage().toJSONString());
	}

	@DisplayName("Ping error - UtilityController")
	@Test
	void getPingError() throws DigitalFormsException, ApiException,
			ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.handler.ApiException {
		HealthOrdsResponse health = new HealthOrdsResponse();
		ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.HealthOrdsResponse dfHealth = new ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.model.HealthOrdsResponse();
		health.setStatus("success");
		dfHealth.setStatus("success");
		when(api.health()).thenThrow(new ApiException("error"));
		when(dfApi.health()).thenReturn(dfHealth);
		ResponseEntity<PingResponse> resp = controller.getPing();
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
		Assertions.assertTrue(resp.getBody().getResponseMessage().toJSONString().startsWith(JSON_RESPONSE_ERROR));
	}

}
