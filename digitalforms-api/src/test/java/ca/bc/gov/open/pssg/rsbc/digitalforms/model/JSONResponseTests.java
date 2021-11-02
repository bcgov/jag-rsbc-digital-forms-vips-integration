package ca.bc.gov.open.pssg.rsbc.digitalforms.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * JSON Response Object Tests
 * 
 * @author sivakaruna
 *
 */
public class JSONResponseTests {

	@Test
	public void testObj() {

		JSONResponse<String> response = new JSONResponse<>();
		JSONError error = new JSONError("message", 500);

		response.setData("data");
		response.setError(error);
		response.setResp("response");

		Assertions.assertEquals("data", response.getData());
		Assertions.assertEquals("response", response.getResp());
		Assertions.assertEquals("message", response.getError().getMessage());
		Assertions.assertEquals(500, response.getError().getHttpStatus());

		response.getError().setHttpStatus(501);
		response.getError().setMessage("error");

		Assertions.assertEquals("error", response.getError().getMessage());
		Assertions.assertEquals(501, response.getError().getHttpStatus());

	}

}
