package com.todos.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todos.model.JwtUser;
import com.todos.model.StoreToken;
import com.todos.security.JwtGenerator;


@RestController
public class TokenController {
	
	private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }
	
	@RequestMapping("/generate")
	public StoreToken getToken() {
		//@RequestBody final JwtUser jwtUser
		JwtUser jwtUser = new JwtUser();
		jwtUser.setId(100);
		jwtUser.setUserName("Mukesh");
		jwtUser.setRole("admin");
		StoreToken st = new StoreToken();
		st.setToken(jwtGenerator.generate(jwtUser));
		return st;
	}

}
