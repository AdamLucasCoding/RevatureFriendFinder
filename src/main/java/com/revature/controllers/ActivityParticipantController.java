package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.ActivityParticipant;
import com.revature.services.ActivityParticipantService;

@RestController
@RequestMapping("/api")
public class ActivityParticipantController {
	
	@Autowired
	private ActivityParticipantService apService;
	
	@PostMapping(path="/createap")
	public @ResponseBody ActivityParticipant creatAp(@RequestBody ActivityParticipant ap) {
		return apService.createAp(ap);
	}
	
	@GetMapping(path="/ap/byactivity")
	public @ResponseBody List<ActivityParticipant> apByActivity(@RequestBody int activity_id) {
		return apService.getApByActivity(activity_id);
	}
	
	@GetMapping(path="/ap/byuser")
	public @ResponseBody List<ActivityParticipant> apByUser(@RequestBody int user_id) {
		return apService.getApByUser(user_id);
	}
	
	@PutMapping(path="/user/update")
	public @ResponseBody ActivityParticipant  updateAp(@RequestBody ActivityParticipant ap) {
		return apService.updateAp(ap);
	}
	
	@DeleteMapping(path="/user/delete")
	public @ResponseBody boolean deleteAp(@RequestBody ActivityParticipant ap) {
		return apService.deleteAp(ap);
	}
	
}
