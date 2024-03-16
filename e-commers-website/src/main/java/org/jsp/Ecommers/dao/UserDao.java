package org.jsp.Ecommers.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.Ecommers.model.User;
import org.jsp.Ecommers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	public Optional<User> findById(int id){
		return repository.findById(id);
	}
	public List<User> findAll(){
		return repository.findAll();
	}
	public List<User> findByName(String name){
		return repository.findByName(name);
	}
	public Optional<User> verify(long phone,String password){
		return repository.verify(phone, password);
	}
	public Optional<User> verify(String email,String password){
		return repository.verify(email, password);
	}
	public boolean deleteById(int id) {
		Optional<User> user=repository.findById(id);
		if(user.isPresent()) {
			repository.delete(user.get());
			return true;
		}
		return false;
	}
	public Optional<User> findByToken(String token){
		return repository.findByToken(token);
	}

}
