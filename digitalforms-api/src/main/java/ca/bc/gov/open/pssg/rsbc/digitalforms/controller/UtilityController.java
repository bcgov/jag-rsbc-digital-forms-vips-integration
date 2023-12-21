package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.jag.ordsvipsclient.api.HealthApi;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PingResponse;

/**
 * 
 * Ping endpoint. Demonstration purposes only. Not expected to be carried over to final product. 
 * 
 * Provided a quick test for both this API and the ORDS client.
 * 
 * Demonstrates the usage pattern for the ORDS beans. 
 * 
 * @author shaunmillargov
 *
 */
@RestController
@Tag(name = "Utilities", description = "Utilities")
public class UtilityController {
	
	private final Logger logger = LogManager.getLogger(UtilityController.class);
	
	@Autowired
	private HealthApi vipsHealthApi;
	
	@Autowired
	private ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.api.HealthApi digitalFormsHealthApi;
	
	@SuppressWarnings("unchecked")
	@Operation(summary = "Digital Forms Ping Service")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success") })
	@GetMapping(path ="/api/utility/ping",
	produces = "application/json"
	)
	public ResponseEntity<PingResponse> getPing() {

		logger.info("Heard call to Ping utility");
		try {

			PingResponse resp = new PingResponse();
			JSONObject jsonResponse = new JSONObject();

			jsonResponse.put("VIPS ORDS Health Status", vipsHealthApi.health().getStatus());
			jsonResponse.put("DIGITAL FORMS ORDS Health Status", digitalFormsHealthApi.health().getStatus());

			resp.setResponseMessage(jsonResponse);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("message", e.getMessage());
			PingResponse errorResp = new PingResponse();
			errorResp.setResponseMessage(errorJSON);
			return new ResponseEntity<>(errorResp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
