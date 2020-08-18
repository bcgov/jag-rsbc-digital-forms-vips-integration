package ca.bc.gov.open.pssg.rsbc.digitalforms.util;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;

import com.google.common.base.Enums;
import com.google.common.base.Strings;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONError;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.FORM_TYPE;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.MANUAL_ENTRY_CD;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.NOTICE_SUBJECT_CD;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.PRESENTATION_TYPE_CD;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants.REVIEW_ROLE_TYPE_CD;

/**
 * 
 * Digital Forms Utils.
 * 
 * @author sivakaruna
 *
 */
public class DigitalFormsUtils {

	private static EnumMap<FORM_TYPE, List<Enum<NOTICE_SUBJECT_CD>>> noticeSubjectMap = new EnumMap<>(FORM_TYPE.class);
	private static EnumMap<FORM_TYPE, List<Enum<PRESENTATION_TYPE_CD>>> presentationTypeMap = new EnumMap<>(
			FORM_TYPE.class);
	private static EnumMap<FORM_TYPE, List<Enum<REVIEW_ROLE_TYPE_CD>>> reviewRoleTypeMap = new EnumMap<>(
			FORM_TYPE.class);
	private static EnumMap<FORM_TYPE, List<Enum<MANUAL_ENTRY_CD>>> manualEntryMap = new EnumMap<>(FORM_TYPE.class);

	static {
		// Initialize enum maps
		noticeSubjectMap.put(FORM_TYPE.IRP, Arrays.asList(NOTICE_SUBJECT_CD.PERS));
		noticeSubjectMap.put(FORM_TYPE.ADP, Arrays.asList(NOTICE_SUBJECT_CD.PERS));
		noticeSubjectMap.put(FORM_TYPE.UL, Arrays.asList(NOTICE_SUBJECT_CD.PERS));

		presentationTypeMap.put(FORM_TYPE.IRP, Arrays.asList(PRESENTATION_TYPE_CD.ORAL, PRESENTATION_TYPE_CD.WRIT,
				PRESENTATION_TYPE_CD.CANCELLED, PRESENTATION_TYPE_CD.NONE));
		presentationTypeMap.put(FORM_TYPE.ADP, Arrays.asList(PRESENTATION_TYPE_CD.ORAL, PRESENTATION_TYPE_CD.WRIT,
				PRESENTATION_TYPE_CD.CANCELLED, PRESENTATION_TYPE_CD.NONE));
		presentationTypeMap.put(FORM_TYPE.UL,
				Arrays.asList(PRESENTATION_TYPE_CD.WRIT, PRESENTATION_TYPE_CD.CANCELLED, PRESENTATION_TYPE_CD.NONE));

		reviewRoleTypeMap.put(FORM_TYPE.IRP,
				Arrays.asList(REVIEW_ROLE_TYPE_CD.APPNT, REVIEW_ROLE_TYPE_CD.LWYR, REVIEW_ROLE_TYPE_CD.AUTHPERS));
		reviewRoleTypeMap.put(FORM_TYPE.ADP,
				Arrays.asList(REVIEW_ROLE_TYPE_CD.APPNT, REVIEW_ROLE_TYPE_CD.LWYR, REVIEW_ROLE_TYPE_CD.AUTHPERS));
		reviewRoleTypeMap.put(FORM_TYPE.UL,
				Arrays.asList(REVIEW_ROLE_TYPE_CD.APPNT, REVIEW_ROLE_TYPE_CD.LWYR, REVIEW_ROLE_TYPE_CD.AUTHPERS));

		manualEntryMap.put(FORM_TYPE.IRP, Arrays.asList(MANUAL_ENTRY_CD.Y, MANUAL_ENTRY_CD.N));
		manualEntryMap.put(FORM_TYPE.ADP, Arrays.asList(MANUAL_ENTRY_CD.Y, MANUAL_ENTRY_CD.N));
		manualEntryMap.put(FORM_TYPE.UL, Arrays.asList(MANUAL_ENTRY_CD.Y, MANUAL_ENTRY_CD.N));
	}

	private DigitalFormsUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static void validateFormType(String formType) throws DigitalFormsException {
		if (!EnumUtils.isValidEnumIgnoreCase(FORM_TYPE.class, formType)) {
			throw new DigitalFormsException(
					String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR, DigitalFormsConstants.FORM_TYPE_TEXT),
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

	/**
	 * 
	 * Validate application form fields against enum values
	 * 
	 * @param formData
	 * @param formType
	 * @throws DigitalFormsException
	 */
	public static void validateApplicationForm(ApplicationFormData formData, String formType)
			throws DigitalFormsException {
		if (!Strings.isNullOrEmpty(formData.getNoticeSubjectCd()) && !noticeSubjectMap.get(FORM_TYPE.valueOf(formType))
				.contains(Enums.getIfPresent(NOTICE_SUBJECT_CD.class, formData.getNoticeSubjectCd()).orNull())) {
			throw new DigitalFormsException(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
					DigitalFormsConstants.NOTICE_SUBJECT_TEXT), HttpStatus.NOT_FOUND);
		}
		if (!Strings.isNullOrEmpty(formData.getPresentationTypeCd())
				&& !presentationTypeMap.get(FORM_TYPE.valueOf(formType)).contains(
						Enums.getIfPresent(PRESENTATION_TYPE_CD.class, formData.getPresentationTypeCd()).orNull())) {
			throw new DigitalFormsException(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
					DigitalFormsConstants.PRESENTATION_TYPE_TEXT), HttpStatus.NOT_FOUND);
		}
		if (!Strings.isNullOrEmpty(formData.getReviewRoleTypeCd())
				&& !reviewRoleTypeMap.get(FORM_TYPE.valueOf(formType)).contains(
						Enums.getIfPresent(REVIEW_ROLE_TYPE_CD.class, formData.getReviewRoleTypeCd()).orNull())) {
			throw new DigitalFormsException(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
					DigitalFormsConstants.REVIEW_ROLE_TYPE_TEXT), HttpStatus.NOT_FOUND);
		}
		if (!Strings.isNullOrEmpty(formData.getManualEntryYN()) && !manualEntryMap.get(FORM_TYPE.valueOf(formType))
				.contains(Enums.getIfPresent(MANUAL_ENTRY_CD.class, formData.getManualEntryYN()).orNull())) {
			throw new DigitalFormsException(String.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR,
					DigitalFormsConstants.MANUAL_ENTRY_TEXT), HttpStatus.NOT_FOUND);
		}
	}

}
