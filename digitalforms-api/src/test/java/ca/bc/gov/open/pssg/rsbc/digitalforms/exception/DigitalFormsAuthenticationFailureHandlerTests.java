package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

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
class DigitalFormsAuthenticationFailureHandlerTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void unauthorizedEntryTest() throws Exception {
		this.mockMvc.perform(get("/digitalforms/IRP/guid/application")).andDo(print())
				.andExpect(status().isUnauthorized())
				.andExpect(content().string(containsString(DigitalFormsConstants.UNAUTHORIZED)));
	}

	@WithMockUser(username = "user", password = "password", authorities = "USER")
	@Test
	public void authorizedEntryTest() throws Exception {
		this.mockMvc.perform(get("/digitalforms/invalid")).andDo(print())
				.andExpect(status().isNotFound());
	}

}
