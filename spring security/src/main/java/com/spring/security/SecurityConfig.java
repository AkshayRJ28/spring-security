package com.spring.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	   
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) ->
		requests.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated());
		//http.formLogin(withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailService() {
		UserDetails user1 = User.withUsername("user1")
				.password("{noop}userPass")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withUsername("admin1")
				.password("{noop}adminPass")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user1,admin);
	}

}
