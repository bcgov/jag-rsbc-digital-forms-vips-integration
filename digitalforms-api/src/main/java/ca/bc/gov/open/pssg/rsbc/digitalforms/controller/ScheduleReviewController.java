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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewInfo;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ReviewInfoWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.TimeSlotWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.SavedTimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlotResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.ordsclient.review.TimeSlots;
import ca.bc.gov.open.pssg.rsbc.digitalforms.service.ScheduleReviewService;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 * 
 * Schedule a review Controller.
 * 
 * @author sivakaruna
 *
 */
@RestController
@Tag(name = "Review Scheduling", description = "Review Scheduling" )
public class ScheduleReviewController {

	private class AvailabilityInfoSwaggerResponse extends JSONResponse<TimeSlots> {
	}

	private class ReviewScheduledSwaggerResponse extends JSONResponse<ReviewInfoWrapper> {
	}

	@Autowired
	ScheduleReviewService service;

	private final Logger logger = LoggerFactory.getLogger(ScheduleReviewController.class);

	@GetMapping(value = { "**/review/availableTimeSlot/**",
			"/{noticeTypeCd}/{reviewTypeCd}/{reviewDate}/review/availableTimeSlot/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Get Available Review Timeslots")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success") })
	public ResponseEntity<JSONResponse<TimeSlots>> availableTimeSlotsGet(
			@PathVariable(value = "noticeTypeCd", required = true) String noticeTypeCd,
			@PathVariable(value = "reviewTypeCd", required = true) String reviewTypeCd,
			@PathVariable(value = "reviewDate", required = true) String reviewDate,
			@PathVariable(value = "correlationId", required = true) String correlationId) {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "availableTimeSlotsGet");
		logger.info("Get available time slots request received");

		TimeSlotResponse data = service.getAvailableTimeSlots(noticeTypeCd, reviewTypeCd, reviewDate, correlationId);

		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<TimeSlots> resp = new JSONResponse<>(data.getTimeslots());
			logger.info("Get available time slots request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Get available time slots data not found");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_FOUND_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = { "**/review/schedule/**",
			"/{applicationId}/review/schedule/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@Operation(summary = "Post Selected Review Timeslot")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Success") })
	public ResponseEntity<JSONResponse<ReviewInfoWrapper>> selectedReviewTimePost(
			@PathVariable(value = "applicationId", required = true) String applicationId,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) TimeSlotWrapper timeSlot) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "selectedReviewTimePost");
		logger.info("Post selected review time request received");
		
		// Validate start/end date format
		DigitalFormsUtils.validateTimeDate(timeSlot.getTimeSlot().getReviewStartDtm());
		DigitalFormsUtils.validateTimeDate(timeSlot.getTimeSlot().getReviewEndDtm());

		SavedTimeSlotResponse data = service.postSelectedReviewTime(applicationId, timeSlot.getTimeSlot(),
				correlationId);

		if (data.getRespCode() >= DigitalFormsConstants.ORDS_SUCCESS_CD) {
			JSONResponse<ReviewInfoWrapper> resp = new JSONResponse<>(new ReviewInfoWrapper(new ReviewInfo(data)));
			logger.info("Post selected review time request success");
			MDC.clear();
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} else {
			logger.info("Post selected review time request not processed");
			MDC.clear();
			return new ResponseEntity<>(
					DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.NOT_PROCESSED_ERROR, 404),
					HttpStatus.NOT_FOUND);
		}
	}

}
