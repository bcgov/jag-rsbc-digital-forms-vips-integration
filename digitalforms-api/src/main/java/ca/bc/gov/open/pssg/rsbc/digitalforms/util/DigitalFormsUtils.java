package ca.bc.gov.open.pssg.rsbc.digitalforms.util;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONError;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.FORM_TYPE;

/**
 * 
 * Digital Forms Utils.
 * 
 * @author sivakaruna
 *
 */
public class DigitalFormsUtils {

	private DigitalFormsUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static void validateFormType(String formType) throws DigitalFormsException {
		if (!EnumUtils.isValidEnumIgnoreCase(FORM_TYPE.class, formType)) {
			throw new DigitalFormsException(DigitalFormsConstants.INVALID_FORM_TYPE_ERROR, 
					HttpStatus.NOT_FOUND);
		}
	}

	public static <T> JSONResponse<T> buildErrorResponse(String errorMessage, int statusCode) {
		JSONResponse<T> errorResp = new JSONResponse<>();
		errorResp.setResp(DigitalFormsConstants.JSON_RESPONSE_FAIL);
		JSONError error = new JSONError(errorMessage, statusCode);
		errorResp.setError(error);
		return errorResp;
	}

}
