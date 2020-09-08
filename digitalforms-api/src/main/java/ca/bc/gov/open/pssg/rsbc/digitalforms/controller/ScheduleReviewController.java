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

	private class AvailabilityInfoSwaggerResponse extends JSONResponse<TimeSlots> {
	}

	private class ReviewScheduledSwaggerResponse extends JSONResponse<ReviewInfoWrapper> {
	}

	@Autowired
	ScheduleReviewService service;

	private final Logger logger = LoggerFactory.getLogger(ScheduleReviewController.class);

	@GetMapping(value = { "**/review/availableTimeSlot/**",
			"/{noticeTypeCd}/{reviewTypeCd}/{reviewDate}/review/availableTimeSlot/{correlationId}" }, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Get Available Review Timeslots", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = AvailabilityInfoSwaggerResponse.class) })
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
			"/{noticeNumber}/review/schedule/{correlationId}" }, consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Post Selected Review Timeslot", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success", response = ReviewScheduledSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ReviewInfoWrapper>> selectedReviewTimePost(
			@PathVariable(value = "noticeNumber", required = true) String noticeNumber,
			@PathVariable(value = "correlationId", required = true) String correlationId,
			@RequestBody(required = true) TimeSlotWrapper timeSlot) throws DigitalFormsException {

		MDC.put(DigitalFormsConstants.REQUEST_CORRELATION_ID, correlationId);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "selectedReviewTimePost");
		logger.info("Post selected review time request received");
		
		// Validate start/end date format
		DigitalFormsUtils.validateTimeDate(timeSlot.getTimeSlot().getReviewStartDtm());
		DigitalFormsUtils.validateTimeDate(timeSlot.getTimeSlot().getReviewEndDtm());

		SavedTimeSlotResponse data = service.postSelectedReviewTime(noticeNumber, timeSlot.getTimeSlot(),
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
