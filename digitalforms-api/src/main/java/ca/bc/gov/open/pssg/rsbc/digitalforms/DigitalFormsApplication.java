package ca.bc.gov.open.pssg.rsbc.digitalforms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.bc.gov.open.pssg.rsbc")
public class DigitalFormsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalFormsApplication.class);
	}

}
