package com.revature.models;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	public User() {
		super();
	}

	public User(String userName, String password, String email) {
		super();
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public User(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.username = userName;
		this.password = password;
		this.email = email;
	}
}
