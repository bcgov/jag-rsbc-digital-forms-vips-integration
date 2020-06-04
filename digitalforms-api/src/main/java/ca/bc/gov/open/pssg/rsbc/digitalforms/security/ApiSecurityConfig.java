package ca.bc.gov.open.pssg.rsbc.digitalforms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ca.bc.gov.open.pssg.rsbc.digitalforms.config.ConfigProperties;

/**
 * 
 * This class enforces Basic Auth on all API operations.  
 * 
 * It is assumed all API operations are prefixed with /api/*	   (Basic Auth)
 * It is assumed all Actuator calls are prefixed with /actuator/*  (No security)
 * 
 * @author shaunmillargov
 *
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ConfigProperties properties;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
			.antMatchers("/api/**").authenticated()
			.antMatchers("/actuator/**").permitAll()
			.and()
			.httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(properties.getBasicAuthUser())
			.password("{noop}" + properties.getBasicAuthPassword())
			.roles("USER");
	}
}
