package com.revature.services;

import java.util.List;

import com.revature.models.Message;

public interface MessageService {
	
	boolean createMessage(Message message);
	
	boolean getMessageById(int id);

	List<Message> getMessageByAuthor(int author_id);

	List<Message> getMessageByActivity(int activity_id);

	List<Message> getAllMessages();
	
	boolean updateMessage(Message message);
	
	boolean deleteMessage(Message message);
}
