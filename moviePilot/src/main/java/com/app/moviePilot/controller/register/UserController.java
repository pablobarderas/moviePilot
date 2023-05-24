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

import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.model.register.UserUpdateDTO;
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
public class UserController {
	@Autowired
	UserService dataToUser;
	@Autowired
	DeletedUserService deletedUserSer;
	@Autowired
	UserSecurity userSec;
	@Autowired
	ActiveUserRepository userRepo;
	
	@PostMapping(value = "/user/register")
	public ResponseEntity<User> getRegisterData(final @RequestBody UserRegisterDTO userToRegister) {
		UserRegisterDTO validatedFields = RegisterValidator.checkRegex(userToRegister);
		if (dataToUser.getUser(userToRegister.getUsername()) != null 
			||	deletedUserSer.getDeletedUser(userToRegister.getUsername())!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		if (validatedFields == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		User registeredUser = dataToUser.registerDataToUser(userSec.encryptData(validatedFields));
		if (registeredUser == null) ResponseEntity.noContent();			
		return ResponseEntity.ok(registeredUser);
	}

	@DeleteMapping("/delete/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable String username) {
		ActiveUser userToDelete = dataToUser.getUser(username);
		if (userToDelete != null) {
			dataToUser.deleteUser(userToDelete);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(userToDelete);
	}
	
	@GetMapping("user/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username){
		ActiveUser userToFind = userRepo.findByUsername(username);
		if (userToFind != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userToFind);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping("user/update")
	public ResponseEntity<User> getUserToUpdate(final @RequestBody UserUpdateDTO userToUpdate) {
		UserRegisterDTO validatedFields = RegisterValidator.checkRegex(userToUpdate);
		if (userRepo.findByUsername(userToUpdate.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		if (validatedFields == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		User updatedUser = dataToUser.updateUser((UserUpdateDTO) userSec.encryptData(validatedFields));
		if (updatedUser == null) ResponseEntity.noContent();			
		return ResponseEntity.ok(updatedUser);
	}
	

}
