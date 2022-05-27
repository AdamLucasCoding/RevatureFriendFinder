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
	
	@Override
	public boolean create(User user) {
		return urepo.save(user) != null;
	}

	@Override
	public boolean update(User user) {
		return urepo.save(user) != null;
	}

	@Override
	public User logIn(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logOut(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(int id) {
		Optional<User> user = urepo.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public List<User> findAll() {
		return urepo.findAll();
	}

	@Override
	public boolean delete(User user) {
		urepo.delete(user);
		return true;
	}
}
