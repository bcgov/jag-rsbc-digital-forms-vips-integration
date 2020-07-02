package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPStatusInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * IRP Query Controller. 
 * 
 * @author shaunmillargov
 *
 */
@RestController
@RequestMapping("/IRP/")
@Api(value = "IRP Query", tags = { "IRP Query" })
public class IRPQueryServiceController {
	
	@Autowired 
	private IRPQueryService irpService; 
	
	// Provides generic type class defs for Swagger 2. 
	private class IRPQuerySwaggerResponse extends JSONResponse<IRPStatusInfoResponse>{}
	
	public IRPQueryServiceController(IRPQueryServiceImpl irpService) {
		this.irpService = irpService;
	}

	@ApiOperation(value = "Get IRP status", response = IRPQuerySwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = IRPQuerySwaggerResponse.class)})
	@GetMapping(value ="/{id}", 
		produces = "application/json"
	)
	public ResponseEntity<JSONResponse<IRPStatusInfoResponse>> irpGet(@PathVariable (value="id",required=true) Long id)  {
		IRPStatusInfoResponse data = irpService.getIRP(id);
	    JSONResponse<IRPStatusInfoResponse> resp = new JSONResponse<>(data);
	    return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
