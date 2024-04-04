package com.crud.Crud.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.Crud.exception.UserNotFoundException;
import com.crud.Crud.model.user;
import com.crud.Crud.repository.userRepository;

@RestController
@CrossOrigin("http://localhost:3000/")
public class userController {
	
	@Autowired
	private userRepository userRepository;
	
	@PostMapping("/postuser")
	user newUser (@RequestBody user newUser) {
		return userRepository.save(newUser);
		
	}
	
	@GetMapping("/getuser")
	List<user> getAllUser(){
		return userRepository.findAll();
	}
		
		@GetMapping("/user/{id}")
		user getUserByID(@PathVariable Long id) {
			return userRepository.findById(id)
					.orElseThrow(()->new UserNotFoundException(id));
		
	
	}

		@PutMapping("/user/{id}")
		user updateUser(@RequestBody user newUser,@PathVariable Long id) {
			return userRepository.findById(id)
					.map(user->{
						user.setUsername(newUser.getUsername());
						user.setName(newUser.getName());
						user.setEmail(newUser.getEmail());
						return userRepository.save(user);
					}).orElseThrow(()->new UserNotFoundException(id));
		}
		
		@DeleteMapping("/user/{id}")
		String deleteUser(@PathVariable Long id) {
			
			if(!userRepository.existsById(id)) {
				throw new UserNotFoundException(id);
			}
			
			userRepository.deleteById(id);
			
			return "user with id "+id+" has been deleted";
		}
}

