package com.todos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;


import com.todos.repositories.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				
				return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
			}
		};
	}

}
