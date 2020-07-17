package ca.bc.gov.open.pssg.rsbc.digitalforms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class DigitalFormsStarterApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Context loaded");
		Assertions.assertTrue(true);
	}

}
