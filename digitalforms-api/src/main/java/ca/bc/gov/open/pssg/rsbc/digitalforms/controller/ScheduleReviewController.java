package ca.bc.gov.open.pssg.rsbc.digitalforms.controller;

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
@Api(value = "Schedule Review", tags = { "Schedule Review" })
public class ScheduleReviewController {

	private class AvailabilityInfoSwaggerResponse extends JSONResponse<ReviewTimeAvailabilityInfo> {
	}

	private class ReviewScheduledSwaggerResponse extends JSONResponse<Boolean> {
	}

	@Autowired
	ScheduleReviewService service;

	@GetMapping(value = "/{noticeTypeCd}/{reviewTypeCd}/{reviewDate}/review/availableTimeSlot", produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Get Available Review Timeslot", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = AvailabilityInfoSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<ReviewTimeAvailabilityInfo>> availableTimeSlotGet(
			@PathVariable(value = "noticeTypeCd", required = true) String noticeTypeCd,
			@PathVariable(value = "reviewTypeCd", required = true) String reviewTypeCd,
			@PathVariable(value = "reviewDate", required = true) String reviewDate) {

		ReviewTimeAvailabilityInfo data = service.getAvailableTimeSlots(noticeTypeCd, reviewTypeCd, reviewDate);
		// TODO Update based on ORDS response
		JSONResponse<ReviewTimeAvailabilityInfo> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@PostMapping(value = "/{noticeNo}/review/schedule", consumes = DigitalFormsConstants.JSON_CONTENT, produces = DigitalFormsConstants.JSON_CONTENT)
	@ApiOperation(value = "Post Selected Review Timeslot", response = JSONResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Success", response = ReviewScheduledSwaggerResponse.class) })
	public ResponseEntity<JSONResponse<Boolean>> selectedReviewTimePost(
			@PathVariable(value = "noticeNo", required = true) Long noticeNo,
			@RequestBody(required = true) ReviewTimeSlot timeSlot) {
		Boolean data = service.postSelectedReviewTime(noticeNo, timeSlot);
		// TODO Update based on ORDS response
		JSONResponse<Boolean> resp = new JSONResponse<>(data);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
