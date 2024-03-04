package org.jsp.ecommerceapp.exception;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.http.ResponseEntity;

public class MerchantNotFoundException extends RuntimeException
{
   public MerchantNotFoundException(String message)
   {
	   super(message);
   }
}
