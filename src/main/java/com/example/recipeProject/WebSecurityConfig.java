package com.example.recipeProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.recipeProject.service.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled= true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/css/**", "/js/**", "/templates/**").permitAll()
			.and()
			.authorizeRequests().antMatchers("/signup", "/forgotpassword", "/resetpassword", "/saveuser", "/changepassword", "/updatepassword", "/updatepassword/**").permitAll()
			.and()
			.authorizeRequests().antMatchers("/recipes", "/cookingsteps", "/ingredients", "/recipes/**").permitAll() //Allowing REST Service without login.
			.and()
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
		.formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/recipelist")
        	.permitAll()
        	.and()
        .logout()
        	.permitAll()
        	.invalidateHttpSession(true);
	}
}
