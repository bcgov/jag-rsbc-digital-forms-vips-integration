package ca.bc.gov.open.pssg.rsbc.digitalforms.util;

public class VipswsConstants {
	
	public static final String VIPSCARMAWS_PKG_NAME = "vips_carma_services_pkg";
	public static final String VIPS_NOTIFICATIONS_PKG_NAME = "vips_notifications_pkg";
		
	// General Response code types
	public static final int VIPSWS_SUCCESS_CD = 0;
	public static final int VIPSWS_GENERAL_FAILURE_CD = 1;
	
	// Response code indicating that web service layer that the back end stored procedure has succeeded but 
	// some further action is required (e.g. invoke ICBC operation). 
	public static final int VIPSWS_FURTHER_OPS_REQUIRED = 10;
	public static final int VIPSWS_SHOW_MANUAL_DIALOG = 11;
	public static final int VIPSWS_NOTIFY_BOTH = 21;
	
	public static final int VIPSWS_INTERNAL_FAILURE_CD = 29;
	
	// General Response message types
	public static final String RESULT_OK = "Success";
	
	// Custom response code types
	// result code indicates a low level JAVA issue rather than a DB problem in all JSON response types.
	public static final int VIPSWS_JAVA_EX = 99; 
	
	// Unable to fetch either impoundment or prohibtion notice no.
	public static final String NOTICE_NO_NOT_AVILABLE = "unavailable";
	
	// Flags used by operations which notify front end and invoke various ICBC ASYNC operations.
	// Only by classes which extend VipsStoredProcedureWHooks
	// Rule where originally documented in RSVIPS-124
	public static final int FRONT_END_NOTIFICATION = 16;
	public static final int CREATE_SNAP = 32;
	public static final int DELETE_SNAP = 64;
	public static final int CREATE_EXP_STATUS = 128;
	public static final int CREATE_CONTRAVENTION = 256;
	public static final int DELETE_EXP_STATUS = 512; // PLACE HOLDER - OPERATION NOT AVAILABLE YET 
	public static final int DELETE_CONTRAVENTION = 1024; 
	public static final int CREATE_EXP_STATUS_CREATE_CONTRAVENTION = 2048;
	public static final int DELETE_EXP_STATUS_DELETE_CONTRAVENTION = 4096; // PLACE HOLDER - OPERATION NOT AVAILABLE YET
	public static final int DELETE_SNAP_CREATE_SNAP = 8192; // OLD VEH REG NO USED FOR DELETE, NEW VEH REG NO USED FOR CREATE
	public static final int FLAG_TOTAL = 16368; // used to validate flags from back end 
	
	// ICBC Driver and Vehicle Search not found values.
	public static final String ICBC_NOT_FOUND = "Not Found";
	public static final int ICBC_NOT_FOUND_CD = 98;

	public static final String ICBC_DATE_FORMAT = "yyyy-MM-dd";
	public static final String ICBC_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String VIPS_DISPLAY_DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String VIPS_NO_META_DATA_AVILABLE = "Not_available"; 
	
	// Used to differentiate email groups when things go wrong. 
	public static final int VIPS_EMAIL_GROUP_1 = 1;
	public static final int VIPS_EMAIL_GROUP_2 = 2;
	
	
	public enum dataSourceCd {
		ICBC, VIPS, CON, CONM;
	}
	
	public enum noticeTypeCd {
		IMP, ADP, IRP, UL;
	}
	
	public enum sanctionTypeCd {
		IMPOUND, PROHIB
	}
	
	public enum icbcTransmissionCd {
		I, C, F; // in-flight, complete, failure
	}
	
	public enum vipsExceptionTypes {
		ICBC, INTERNAL;
	}
	
	public enum parentOperationNames {
		CREATE_IMPOUNDMENT,
		CREATE_PROHIBITION,
		CREATE_REVIEW_DECISION,
		ASSOCIATE_PRIOR_IMPOUNDMENTS,
		DISASSOCIATE_PRIOR_IMPOUNDMENT, 
		UPDATE_IMPOUNDMENT,
		UPDATE_PROHIBITION,
		UPDATE_REVIEW_DECISION,
		UPDATE_REGISTRATION, 
		UPDATE_VEHICLE,
		UPDATE_VEHICLE_REGISTRATION,
		SET_CANCELLED,
		TEST_OPERATION
	}

	/**
	 * Converts review type into Sanction type. 
	 * @param nt
	 * @return
	 */
	public static sanctionTypeCd getSantionType(noticeTypeCd nt) {
		switch (nt) {
		case IMP: 
			return sanctionTypeCd.IMPOUND;
		case ADP: 
			return sanctionTypeCd.PROHIB; 
		case IRP: 
			return sanctionTypeCd.PROHIB; 
		case UL: 
			return sanctionTypeCd.PROHIB; 
		default: 
			return null;
		}
	}

}
