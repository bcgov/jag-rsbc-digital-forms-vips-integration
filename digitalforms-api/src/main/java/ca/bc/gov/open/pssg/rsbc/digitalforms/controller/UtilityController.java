package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.jag.ordsvipsclient.api.HealthApi;
import ca.bc.gov.open.jag.ordsvipsclient.api.handler.ApiException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.PingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
@Api(value = "IRP Utilities", tags = { "IRP Utilities" })
public class UtilityController {
	
	private final Logger logger = LogManager.getLogger(UtilityController.class);
	
	@Autowired
	private HealthApi healthApi;
	
	@ApiOperation(value = "Digital Forms Ping Service", response = PingResponse.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = PingResponse.class) })
	@GetMapping(path ="/api/utility/ping",
	produces = "application/json"
	)
	public ResponseEntity<PingResponse> getPing() throws ApiException {
		
		logger.info("Heard call to Ping utility");
		
		PingResponse resp = new PingResponse();
		resp.setResponseMessage("ORDS Health Status = " + healthApi.health().getStatus());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
