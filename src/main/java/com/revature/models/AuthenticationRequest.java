package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String pword;
	
	public AuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationRequest(String username, String pword) {
		this.username = username;
		this.pword = pword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", pword=" + pword + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(pword, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationRequest other = (AuthenticationRequest) obj;
		return Objects.equals(pword, other.pword) && Objects.equals(username, other.username);
	}
	
	

}
