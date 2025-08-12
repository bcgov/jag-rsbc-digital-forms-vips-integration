package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import ca.bc.gov.open.jagvipsclient.validation.VipsValidTimeframeResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.*;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ValidationService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ValidationServiceImpl;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * 
 * Validation service Controller.
 * 
 * @author jdosil
 *
 */
@RestController
@Validated
@Tag(name = "Validation", description = "Validation")
public class ValidationController {

	@Autowired
	ValidationService service;

	private final Logger logger = LoggerFactory.getLogger(ValidationController.class);

	public ValidationController(ValidationServiceImpl irpService) {
		this.service = irpService;
	}


	@GetMapping(path ="api/validation/withinTimeframe", produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Get within timeframe ")
	@ApiResponses(value =  	{	@ApiResponse(responseCode = "200", description = "Success")
							,	@ApiResponse(responseCode = "400", description = "Bad Request")
							})
	public ResponseEntity<WithinTimeFrameResponse> getWithinTimeframe(
			@Valid @NotBlank @RequestParam(name = "startDate",required = true)  String startDate,
			@Valid @NotNull @RequestParam(name = "intervalDays",required = true) BigDecimal intervalDays) {

		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "withinTimeframeGet");
		logger.info("Validate timeframe request received");

		VipsValidTimeframeResponse data = service.withinTimeFrame(startDate, intervalDays);
		WithinTimeFrameResponse resp = new WithinTimeFrameResponse(data.getValid());

		// 2 possible outcomes; good, not found. Any exception caught at
		// DigitalFormsControllerExceptionHandler.
		if (data.getRespCode() == DigitalFormsConstants.ORDS_SUCCESS_CD) {
			logger.info("Within timeframe request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Within timeframe error");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
