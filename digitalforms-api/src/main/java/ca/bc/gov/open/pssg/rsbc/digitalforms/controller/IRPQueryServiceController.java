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
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPStatusInfoResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONError;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPQueryServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
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
	@GetMapping(value ="/{noticeNumber}", 
		produces = "application/json"
	)
	public ResponseEntity<JSONResponse<IRPStatusInfoResponse>> irpGet(@PathVariable (value="noticeNumber",required=true) Long id)  {
		
		VipsProhibitionStatusResponse ordsResp = irpService.getIRP(id);
		
		IRPInfo info = new IRPInfo(ordsResp); 
		IRPStatusInfoResponse data = new IRPStatusInfoResponse(info);  

		// 3 possible outcomes; good, not found, error. 
		if (ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<IRPStatusInfoResponse> resp = new JSONResponse<>(data);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else if (ordsResp.getRespCode() == DigitalFormsConstants.VIPS_ORDS_IRP_NOT_FOUND) {
			JSONResponse<IRPStatusInfoResponse> resp = new JSONResponse<>(null); 
		    resp.setError(new JSONError("Notice Number not found", HttpStatus.NOT_FOUND.value()));
		    return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
		} else {
			JSONResponse<IRPStatusInfoResponse> resp = new JSONResponse<>(null); 
		    resp.setError(new JSONError("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()));
		    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
