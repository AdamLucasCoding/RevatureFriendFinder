package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository urepo;
	
	@Override
	public User createUser(User user) {
		return urepo.save(user);
	}

	@Override
	public User updateUser(User user) {
//		User target = urepo.findById(user.getId()).stream().findFirst().get();
//		target.setUsername(user.getUsername());
//		target.setPassword(user.getPassword());
//		target.setEmail(user.getEmail());
		return urepo.save(user);
	}

	@Override
	public User userLogIn(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userLogOut(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(int id) {
		//return urepo.findById(id).stream().findFirst().get();
		System.out.println("ID: " + id);
		
		return urepo.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return urepo.findAll();
	}

	@Override
	public boolean deleteUser(User user) {
		urepo.delete(user);
		return true;
	}
}
