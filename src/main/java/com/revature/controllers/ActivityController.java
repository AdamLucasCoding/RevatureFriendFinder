package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.services.ActivityService;

@RestController
@RequestMapping("/api")
public class ActivityController {
	
	@Autowired
	private ActivityService actionService;
	
	//create
	
	//modify
	//search
	//delete

}
