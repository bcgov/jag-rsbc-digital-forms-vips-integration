package ca.bc.gov.open.pssg.rsbc.digitalforms.model;


import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * 
 * Ping response Object Tests
 * 
 * @author sivakaruna
 *
 */
public class PingResponseTests {

	@SuppressWarnings("unchecked")
	@Test
	public void testObj() {
		
		JSONObject obj = new JSONObject();
		obj.put("key", "value");
		PingResponse response = new PingResponse();
		response.setResponseMessage(obj);
		Assertions.assertEquals("value", response.getResponseMessage().get("key"));
		Assertions.assertFalse(response.getTImeDt().isEmpty());
	}
}
