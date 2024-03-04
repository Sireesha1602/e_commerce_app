package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao
{
   @Autowired
   private MerchantRepository repo;
     
   public Merchant saveMerchant(Merchant merchant)
   {
	   return repo.save(merchant);
   }
   
   public Merchant updateMerchant(Merchant merchant)
   {
	   return repo.save(merchant);
   }
   
   public Optional<Merchant> findById(int id)
   {
	   return repo.findById(id);
   }
   
   public List<Merchant> findAll()
   {
	   return repo.findAll();
   }
   
   public boolean delete(int id)
   {
	   Optional<Merchant> dbMerchant=repo.findById(id);
	   
	   if(dbMerchant.isPresent())
	   {
		   repo.delete(dbMerchant.get());
		   return true;
	   }
	   return false;
	   
   }
}
