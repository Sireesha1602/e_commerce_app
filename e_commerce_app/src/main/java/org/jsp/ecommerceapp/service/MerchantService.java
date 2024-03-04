package org.jsp.ecommerceapp.service;


import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.IdNotFoundException;
import org.jsp.ecommerceapp.exception.MerchantNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService
{
	@Autowired
	private MerchantDao dao;
   public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant)
   {
	   ResponseStructure<Merchant> s=new ResponseStructure<>();
	   s.setData(dao.saveMerchant(merchant));
	   s.setMessage("merchant saved");
	   s.setStatusCode(HttpStatus.CREATED.value());
	return new ResponseEntity<ResponseStructure<Merchant>>(s,HttpStatus.CREATED) ;
   }
   
   public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant)
   {
	   ResponseStructure<Merchant> s=new ResponseStructure<>();
	   Optional<Merchant> recMerchant=dao.findById(merchant.getId());
	   if(recMerchant.isPresent())
	   {
		   Merchant dbMerchant=recMerchant.get();
		   dbMerchant.setName(merchant.getName());
		   dbMerchant.setPhone(merchant.getPhone());
		   dbMerchant.setEmail(merchant.getEmail());
		   dbMerchant.setPassword(merchant.getPassword());
		   dbMerchant.setGst_number(merchant.getGst_number());
		   
		   s.setData(dao.saveMerchant(dbMerchant));
		   s.setMessage("merchant updated");
		   s.setStatusCode(HttpStatus.ACCEPTED.value());
		   return  new ResponseEntity<ResponseStructure<Merchant>>(s,HttpStatus.ACCEPTED);
		   
	   }
	   throw new MerchantNotFoundException("merchant not found to update");	   
   }
   public ResponseEntity<ResponseStructure<Merchant>> findById(int id)
   {
	   ResponseStructure<Merchant> s=new ResponseStructure<>();
	   Optional<Merchant> dbMerchant=dao.findById(id);
	   if(dbMerchant.isPresent())
	   {
		   s.setData(dbMerchant.get());
		   s.setMessage("merchant found");
		   s.setStatusCode(HttpStatus.OK.value());
		   return new ResponseEntity<ResponseStructure<Merchant>>(s,HttpStatus.OK);
	   }
	   throw new IdNotFoundException("given id not present");
   }
   
   public ResponseEntity<ResponseStructure<List<Merchant>>> findAll()
   {
	 ResponseStructure<List<Merchant>> s=new ResponseStructure<>();
	 s.setData(dao.findAll());
	 s.setMessage("List of Merchants");
	 s.setStatusCode(HttpStatus.OK.value());
	 
	 return new ResponseEntity<ResponseStructure<List<Merchant>>>(s,HttpStatus.OK);	 			 
   }
   public ResponseEntity<ResponseStructure<String>> delete(int id)
   {
	   ResponseStructure<String> s=new ResponseStructure<>();
	   Optional<Merchant> recMerchant=dao.findById(id);
	   if(recMerchant.isPresent())
	   {
		   dao.delete(id);
		   s.setData("hospital found");
		   s.setMessage("hospital deleted");
		   s.setStatusCode(HttpStatus.OK.value());
		   return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.OK);
	   }
	   throw new IdNotFoundException("given id not present");
			   
		   
			   
   }
   
   
   
   
   
}
