package com.app.moviePilot.controller.register;

import org.springframework.stereotype.Component;

import com.app.moviePilot.model.register.UserRegisterDTO;
@Component
public class RegisterValidator {
	public UserRegisterDTO checkRegex(UserRegisterDTO userToRegister) {
		if (!userToRegister.getUsername().matches("\\w{6,18}"))
			return null;
		if (!userToRegister.getPassword().matches("^(?=.*\\d).{8,}$"))
			return null;
		if (!userToRegister.getEmail()
				.matches("\\b[a-zA-Z](\\d|\\w|\\.)*@(\\w*\\.\\w{2,}\\.\\w{2,}|\\w*\\.\\w{2,})\\b"))
			return null;
		return userToRegister;
	}

}
