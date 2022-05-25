package com.revature.services;

import java.util.List;

import com.revature.models.Message;

public interface MessageService {
	
	Message createMessage(Message message);

	List<Message> getMessageByAuthor(int author_id);

	List<Message> getMessageByActivity(int activity_id);

	List<Message> getAllMessages();
	
}
