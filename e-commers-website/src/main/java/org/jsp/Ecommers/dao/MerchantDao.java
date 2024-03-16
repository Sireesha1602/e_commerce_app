package org.jsp.Ecommers.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.Ecommers.model.Merchant;
import org.jsp.Ecommers.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository repository;
	public Merchant saveMerchant(Merchant merchant) {
		return repository.save(merchant);
	}
	public Optional<Merchant> findByID(int id) {
		return repository.findById(id);
	}
	public List<Merchant> findAll(){
		return repository.findAll();
	}
	public List<Merchant> findByName(String name){
		return repository.findByName(name);
	}
	public boolean deleteById(int id) {
		Optional<Merchant> m=repository.findById(id);
		if(m.isPresent()) {
			repository.delete(m.get());
			return true;			
		}
		return false;
	}
	public Optional<Merchant> verify(long phone,String password){
		return repository.verify(phone, password);
	}
	public Optional<Merchant> verify(String email,String password){
		return repository.verify(email, password);
	}
	public Optional<Merchant> findByToken(String token){
		return repository.findByToken(token);
	}

}
