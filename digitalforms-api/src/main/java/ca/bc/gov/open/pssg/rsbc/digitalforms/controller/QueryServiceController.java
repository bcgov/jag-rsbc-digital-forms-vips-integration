package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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

/**
 * 
 * Query Controller.
 * 
 * @author shaunmillargov
 *
 */
@RestController
@Tag(name = "Query", description = "Query")
public class QueryServiceController {

	@Autowired
	private QueryService service;

	// Provides generic type class defs for Swagger 2.
	private class QuerySwaggerResponse extends JSONResponse<ProhibitionStatusResponse> {
	}

	public QueryServiceController(QueryServiceImpl irpService) {
		this.service = irpService;
	}

	private final Logger logger = LoggerFactory.getLogger(QueryServiceController.class);

	@Operation(summary = "Get Prohibition status")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success") })
	@GetMapping(value = { "**/status/**", "/{noticeNumber}/status/{correlationId}" }, produces = "application/json")
	public ResponseEntity<JSONResponse<ProhibitionStatusResponse>> getProhibitionInfo(
			@PathVariable(value = "noticeNumber", required = true) String noticeNumber,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "getProhibitionInfo");
		logger.info("Get prohibition info request received");

		VipsProhibitionStatusResponse ordsResp = service.getProhibitionStatus(noticeNumber, correlationId);

		// Map the response to an interim object to rid the response of the respCd and
		// respMsg.
		ProhibitionStatusResponse resp = new ProhibitionStatusResponse(ordsResp);

		// 2 possible outcomes; good, not found. Any exception caught at
		// DigitalFormsControllerExceptionHandler.
		if (ordsResp.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<ProhibitionStatusResponse> r = new JSONResponse<>(resp);
			logger.info("Get prohibition info request success");
			MDC.clear();
			return new ResponseEntity<>(r, HttpStatus.OK);

		} else {
			JSONResponse<ProhibitionStatusResponse> r = new JSONResponse<>(null);
			r.setError(new JSONError(ordsResp.getRespMsg(), HttpStatus.NOT_FOUND.value()));
			logger.info("Get prohibition info not found");
			MDC.clear();
			return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
		}
	}
}
