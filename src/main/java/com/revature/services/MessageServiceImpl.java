package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Message;
import com.revature.repositories.MessageRepository;

@RestController
@RequestMapping("/api")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository mrepo;
	
	@Override
	public boolean createMessage(Message message) {
		if(mrepo.save(message) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Message> getMessageByAuthor(int author_id) {
		return mrepo.findByUser(author_id);
	}

	@Override
	public List<Message> getMessageByActivity(int activity_id) {
		return mrepo.findByActivity(activity_id);
	}

	@Override
	public List<Message> getAllMessages() {
		return mrepo.findAll();
	}

	@Override
	public boolean getMessageById(int id) {
		if(mrepo.findById(id).get() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateMessage(Message message) {
		if(mrepo.save(message) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteMessage(Message message) {
		mrepo.delete(message);
		return true;
	}

}
