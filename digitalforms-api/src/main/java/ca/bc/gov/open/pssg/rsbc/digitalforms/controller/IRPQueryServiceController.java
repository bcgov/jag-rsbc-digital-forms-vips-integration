package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> irpGet(@PathVariable (value="id",required=true) Long id)  {
	    String resp = irpService.getIRP(id); 		
	    return new ResponseEntity<String>(resp, HttpStatus.CREATED);
	}

}
