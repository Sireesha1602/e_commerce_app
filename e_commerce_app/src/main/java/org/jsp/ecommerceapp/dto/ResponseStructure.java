package org.jsp.ecommerceapp.dto;

public class ResponseStructure<S> 
{
 private S data;
 private String message;
 private int statusCode;
 
 

public S getData() {
	return data;
}
public void setData(S data) {
	this.data = data;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getStatusCode() {
	return statusCode;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}
 
 
  
}
