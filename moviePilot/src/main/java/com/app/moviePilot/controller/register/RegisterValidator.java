package com.app.moviePilot.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.model.register.RegisterData;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;
import com.app.moviePilot.repository.ActiveUserRepository;
import com.app.moviePilot.security.UserSecurity;
/**
 * 
 * @author Marino Burillo
 *
 */
@Controller
public class RegisterValidator {
	@Autowired
	SaveRegisteredUser dataToUser;
	@Autowired
	UserSecurity userSec;
	@Autowired
	ActiveUserRepository userRepo;
	@PostMapping(value = "/user/register")
	public ResponseEntity<User> getRegisterData(final @RequestBody RegisterData userToRegister) {
		RegisterData validatedFields = checkRegex(userToRegister);	
		if(userRepo.findByUsername(userToRegister.getUsername())!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		if(validatedFields==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		User registeredUser = encryptData(validatedFields);
		if(registeredUser==null) ResponseEntity.noContent();
		return ResponseEntity.ok(registeredUser);
	}
	 @DeleteMapping("/{username}")
	    public ResponseEntity<User> deleteUser(@PathVariable String username) {
			ActiveUser userToDelete = userRepo.findByUsername(username);
			if (userToDelete != null) {
				userRepo.delete(userToDelete);
			} else {
				return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
			}			
			return new ResponseEntity<User>(userToDelete, HttpStatus.OK);
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
