package ca.bc.gov.open.pssg.rsbc.digitalforms.security;

import ca.bc.gov.open.pssg.rsbc.digitalforms.dao.VipswsDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;
import ca.bc.gov.open.pssg.rsbc.digitalforms.exception.DigitalFormsAuthenticationFailureHandler;

/**
 * 
 * This class enforces Basic Auth on all API operations.  
 * 
 * It is assumed all Actuator calls are prefixed with /actuator/*  (No security)
 * 
 * @author shaunmillargov
 *
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ConfigProperties.class)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String[] AUTH_WHITELIST = {

            // -- Non protected Swagger ui, actuator endpoints. 
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/actuator/**"
    };
	
	
	@Autowired
	private ConfigProperties properties;
	
	@Autowired
	private DigitalFormsAuthenticationFailureHandler authenticationFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Stateless session validates basic auth credentials for each request
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.csrf().disable().authorizeRequests()
			.antMatchers(AUTH_WHITELIST).permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic();
		
		// Authentication failure error response handler
		http.exceptionHandling().authenticationEntryPoint(authenticationFailureHandler);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(properties.getBasicAuthUser())
			.password(passwordEncoder().encode(properties.getBasicAuthPassword()))
			.roles("USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
