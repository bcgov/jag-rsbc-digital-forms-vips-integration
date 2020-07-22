package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONError;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.QueryService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.QueryServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Query Controller. 
 * 
 * @author shaunmillargov
 *
 */
@RestController
@Api(value = "Query", tags = { "Query" })
public class QueryServiceController {
	
	@Autowired 
	private QueryService service; 
	
	// Provides generic type class defs for Swagger 2. 
	private class QuerySwaggerResponse extends JSONResponse<ProhibitionStatusResponse>{}
	
	public QueryServiceController(QueryServiceImpl irpService) {
		this.service = irpService;
	}

	@ApiOperation(value = "Get Prohibition status", response = QuerySwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = QuerySwaggerResponse.class)})
	@GetMapping(value ="/{noticeNumber}/status", 
		produces = "application/json"
	)
	public ResponseEntity<JSONResponse<ProhibitionStatusResponse>> getProhibitionInfo(
			@PathVariable (value="noticeNumber",required=true) Long noticeNumber) throws Exception  {
		 
		VipsProhibitionStatusResponse ordsResp = service.getProhibitionStatus(noticeNumber);
		
		// Map the response to an interim object to rid the response of the respCd and respMsg.
		ProhibitionStatusResponse resp = new ProhibitionStatusResponse(ordsResp);  

		// 2 possible outcomes; good, not found. Any exception caught at DigitalFormsControllerExceptionHandler. 
		if (ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD) {	
			JSONResponse<ProhibitionStatusResponse> r = new JSONResponse<>(resp);
			return new ResponseEntity<>(r, HttpStatus.OK);
			
		} else {
			JSONResponse<ProhibitionStatusResponse> r = new JSONResponse<>(null);
			r.setError(new JSONError(ordsResp.getRespMsg(), HttpStatus.NOT_FOUND.value()));
			return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
		} 
	}
}

