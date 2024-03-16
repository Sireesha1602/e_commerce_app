package org.jsp.Ecommers.controller;

import java.util.List;

import org.jsp.Ecommers.dto.ResponseStructure;
import org.jsp.Ecommers.model.Merchant;
import org.jsp.Ecommers.services.MerchantServices;
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
@RequestMapping(value="/merchants")
@CrossOrigin
public class MerchantCotroller {
	@Autowired
	private MerchantServices service;
	@PostMapping	
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant, HttpServletRequest request){
		return service.saveMerchant(merchant,request);
		
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant){
		return service.updateMerchant(merchant);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable(name="id") int id){
		return service.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name="id") int id){
		return service.deleteMerchantById(id);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll(){
		return service.findall();
		
	}
	@GetMapping(value= "/name/{name}")
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(@PathVariable(name="name") String name){
		return service.findByName(name);
	}
	@GetMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verify(@RequestParam(name="phone") long phone,@RequestParam(name="password") String password ){
		return service.verify(phone, password);
	}
	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verify(@RequestParam(name="email") String email,@RequestParam(name="password") String password ){
		return service.verify(email, password);
	}
	@GetMapping("/active")
	public ResponseEntity<ResponseStructure<String>> active(@RequestParam String token){
		return service.active(token);
	}

}
