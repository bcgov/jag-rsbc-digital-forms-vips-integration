package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationFormDataPatch;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.ApplicationInfoWrapper;
import ca.bc.gov.open.pssg.rsbc.digitalforms.model.JSONResponse;
import ca.bc.gov.open.pssg.rsbc.digitalforms.controller.ValidationController;
import java.math.BigDecimal;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;

/**
 * 
 * Authentication failure and Controller advice exception handler Tests.
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

	@Test
	void missingPathVariableReturnsStandardPayloadAndClearsMdc() throws Exception {
		DigitalFormsControllerExceptionHandler handler = new DigitalFormsControllerExceptionHandler();
		MethodParameter parameter = new MethodParameter(
				ValidationController.class.getMethod("getWithinTimeframe", String.class, BigDecimal.class), 0);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "test");
		ResponseEntity<JSONResponse<String>> response = handler.handleMissingPathVariableException(
				new MissingPathVariableException("applicationId", parameter), null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(400, response.getBody().getError().getHttpStatus());
		Assertions.assertEquals(DigitalFormsConstants.MISSING_PARAMS_ERROR, response.getBody().getError().getMessage());
		Assertions.assertNull(MDC.get(DigitalFormsConstants.REQUEST_ENDPOINT));
	}

	@Test
	void validationErrorsIncludeAllFieldsAndClearMdc() throws Exception {
		BeanPropertyBindingResult binding = new BeanPropertyBindingResult(new Object(), "request");
		binding.addError(new FieldError("request", "surname", "must not be blank"));
		binding.addError(new FieldError("request", "email", "must be valid"));
		MethodParameter parameter = new MethodParameter(
				ValidationController.class.getMethod("getWithinTimeframe", String.class, BigDecimal.class), 0);
		MDC.put(DigitalFormsConstants.REQUEST_ENDPOINT, "test");
		ResponseEntity<JSONResponse<String>> response = new DigitalFormsControllerExceptionHandler()
				.handleValidationExceptions(new MethodArgumentNotValidException(parameter, binding));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(400, response.getBody().getError().getHttpStatus());
		String message = response.getBody().getError().getMessage();
		Assertions.assertTrue(message.contains("surname: must not be blank"));
		Assertions.assertTrue(message.contains("email: must be valid"));
		Assertions.assertTrue(message.contains(", "));
		Assertions.assertNull(MDC.get(DigitalFormsConstants.REQUEST_ENDPOINT));
	}

	@DisplayName("unauthorizedEntryTest")
	@Test
	public void unauthorizedEntryTest() throws Exception {
		this.mockMvc.perform(get("/guid/application/123")).andDo(print()).andExpect(status().isUnauthorized())
				.andExpect(content().string(containsString(DigitalFormsConstants.UNAUTHORIZED_ERROR)));
	}

	@DisplayName("authorizedEntryTest - invalid form type")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void authorizedEntryTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(new ApplicationInfoWrapper<>(new ApplicationFormDataPatch()));
		this.mockMvc
				.perform(patch("/ABC/abc/application/123").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print()).andExpect(status().isNotFound()).andExpect(content().string(containsString(String
						.format(DigitalFormsConstants.INVALID_ATTRIBUTE_ERROR, DigitalFormsConstants.FORM_TYPE_TEXT))));
	}

	@DisplayName("noHandlerErrorTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void noHandlerErrorTest() throws Exception {
		this.mockMvc.perform(get("/invalid")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString("")));
	}

	@DisplayName("unexpectedErrorTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void unexpectedErrorTest() throws Exception {
		this.mockMvc.perform(get("/guid/application/123")).andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString(DigitalFormsConstants.UNKNOWN_ERROR)));
	}

	@DisplayName("missingRequestBodyErrorTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void missingRequestBodyErrorTest() throws Exception {
		this.mockMvc.perform(post("/IRP/abc/application/123").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().string(containsString(DigitalFormsConstants.MISSING_REQUEST_BODY_ERROR)));
	}

	@DisplayName("missingPathVariableErrorTest")
	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void missingPathVariableErrorTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(new ApplicationInfoWrapper<>(new ApplicationFormDataPatch()));
		this.mockMvc.perform(patch("/application/123").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(DigitalFormsConstants.MISSING_PARAMS_ERROR)));
	}
}
