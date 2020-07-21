package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsException;

/**
 * 
 * Application Form Controller Tests.
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class DigitalFormsExceptionTests {

	@DisplayName("DigitalFormsException")
	@Test
	void createException() throws DigitalFormsException {
		DigitalFormsException exception = new DigitalFormsException("Exception", HttpStatus.BAD_REQUEST, null);
		Assertions.assertEquals("Exception", exception.getMessage());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
	}
}
