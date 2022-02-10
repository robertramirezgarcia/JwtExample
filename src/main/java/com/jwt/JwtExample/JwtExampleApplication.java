package com.jwt.JwtExample;

import com.jwt.JwtExample.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class JwtExampleApplication {

	public static void main(String[] args) {

		SpringApplication.run(JwtExampleApplication.class, args);
	}

		@EnableWebSecurity
		@Configuration
		class WebSecurityConfig extends WebSecurityConfigurerAdapter {
				protected void configure(HttpSecurity http) throws Exception{
					http.csrf().disable()
							.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
							.authorizeRequests()
							.antMatchers(HttpMethod.POST, "/user/login").permitAll()
							.anyRequest().authenticated();
				}
			}
	}
