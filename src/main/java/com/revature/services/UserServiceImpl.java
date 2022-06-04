package com.revature.services;

import java.util.List;
import java.util.Optional;

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
	
	public UserServiceImpl(UserRepository dao) {
		this.urepo = dao;
	}

	@Override
	public boolean createUser(User user) {
		User tempUser2 = urepo.save(user);
		System.out.println("User received: " + user.toString());
		System.out.println("User returned: " + tempUser2.toString());
		return urepo.save(user) != null ? true : false;
		
	}

	@Override
	public boolean updateUser(User user) {
		if(urepo.save(user) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User findUserByName(String username) {
		Optional<User> user = urepo.findAll()
				.stream()
				.filter(u -> (u.getUsername().equals(username)))
				.findFirst();
		return user.isPresent() ? user.get() : null;
	}
	

	@Override
	public boolean userLogOut(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(int id) {
		System.out.println("Id received: " + id);
		User tempUser = urepo.findById(id).get();
		System.out.println("tempUser: " + tempUser.toString());
		return tempUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = urepo.findAll();
		
		return allUsers;
	}

	@Override
	public boolean deleteUser(User user) {
		urepo.delete(user);
		return true;
	}
}
