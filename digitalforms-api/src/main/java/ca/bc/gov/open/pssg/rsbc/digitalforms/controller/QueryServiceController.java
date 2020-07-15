package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.MockProhibitionStatus;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.MockProhibitionStatusResponse;
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
	
	//@Autowired 
	//private QueryService service; 
	
	// Provides generic type class defs for Swagger 2. 
	private class QuerySwaggerResponse extends JSONResponse<MockProhibitionStatusResponse>{}
	
	//public QueryServiceController(QueryServiceImpl irpService) {
	//	this.service = irpService;
	//}

	@ApiOperation(value = "Get Prohibition status", response = QuerySwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = QuerySwaggerResponse.class)})
	@GetMapping(value ="/{noticeNumber}/status", 
		produces = "application/json"
	)
	public ResponseEntity<JSONResponse<MockProhibitionStatusResponse>> getProhibitionInfo(
			@PathVariable (value="noticeNumber",required=true) Long id)  {
		
		//TODO - uncomment once update VIPS ORDS /prohibitionInfo operation available. 
		//VipsProhibitionStatusResponse ordsResp = service.getProhibitionInfo(id);
		
		// remove mock response once DF-266/267 completed.
		MockProhibitionStatus status = new MockProhibitionStatus();
		status.setNoticeType("IRP");
		status.setEffectiveDate("2018-06-20 00:00:00 -07:00");
		status.setReviewFormSubmitted("true"); 
		status.setReviewCreated("true");
		status.setOriginalCause("IRP3");
		status.setSurnameNm("Jones");
		MockProhibitionStatusResponse ordsResp = new MockProhibitionStatusResponse(status);
		
		JSONResponse<MockProhibitionStatusResponse> resp = new JSONResponse<>(ordsResp);
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
		//IRPInfo info = new IRPInfo(ordsResp); 
		
		//QueryInfoResponse data = new QueryInfoResponse(info);  

		// 3 possible outcomes; good, not found, error. 
//		if (ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD) {
//			JSONResponse<QueryInfoResponse> resp = new JSONResponse<>(data);
//			return new ResponseEntity<>(resp, HttpStatus.OK);
//		} else if (ordsResp.getRespCode() == DigitalFormsConstants.VIPS_ORDS_IRP_NOT_FOUND) {
//			JSONResponse<QueryInfoResponse> resp = new JSONResponse<>(null); 
//		    resp.setError(new JSONError("Notice Number not found", HttpStatus.NOT_FOUND.value()));
//		    return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
//		} else {
//			JSONResponse<QueryInfoResponse> resp = new JSONResponse<>(null); 
//		    resp.setError(new JSONError("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()));
//		    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
		
	}
}

