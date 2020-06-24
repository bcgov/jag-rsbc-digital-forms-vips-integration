package ca.bc.gov.open.pssg.rsbc.digitalforms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication(scanBasePackages = "ca.bc.gov.open.pssg.rsbc")
public class DigitalFormsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalFormsApplication.class, args);
	}
	
	// Do not tamper with. 
	// Forces application/json as response content-type due to problem with Swagger 2.9.2. 
	// Fixes issue with calling http(s)://host/digitalforms/v2/api-docs
	// @See https://github.com/springfox/springfox/issues/1835
	@Bean
	public RequestMappingHandlerAdapter requestHandler() {
	    RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	    List<MediaType> mediaTypeList = new ArrayList<>();
	    mediaTypeList.add(MediaType.APPLICATION_JSON);
	    converter.setSupportedMediaTypes(mediaTypeList);
	    adapter.getMessageConverters().add(converter);
	    return adapter;
	}

}
