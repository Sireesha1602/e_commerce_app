package org.jsp.ecommerceapp.controller;

import java.util.List;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchants")
public class MerchantController 
{
  @Autowired
  private MerchantService service;
  
  @PostMapping
  public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant)
  {
	  return service.saveMerchant(merchant);
  }
  
  @PutMapping
  public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant)
  {
	  return service.updateMerchant(merchant);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable int id)
  {
	  return service.findById(id);
  }
  @GetMapping
  public ResponseEntity<ResponseStructure<List<Merchant>>> findAll()
  {
	  return service.findAll();
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseStructure<String>> delete(@PathVariable int id)
  {
	  return service.delete(id);
  }
   
}
