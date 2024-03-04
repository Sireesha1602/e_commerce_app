package org.jsp.ecommerceapp.exception;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler 
{
  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException e)
  {
	  ResponseStructure<String> s=new ResponseStructure<>();
	  s.setData("null");
	  s.setMessage(e.getMessage());
	  s.setStatusCode(HttpStatus.NOT_FOUND.value());
	  return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(MerchantNotFoundException.class)
  public ResponseEntity<ResponseStructure<String>> handleMNFE(IdNotFoundException e)
  {
	  ResponseStructure<String> s=new ResponseStructure<>();
	  s.setData("null");
	  s.setMessage(e.getMessage());
	  s.setStatusCode(HttpStatus.NOT_FOUND.value());
	  return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
  }
}
