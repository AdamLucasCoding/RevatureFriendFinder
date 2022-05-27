package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Message;
import com.revature.models.ClientMessage;
import com.revature.models.Message;
import com.revature.services.ActivityService;
import com.revature.services.MessageService;
import com.revature.util.ClientMessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/message")
@Api(value = "MessageRestController")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping(path = "/register")
	@ApiOperation(value = "Create a new message entity")
	public @ResponseBody ClientMessage createMessage(@RequestBody Message message) {
		return messageService.create(message) ? ClientMessageUtil.CREATION_SUCCESSFUL
				: ClientMessageUtil.CREATION_FAILED;
	}

	@ApiOperation(value = "Find message by unique id", notes = "Provide an unique id to lookup a specific message", response = Message.class)
	@GetMapping(path = "/{id}")
	public @ResponseBody Message findById(@PathVariable int id) {
		return messageService.findById(id);
	}

	@ApiOperation(value = "Find all messages")
	@GetMapping(path = "/all")
	public @ResponseBody List<Message> findAll() {
		return messageService.findAll();
	}

	@PutMapping(path = "/")
	@ApiOperation(value = "Update an existing message entity")
	public @ResponseBody ClientMessage update(@RequestBody Message message) {
		return messageService.update(message) ? ClientMessageUtil.UPDATE_SUCCESSFUL : ClientMessageUtil.UPDATE_FAILED;
	}

	@DeleteMapping(path = "/")
	@ApiOperation(value = "Delete an existing message entity")
	public @ResponseBody ClientMessage delete(@RequestBody Message message) {
		messageService.delete(message);
		return ClientMessageUtil.DELETION_SUCCESSFUL;
	}

	
}
