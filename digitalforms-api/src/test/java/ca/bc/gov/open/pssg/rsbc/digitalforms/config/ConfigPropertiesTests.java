package ca.bc.gov.open.pssg.rsbc.digitalforms.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * 
 * Config properties Tests
 * 
 * @author sivakaruna
 *
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class ConfigPropertiesTests {
	
	@Autowired
	private ConfigProperties properties;
	
	@Test
	void testConfig() {
		Assertions.assertEquals("0.0.1", properties.getServiceApiVersion());
		Assertions.assertEquals(true, properties.isServiceSwaggerEnabled());
		Assertions.assertEquals("digitalforms", properties.getOrdsUserGuid());
	}

}
