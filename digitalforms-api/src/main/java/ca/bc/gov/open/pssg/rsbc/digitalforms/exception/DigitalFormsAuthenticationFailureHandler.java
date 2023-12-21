package ca.bc.gov.open.pssg.rsbc.digitalforms.exception;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsConstants;
import ca.bc.gov.open.pssg.rsbc.digitalforms.util.DigitalFormsUtils;

/**
 * Authentication failure response handler
 * 
 * @author sivakaruna
 */
@Component
public class DigitalFormsAuthenticationFailureHandler extends BasicAuthenticationEntryPoint  {
	
	private final Logger logger = LoggerFactory.getLogger(DigitalFormsAuthenticationFailureHandler.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		
		logger.debug("API basic authentication failed");
		response.setContentType(DigitalFormsConstants.JSON_CONTENT);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream()
				.print(DigitalFormsUtils.buildErrorResponse(DigitalFormsConstants.UNAUTHORIZED_ERROR, 401).toString());
	}
	
	@Override
    public void afterPropertiesSet() {
        setRealmName("DigitalForms");
        super.afterPropertiesSet();
    }

}
