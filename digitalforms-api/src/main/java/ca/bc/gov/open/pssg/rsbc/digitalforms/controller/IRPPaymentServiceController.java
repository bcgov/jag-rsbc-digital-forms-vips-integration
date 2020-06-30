package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.IRPPaymentTransRequest;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPPaymentService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.IRPPaymentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * IRP Payment Controller. 
 * 
 * @author shaunmillargov
 *
 */
@RestController
@RequestMapping("/IRP/")
@Api(value = "IRP Payment", tags = { "IRP Payment" })
public class IRPPaymentServiceController {
	
	// Provides generic type class defs for Swagger 2. 
	private class IRPReviewPaidSwaggerResponse extends JSONResponse<Boolean>{}
	
	@Autowired 
	private IRPPaymentService irpPaymentService; 
	
	public IRPPaymentServiceController(IRPPaymentServiceImpl irpPaymentService) {
		this.irpPaymentService = irpPaymentService;
	}
	
	@ApiOperation(value = "Set IRP Review Paid", response = IRPReviewPaidSwaggerResponse.class) 
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = IRPReviewPaidSwaggerResponse.class)})
	@PostMapping(path ="/{irpNoticeNumber}/payment",
	consumes = "application/json",
	produces = "application/json"
	)	
	public ResponseEntity<JSONResponse<Boolean>> setIRPReviewPaid(@PathVariable (value="irpNoticeNumber", required=true) Long irpNoticeNumber, @RequestBody (required=true) IRPPaymentTransRequest paymentInfo)  {
	    boolean data = irpPaymentService.setIRPReviewPaid(irpNoticeNumber, paymentInfo);
	    JSONResponse<Boolean> resp = new JSONResponse<>(data);
	    return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
