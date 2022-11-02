package com.recipemanager.cookingrecipe.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * Class to provide basic auth
 * @author Akanksha
 *
 */
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	private static final String API_RECIPE = "/api/recipe/**";
	private String admin = "ADMIN";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles(admin);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, API_RECIPE).hasRole(admin)
				.antMatchers(HttpMethod.POST, "/api/recipe").hasRole(admin).antMatchers(HttpMethod.PUT, API_RECIPE)
				.hasRole(admin).antMatchers(HttpMethod.DELETE, API_RECIPE).hasRole(admin).and().csrf().disable().formLogin()
				.disable();
	}

}
