package com.trade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;





@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private static final String[] permitted_url = {
			"/actuator/**",
			"/proxy/**",
			"/hystrix/**",
			"/v2/api-docs",
			"/configuration/ui",
			"/swagger-resources/**",
			"/configuration/**",
			"/swagger-ui.html",
			"/webjars/**",
			"/api/**",
			"/static/**",
			"/css/**",
			"/js/**",
			"/images/**"
    };

	private static final String LOGIN_INVALID_SESSION_URL="/login";
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
    
     
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(permitted_url);
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/*.antMatchers("/").permitAll()*/
		//.antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
		.antMatchers(permitted_url).permitAll()
		.antMatchers("/").hasAnyRole()
		.anyRequest().authenticated().and().formLogin()
		.permitAll().and().logout().permitAll()
		.and()
        .exceptionHandling();
  		http.sessionManagement()
		.maximumSessions(1)
		.expiredUrl(LOGIN_INVALID_SESSION_URL)
		.maxSessionsPreventsLogin(false);
		/*http.csrf().disable();*/
		
	}

	

	


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}


	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}


	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}







}
