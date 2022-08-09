package com.mahesh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurity extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
	http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.inMemoryAuthentication().withUser("TATA").password(passwordEncoder().encode("TATA")).roles("ÄDMIN");
    	auth.inMemoryAuthentication().withUser("tatauser").password(passwordEncoder().encode("tatauser")).roles("USER");
    }
    
    @Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(10);
	}

}
