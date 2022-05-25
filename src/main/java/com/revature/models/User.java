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
	
	@Column(name="pword", nullable=false)
	private String pword;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	public User() {
		super();
	}

	public User(String userName, String password, String email) {
		super();
		this.username = userName;
		this.pword = password;
		this.email = email;
	}

	public User(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.username = userName;
		this.pword = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pword=" + pword + ", email=" + email + "]";
	}
	
	
}
