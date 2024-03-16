package org.jsp.Ecommers.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.jsp.Ecommers.Exception.InvalidCredentialsException;
import org.jsp.Ecommers.Exception.UserIdNotFoundException;
import org.jsp.Ecommers.Exception.UserInvalidCredientialsException;
import org.jsp.Ecommers.dao.UserDao;
import org.jsp.Ecommers.dto.ResponseStructure;
import org.jsp.Ecommers.model.User;
import org.jsp.Ecommers.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class UserServices {
	@Autowired
	private UserDao dao;
	@Autowired
	private EmailServiceUser emailService;

	public ResponseStructure<User> saveUser(User user, HttpServletRequest request) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(45));
		structure.setMessage("User is saved successfully");
		structure.setData(dao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		String message = emailService.sendWelcomeMail(user, request);
		return structure;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		Optional<User> u = dao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (u.isPresent()) {
			User u1 = u.get();
			u1.setAge(user.getAge());
			u1.setEmail(user.getEmail());
			u1.setGender(user.getGender());
			u1.setName(user.getName());
			u1.setPhone(user.getPhone());
			dao.saveUser(u1);
			structure.setMessage("user profile updated");
			structure.setData(u1);
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new UserInvalidCredientialsException("User id not found in the database");
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> u = dao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (u.isPresent()) {
			structure.setMessage("User Found");
			structure.setData(u.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new UserIdNotFoundException("User id not found in the database");

	}

	public ResponseEntity<ResponseStructure<String>> deleteUserById(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> m = dao.findById(id);
		if (m.isPresent()) {
			structure.setMessage("User found");
			structure.setData("User is deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			dao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("User not found");
		structure.setData("User is not deleted");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<List<User>>> findByName(String name) {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> Users = dao.findByName(name);
		structure.setData(Users);
		if (Users.size() > 0) {
			structure.setMessage("Users with name " + name);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("there are no Users with name " + name);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<List<User>>> findall() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> Users = dao.findAll();
		structure.setData(Users);
		if (Users.size() > 0) {
			structure.setMessage("Users");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("No Users are there");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseStructure<User>> verify(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> m = dao.verify(email, password);
		if (m.isPresent()) {
			structure.setData(m.get());
			structure.setMessage("User verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}

		throw new InvalidCredentialsException("email or password is incorrect");

	}

	public ResponseEntity<ResponseStructure<User>> verify(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> m = dao.verify(phone, password);
		if (m.isPresent()) {
			structure.setData(m.get());
			structure.setMessage("User verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}

		throw new InvalidCredentialsException("phone number or password is incorrect");

	}
	public ResponseEntity<ResponseStructure<String>> active(String token){
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<User> u=dao.findByToken(token);
		if(u.isPresent()) {
			User u1=u.get();
			u1.setToken(null);
			u1.setStatus(AccountStatus.ACTIVE.toString());
			dao.saveUser(u1);
			structure.setMessage("User found");
			structure.setData("User verified and activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new UserIdNotFoundException("User not found with the token");
		
	}
}