package org.jsp.Ecommers.controller;

import java.util.List;

import org.jsp.Ecommers.dto.ResponseStructure;
import org.jsp.Ecommers.model.User;
import org.jsp.Ecommers.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/users")
@CrossOrigin
public class UserController {
	@Autowired
	private UserServices service;
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseStructure<User> saveUser(@RequestBody User User,HttpServletRequest request){
		return service.saveUser(User,request);
		
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User User){
		return service.updateUser(User);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name="id") int id){
		return service.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name="id") int id){
		return service.deleteUserById(id);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
		return service.findall();
		
	}
	@GetMapping(value= "/name/{name}")
	public ResponseEntity<ResponseStructure<List<User>>> findByName(@PathVariable(name="name") String name){
		return service.findByName(name);
	}
	@PostMapping(value="/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam(name="phone") long phone,@RequestParam(name="password") String password ){
		return service.verify(phone, password);
	}
	@PostMapping(value="/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam(name="email") String email,@RequestParam(name="password") String password ){
		return service.verify(email, password);
	}
	@GetMapping(value="/active")
	public ResponseEntity<ResponseStructure<String>> active(@RequestParam String token){
		return service.active(token);		
	}

}
