package org.jsp.Ecommers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String gender;
	@Column(nullable=false)
	private int age;
	@Column(nullable=false,unique=true)
	private long phone;
	@Column(nullable=false,unique=true)
	private String email;
	@Column(nullable=false)
	private String password;
	private String token;
	private String status;
}
