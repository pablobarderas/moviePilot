package com.app.moviePilot.controller.register;

import com.app.moviePilot.model.register.UserRegisterDTO;
/**
 * 
 * @author Marino Burillo
 *
 */
public abstract class RegisterValidator {
	public static UserRegisterDTO checkRegex(UserRegisterDTO userToRegister) {
		if (!userToRegister.getUsername().matches("\\w{6,18}"))
			return null;
		if (!userToRegister.getPassword().matches("^(?=.*\\d).{8,}$"))
			return null;
		if (!userToRegister.getEmail()
				.matches("\\b[a-zA-Z](\\d|\\w|\\.)*@\\w+(\\.\\w{2,}\\.\\w{2,}|\\.\\w{2,})\\b"))
			return null;
		return userToRegister;
	}

}
