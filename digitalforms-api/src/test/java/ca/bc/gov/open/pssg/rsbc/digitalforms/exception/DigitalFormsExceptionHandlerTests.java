package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormData;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

/**
 * 
 * Authentication failure handler Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
class DigitalFormsExceptionHandlerTests {
	@Autowired
	private MockMvc mockMvc;

	@DisplayName("unauthorizedEntryTest")
	@Test
	public void unauthorizedEntryTest() throws Exception {
		this.mockMvc.perform(get("/digitalforms/IRP/guid/application")).andDo(print())
				.andExpect(status().isUnauthorized())
				.andExpect(content().string(containsString(DigitalFormsConstants.UNAUTHORIZED_ERROR)));
	}

	@DisplayName("authorizedEntryTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void authorizedEntryTest() throws Exception {
		this.mockMvc.perform(get("/digitalforms/invalid")).andDo(print())
				.andExpect(status().isNotFound());
	}

	@DisplayName("invalidRequestErrorTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void invalidRequestErrorTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(new ApplicationInfoWrapper<>(new ApplicationFormData()));
		this.mockMvc.perform(patch("/digitalforms/ABC/abc/application/123").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andDo(print()).andExpect(status().isBadRequest());
	}

	@DisplayName("missingRequestBodyErrorTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void missingRequestBodyErrorTest() throws Exception {
		this.mockMvc.perform(patch("/digitalforms/ABC/abc/application/123")).andDo(print())
				.andExpect(status().isInternalServerError());
	}

}
