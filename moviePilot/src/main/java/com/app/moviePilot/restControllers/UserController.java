package com.app.moviePilot.restControllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.moviePilot.controller.register.UserImageManager;
import com.app.moviePilot.config.GsonDeserializerBuilder;
import com.app.moviePilot.controller.register.RegisterValidator;
import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.model.register.UserUpdateDTO;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;
import com.app.moviePilot.model.user.roles.Role;
import com.app.moviePilot.repository.ActiveUserRepository;
import com.app.moviePilot.security.JwtUtils;
import com.app.moviePilot.security.UserSecurity;
import com.app.moviePilot.services.DeletedUserService;
import com.app.moviePilot.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 
 * @author Marino Burillo
 *
 */
@RestController
public class UserController {
	@Autowired
	UserService dataToUser;
	@Autowired
	DeletedUserService deletedUserSer;
	@Autowired
	UserSecurity userSec;
	
	/**
	 * 
	 * @param request, the header of the HTTP request
	 * @param userToRegister
	 * @return a JWT as a String
	 */
	@PostMapping(value = "/user/register",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getRegisterData(final HttpServletRequest request,final @RequestBody UserRegisterDTO userToRegister) {
		String origin = request.getHeader("Origin");
		UserRegisterDTO validatedFields = RegisterValidator.checkRegex(userToRegister);
		if (dataToUser.getUser(userToRegister.getUsername()) != null 
			||	deletedUserSer.getDeletedUser(userToRegister.getUsername())!=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		if (validatedFields == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		ActiveUser registeredUser = dataToUser.registerDataToUser(userSec.encryptData(validatedFields));
		if (registeredUser == null) ResponseEntity.noContent();	
		return ResponseEntity.ok(JwtUtils.generateToken(registeredUser));
	}
	/**
	 * 
	 * @param authorizationHeader
	 * @param username
	 * @param password
	 * @return JWT as String
	 */
	@PostMapping(value = "/user/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(final @RequestParam("username") String username,
			final @RequestParam("password") String password) {
			ActiveUser u = new ActiveUser();
			u.setUsername(username);
			u.setPassword(password);
			u.setPassword(userSec.decryptString(u));
			ActiveUser loggedUser = dataToUser.checkLogin(u);
			if (loggedUser == null) return ResponseEntity.badRequest().body(null);
			return ResponseEntity.ok(JwtUtils.generateToken(loggedUser));
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
		ActiveUser userToFind = dataToUser.getUser(username);
		if (userToFind != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userToFind);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping("user/update")
	public ResponseEntity<User> getUserToUpdate(@RequestParam("image") final MultipartFile file, @RequestParam("user") final String userJson) {
		Gson gson = GsonDeserializerBuilder.getDeserializedGson();
		UserUpdateDTO userToUpdate = gson.fromJson(userJson, UserUpdateDTO.class);
		UserUpdateDTO validatedFields = (UserUpdateDTO) RegisterValidator.checkRegex(userToUpdate);
		if (dataToUser.getUser(userToUpdate.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}			
		if (validatedFields == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
		if(file!=null) UserImageManager.saveImage(file);
		User updatedUser = dataToUser.updateUser((UserUpdateDTO) userSec.encryptData(validatedFields));
		if (updatedUser == null) ResponseEntity.noContent();			
		return ResponseEntity.ok(updatedUser);
	}
	
	@PostMapping("/toadmin/{adminUsername}")
	public ResponseEntity<User> makeUserAdmin(final @PathVariable String adminUsername, final @RequestBody String userToMakeAdmin) {
		ActiveUser admin = dataToUser.getUser(adminUsername);
		ActiveUser normalUser = dataToUser.getUser(userToMakeAdmin);
		if(admin == null || normalUser == null || admin.getRole().equals(Role.NORMAL_USER)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		User updatedUser = dataToUser.makeUserAdmin(normalUser);
		return ResponseEntity.ok(updatedUser);
	}
	
	@PostMapping(value = "/user/registerJwt",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> test(final @RequestBody UserRegisterDTO userToRegister) {
		ActiveUser u = new ActiveUser();
		u.setUsername(userToRegister.getUsername());
		u.setPassword(userToRegister.getPassword());
		u.setEmail(userToRegister.getEmail());
		
		return ResponseEntity.ok(JwtUtils.generateToken(u));
	}
	@PostMapping(value = "/user/loginJwt",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String testLogin(@RequestHeader("Authorization") String authorizationHeader,final @RequestBody UserRegisterDTO userToRegister) {
		 String token = authorizationHeader.replace("Bearer ", "");
	        boolean isValidToken = JwtUtils.verifyAndExtractData(token);
	        if (isValidToken) {
	            return "Token válido";
	        } else {
	            return "Token inválido";
	        }
	}
}
