package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IRPQueryServiceControllerTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("IRPQuery - whenValidResponse")
	public void whenValidResponseTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/digitalforms/IRP/1",
				String.class))
				.contains("{\"resp\":\"success\",\"data\":\"IRP result\"}");
	}
	
	@DisplayName("IRPQuery - whenValidReturns201")
	@SuppressWarnings("rawtypes")
	@Test
	public void whenValidReturns201() throws Exception {
		
		ResponseEntity<JSONResponse> responseEntity =
			    restTemplate.getForEntity("http://localhost:" + port + "/digitalforms/IRP/1", JSONResponse.class);
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

}
