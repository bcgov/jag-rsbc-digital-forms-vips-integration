package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewTimeAvailabilityInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewTimeSlot;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ScheduleReviewService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Schedule a review Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@Api(value = "Review Scheduling", tags = { "Review Scheduling" })
public class ScheduleReviewController {

	private class AvailabilityInfoSwaggerResponse extends JSONResponse<ReviewTimeAvailabilityInfo> {
	}

	private class ReviewScheduledSwaggerResponse extends JSONResponse<Boolean> {
	}

	@Autowired
	ScheduleReviewService service;
	
	Logger logger = LoggerFactory.getLogger(ScheduleReviewController.class);

	@GetMapping(value = { "**/review/availableTimeSlot/**",
			"/{noticeTypeCd}/{reviewTypeCd}/{reviewDate}/review/availableTimeSlot/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Get Available Review Timeslots", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = AvailabilityInfoSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ReviewTimeAvailabilityInfo>> availableTimeSlotsGet(
			@PathVariable(value = "noticeTypeCd", required = true) String noticeTypeCd,
			@PathVariable(value = "reviewTypeCd", required = true) String reviewTypeCd,
			@PathVariable(value = "reviewDate", required = true) String reviewDate,
			@PathVariable(value = "correlationId", required = true) String correlationId) {
		
		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "availableTimeSlotsGet");
		logger.info("Get available time slots request received [{}]", correlationId);

		try {
			ReviewTimeAvailabilityInfo data = service.getAvailableTimeSlots(noticeTypeCd, reviewTypeCd, reviewDate);
			// TODO Update based on ORDS response
			JSONResponse<ReviewTimeAvailabilityInfo> resp = new JSONResponse<>(data);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} finally {
			MDC.clear();
		}

	}

	@PostMapping(value = { "**/review/schedule/**",
			"/{noticeNo}/review/schedule/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Post Selected Review Timeslot", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success", response = ReviewScheduledSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<Boolean>> selectedReviewTimePost(
			@PathVariable(value = "noticeNo", required = true) Long noticeNo,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) ReviewTimeSlot timeSlot) {
		
		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "selectedReviewTimePost");
		logger.info("Post selected review time request received [{}]", correlationId);

		try {
			Boolean data = service.postSelectedReviewTime(noticeNo, timeSlot);
			// TODO Update based on ORDS response
			JSONResponse<Boolean> resp = new JSONResponse<>(data);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} finally {
			MDC.clear();
		}
	}

}
