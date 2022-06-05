package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.ClientMessage;
import com.revature.models.Message;
import com.revature.services.MessageService;
import com.revature.util.ClientMessageUtil;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	@Lazy
	private MessageService messageService;
	
	@PostMapping(path="/message/createmessage")
	public @ResponseBody ClientMessage createMessage(@RequestBody Message message) {
		System.out.println("@MessageController - createMessage: " + message.toString());
		return messageService.createMessage(message) ? ClientMessageUtil.CREATION_SUCCESSFUL : ClientMessageUtil.CREATION_FAILED;
	}
	
	// get all msgs
	@GetMapping(path="/message/all")
	public @ResponseBody List<Message> allMessages(){
		return messageService.getAllMessages();
	}
	
	@GetMapping(path="/message")
	public @ResponseBody Message messagesById(@RequestParam(value = "id", name = "id") int id) {
		return messageService.getMessageById(id);
	}
	
	@GetMapping(path="/message/byactivity")
	public @ResponseBody List<Message> messagesByActivity(@RequestBody int activity_id) {
		return messageService.getMessageByActivity(activity_id);
	}
	
	@GetMapping(path="/message/byuserid")
	public @ResponseBody List<Message> messagesByUser(@RequestBody int user_id) {
		return messageService.getMessageByAuthor(user_id);
	}
	
	@PutMapping(path = "/message/update")
	public @ResponseBody ClientMessage updateMessage(@RequestBody Message message) {
		return messageService.updateMessage(message) ? ClientMessageUtil.UPDATE_SUCCESSFUL : ClientMessageUtil.UPDATE_FAILED;
	}
	
	@DeleteMapping(path = "/message/delete")
	public @ResponseBody ClientMessage deleteMessage(@RequestBody Message message) {
		return messageService.deleteMessage(message) ? ClientMessageUtil.DELETION_SUCCESSFUL : ClientMessageUtil.DELETION_SUCCESSFUL;
	}
}
