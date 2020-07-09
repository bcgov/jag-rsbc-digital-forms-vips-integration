package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.jagvipsclient.prohibition.VipsProhibitionStatusResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONError;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.QueryInfoResponse;
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
@RequestMapping("/IRP/")
@Api(value = "Query", tags = { "Query" })
public class QueryServiceController {
	
	@Autowired 
	private QueryService service; 
	
	// Provides generic type class defs for Swagger 2. 
	private class QuerySwaggerResponse extends JSONResponse<QueryInfoResponse>{}
	
	public QueryServiceController(QueryServiceImpl irpService) {
		this.service = irpService;
	}

	@ApiOperation(value = "Get Prohibition status", response = QuerySwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = QuerySwaggerResponse.class)})
	@GetMapping(value ="/{noticeNumber}", 
		produces = "application/json"
	)
	public ResponseEntity<JSONResponse<QueryInfoResponse>> irpGet(@PathVariable (value="noticeNumber",required=true) Long id)  {
		
		VipsProhibitionStatusResponse ordsResp = service.getProhibitionInfo(id);
		
		IRPInfo info = new IRPInfo(ordsResp); 
		QueryInfoResponse data = new QueryInfoResponse(info);  

		// 3 possible outcomes; good, not found, error. 
		if (ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<QueryInfoResponse> resp = new JSONResponse<>(data);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else if (ordsResp.getRespCode() == DigitalFormsConstants.VIPS_ORDS_IRP_NOT_FOUND) {
			JSONResponse<QueryInfoResponse> resp = new JSONResponse<>(null); 
		    resp.setError(new JSONError("Notice Number not found", HttpStatus.NOT_FOUND.value()));
		    return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
		} else {
			JSONResponse<QueryInfoResponse> resp = new JSONResponse<>(null); 
		    resp.setError(new JSONError("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()));
		    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
