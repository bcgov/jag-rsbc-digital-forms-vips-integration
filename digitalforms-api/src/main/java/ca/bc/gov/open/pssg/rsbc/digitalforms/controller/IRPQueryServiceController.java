package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryServiceImpl;
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
public class IRPQueryServiceController {
	
	@Autowired 
	private IRPQueryService irpService; 
	
	public IRPQueryServiceController(IRPQueryServiceImpl irpService) {
		this.irpService = irpService;
	}

	@ApiOperation(value = "Get IRP status", response = JSONResponse.class, tags={ "IRP Query" }) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = JSONResponse.class)})
	@RequestMapping(value ="/{id}",
		method = RequestMethod.GET, 
		produces = "application/json"
	)
	public ResponseEntity<JSONResponse<String>> irpGet(@PathVariable (value="id",required=true) Long id)  {
	    String data = irpService.getIRP(id);
	    JSONResponse<String> resp = new JSONResponse<String>(data);
	    return new ResponseEntity<JSONResponse<String>>(resp, HttpStatus.OK);
	}
}
