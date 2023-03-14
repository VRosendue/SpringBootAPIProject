package com.boxinator.project.services;

import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.boxinator.models.Users;
import com.boxinator.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	

	public ResponseEntity<Users> getUserById(Long id) {
		HttpStatus httpStatus = null;
		Users returnUser = null;

		try {
			if ((returnUser = userRepo.getReferenceById(id)) != null) {
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnUser, httpStatus);
	}
	
	
	public ResponseEntity<Users> createUser(Users newUser) {
		HttpStatus httpStatus = null;
		Users returnUser = null;
		Long id = newUser.getUserId();

		try {
			if (userRepo.existsById(id) == false) {
				returnUser = userRepo.saveAndFlush(newUser);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnUser, httpStatus);
	}

	public ResponseEntity<Users> deleteUser(Long id) {
		HttpStatus httpStatus = null;
		Users returnUser = null;

		try {
			if((returnUser = userRepo.getReferenceById(id)) != null) {
				userRepo.deleteById(id);
				httpStatus = HttpStatus.OK;
			}else {
				httpStatus = HttpStatus.BAD_GATEWAY;
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(returnUser, httpStatus);
	}
	
	public ResponseEntity<List<Users>> getAllUsers(){
		List<Users> returnUser = null;
		HttpStatus httpStatus = null;
		
		try {
			returnUser = userRepo.findAll();
			if (returnUser.isEmpty()) {
				httpStatus = HttpStatus.NOT_FOUND;
			}else {
				httpStatus = HttpStatus.OK;
			}
		} catch (Exception e) {
			System.out.println("The house is on fire...");
			System.out.println(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(returnUser, httpStatus);
	}
	
	public ResponseEntity<Users> updateUser(Users newUser, Long id){
		HttpStatus httpStatus = null;
		Users returnUser = null;
		
		try {
			if((returnUser = userRepo.getReferenceById(id))!= null) {
				newUser = (Users)HelperService.partialUpdate(httpStatus, returnUser);
				returnUser = userRepo.saveAndFlush(newUser);
				httpStatus = HttpStatus.OK;
			}else {
				httpStatus = HttpStatus.BAD_GATEWAY;
			}
		} catch (BeansException e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("House is on fire.");
			System.out.println(e.getMessage());		}
		return new ResponseEntity<>(returnUser, httpStatus);
	}
}
