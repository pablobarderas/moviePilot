package com.app.moviePilot.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.model.register.RegisterData;
import com.app.moviePilot.model.user.User;
import com.app.moviePilot.security.UserSecurity;
/**
 * 
 * @author Marino Burillo
 *
 */
@RestController
public class RegisterValidator {
	@Autowired
	SaveRegisteredUser dataToUser;
	@Autowired
	UserSecurity userSec;
	@PostMapping(value = "/register")
	public ResponseEntity<User> getRegisterData(final @RequestBody RegisterData userToRegister) {	
		RegisterData validatedFields = checkRegex(userToRegister);		
		if(validatedFields==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		return ResponseEntity.ok(encryptData(validatedFields));
	}
	 @DeleteMapping("/{username}")
	    public ResponseEntity<Void> deleteUser(@PathVariable String username) {

	        return ResponseEntity.noContent().build();
	    }
	private RegisterData checkRegex(RegisterData userToRegister) {
		if(!userToRegister.getUsername().matches("\\w{6,18}")) return null;
		if(!userToRegister.getPassword().matches("^(?=.*\\d).{8,}$")) return null;
		if(!userToRegister.getEmail().matches("\\b[a-zA-Z](\\d|\\w|\\.)*@(\\w*\\.\\w{2,}\\.\\w{2,}|\\w*\\.\\w{2,})\\b")) return null;
		return userToRegister;
	}
	private User encryptData(RegisterData d) {
		d.setEmail(userSec.stringToEncrypted(d.getEmail(), d.getUsername()));
		d.setPassword(userSec.stringToEncrypted(d.getPassword(), d.getUsername()));
		return dataToUser.registerDataToUser(d);
	}

	
}
