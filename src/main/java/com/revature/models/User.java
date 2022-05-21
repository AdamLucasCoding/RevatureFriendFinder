package com.revature.models;


import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="u_id")
	private int userId;
	
	@Column(name="u_username", unique=true, nullable=false)
	private String username;
	
	@Column(name="u_password", nullable=false)
	private String password;
	
	@Column(name="u_email", unique=true, nullable=false)
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

	public User(int userId, String userName, String password, String email) {
		super();
		this.userId = userId;
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}
}
