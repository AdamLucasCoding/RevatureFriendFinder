package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Message;
import com.revature.repositories.MessageRepository;

@Service
@Transactional
public class MessageServiceIMpl implements MessageService {

	@Autowired
	private MessageRepository messageRepo;

	@Override
	public boolean create(Message message) {
		return messageRepo.save(message) != null;
	}

	@Override
	public Message findById(int id) {
		Optional<Message> message = messageRepo.findById(id);
		return message.isPresent() ? message.get() : null;
	}

	@Override
	public List<Message> findAll() {
		return messageRepo.findAll();
	}

	@Override
	public boolean update(Message message) {
		return messageRepo.save(message) != null;
	}

	@Override
	public void delete(Message message) {
		messageRepo.delete(message);
	}

}
