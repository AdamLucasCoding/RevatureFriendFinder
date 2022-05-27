package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.Message;

public interface MessageService {

	boolean create(Message message);

	Message findById(int id);

	List<Message> findAll();

	boolean update(Message message);

	void delete(Message message);

}
