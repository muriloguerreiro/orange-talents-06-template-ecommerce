package br.com.zupacademy.murilo.mercadolivre.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		 .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
		 .antMatchers(HttpMethod.POST, "/categorias").permitAll()
		 .and().csrf().disable();
	}

}
