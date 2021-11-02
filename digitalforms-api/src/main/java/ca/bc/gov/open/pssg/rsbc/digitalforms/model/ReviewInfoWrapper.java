package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Review Info wrapper object
 * 
 * @author sivakaruna
 *
 */
public class ReviewInfoWrapper {

	@JsonProperty("reviewInfo")
	private ReviewInfo reviewInfo;

	public ReviewInfoWrapper() {
	}

	public ReviewInfoWrapper(ReviewInfo reviewInfo) {
		super();
		this.reviewInfo = reviewInfo;
	}

	public ReviewInfo getReviewInfo() {
		return reviewInfo;
	}

	public void setReviewInfo(ReviewInfo reviewInfo) {
		this.reviewInfo = reviewInfo;
	}

}
