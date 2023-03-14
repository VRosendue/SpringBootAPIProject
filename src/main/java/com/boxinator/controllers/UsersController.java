package com.boxinator.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boxinator.models.Users;
import com.boxinator.project.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

	@Autowired
	UserService userService;


	@GetMapping("/{userId}")
	public ResponseEntity<Users> getUserById(@PathVariable long userId) {
		return userService.getUserById(userId);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Users>> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<Users> addUser(@RequestBody Users newUser) {
		return userService.createUser(newUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable(value="id")Long id){
		return userService.deleteUser(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Users>updateUser(@PathVariable(value="id")Long id, @Validated @RequestBody Users newUser){
		return userService.updateUser(newUser, id);
	}
}
