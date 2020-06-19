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
	IRPQueryService irpService; 
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<JSONResponse<String>> irpGet(@PathVariable (value="id",required=true) Long id)  {
	    String data = irpService.getIRP(id);
	    JSONResponse<String> resp = new JSONResponse<String>(data);
	    return new ResponseEntity<JSONResponse<String>>(resp, HttpStatus.CREATED);
	}

}
