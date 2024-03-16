package org.jsp.Ecommers.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.jsp.Ecommers.Exception.IdNotFoundException;
import org.jsp.Ecommers.Exception.InvalidCredentialsException;
import org.jsp.Ecommers.dao.MerchantDao;
import org.jsp.Ecommers.dto.ResponseStructure;
import org.jsp.Ecommers.model.Merchant;
import org.jsp.Ecommers.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantServices {
	@Autowired
	private MerchantDao dao;
	@Autowired
	private ECommerceAppEmailService emailService;
	public ResponseStructure<Merchant> saveMerchant(Merchant merchant,HttpServletRequest request){
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		
		structure.setData(dao.saveMerchant(merchant));
		
	    String message=emailService.sendWelcomeMail(merchant, request);
		structure.setMessage("Merchant Saved"+","+message);
		structure.setStatusCode(HttpStatus.CREATED.value());
		
		return structure;		
	}
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
		Optional<Merchant> m=dao.findByID(merchant.getId());
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(m.isPresent()) {
			Merchant m1=m.get();
			m1.setEmail(merchant.getEmail());
			m1.setGst_number(merchant.getGst_number());
			m1.setName(merchant.getName());
			m1.setPassword(merchant.getPassword());
			m1.setPhone(merchant.getPhone());
			structure.setMessage("Merchant updated");
			structure.setData(dao.saveMerchant(m1));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException("Merchant Id is not found");
	}
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id){
		Optional<Merchant> m=dao.findByID(id);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(m.isPresent()) {
			structure.setMessage("merchant found");
			structure.setData(m.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
			
		}
		throw new IdNotFoundException("Merchant not found in database");
				
	}
	public ResponseEntity<ResponseStructure<String>> deleteMerchantById(int id){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Merchant> m=dao.findByID(id);
		if(m.isPresent()) {
			structure.setMessage("Merchant found");
			structure.setData("Merchant is deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			dao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
		}
		structure.setMessage("Merchant not found");
		structure.setData("Merchant is not deleted");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(String name){
		ResponseStructure<List<Merchant>> structure=new ResponseStructure<>();
		List<Merchant> merchants=dao.findByName(name);
		structure.setData(merchants);
		if(merchants.size()>0) {
			structure.setMessage("merchants with name "+name);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure,HttpStatus.OK);
		}
		structure.setMessage("there are no merchants with name "+name);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure,HttpStatus.NOT_FOUND);
		
	}
	public ResponseEntity<ResponseStructure<List<Merchant>>> findall(){
		ResponseStructure<List<Merchant>> structure=new ResponseStructure<>();
		List<Merchant> merchants=dao.findAll();
		structure.setData(merchants);
		if(merchants.size()>0) {
			structure.setMessage("merchants");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure,HttpStatus.OK);
		}
		structure.setMessage("No merchants are there");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure,HttpStatus.NOT_FOUND);
		
	}
	public ResponseEntity<ResponseStructure<Merchant>> verify(String email,String password){
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		Optional<Merchant> m=dao.verify(email, password);
		if(m.isPresent()) {
			structure.setData(m.get());
			structure.setMessage("Merchant verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		
		throw new InvalidCredentialsException("email or password is incorrect");
		
	}
	public ResponseEntity<ResponseStructure<Merchant>> verify(long phone,String password){
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		Optional<Merchant> m=dao.verify(phone, password);
		if(m.isPresent()) {
			structure.setData(m.get());
			structure.setMessage("Merchant verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
		}
		
		throw new InvalidCredentialsException("phone number or password is incorrect");
		
	}
	public ResponseEntity<ResponseStructure<String>> active(String token){
		Optional<Merchant> m=dao.findByToken(token);
		ResponseStructure<String> structure=new ResponseStructure<>();
		if(m.isPresent()) {
			Merchant merchant=m.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			dao.saveMerchant(merchant);
			structure.setData("merchant found");
			structure.setMessage("Account verified and activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialsException("Invalid url");
	}
	

}
