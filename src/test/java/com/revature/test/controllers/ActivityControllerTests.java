package com.revature.test.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.ActivityController;
import com.revature.models.Activity;
import com.revature.models.User;
import com.revature.services.ActivityService;
import com.revature.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ActivityController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActivityControllerTests {
	
	private static Activity mockActivity1;
	private static Activity mockActivity2;
	private static Activity mockActivityCreation;
	private static Activity mockActivityModification;
	private static Activity mockActivityDeletion;
	private static List<Activity> dummyDb;
	
	ObjectMapper om = new ObjectMapper()		
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	
	@Autowired
	ActivityController activityController;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private ActivityService service;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: building test objects...");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
	    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
		
		mockActivity1 = new Activity(1, "Fighting","Hobby", "Atlanta", createdDate, activityDate, new User(1,"satyanadala1","password","satyanadala1@microsoft.com"), 10);
		mockActivity2 = new Activity(2, "Cooking","Hobby", "Atlanta", createdDate, activityDate, new User(2,"bineeshraghavan","password","bineeshraghavan@facebook.com"), 10);
		
		
		mockActivityCreation = new Activity("Gardening","Hobby", "Columbus", createdDate, activityDate, new User(2,"bineeshraghavan","password","bineeshraghavan@facebook.com"), 5);
		
		mockActivityModification = mockActivityCreation;
		mockActivityModification.setLocation("Atlanta");
		mockActivityModification.setOccupancyMax(12);
		
		mockActivityDeletion = new Activity(4, "Singing","Hobby", "Atlanta", createdDate, activityDate,new User(2,"vidyabineesh","password","vidyabineesh@yahoo.com"), 10);
		

		dummyDb = new ArrayList<>();
		dummyDb.add(mockActivity1);
		dummyDb.add(mockActivity2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(activityController).isNotNull();
	}

	@Test
	@Order(2)
	@DisplayName("2. Create Activity - Happy Path Scenerio Test")
	public void testCreateActivity() throws Exception {
		// id number of this creation should be 3
		mockActivityCreation.setId(3);
		//tell Mockito the behavior that I want this method to act like in the mock environment
		when(service.createActivity(mockActivityCreation)).thenReturn(true);
		
		//act
		RequestBuilder request = MockMvcRequestBuilders.post("/api/activity/create")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockActivityCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).isEqualTo(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL));
	}

	@Test
	@Order(3)
	@DisplayName("3. Get Activity by ID - Happy Path Scenerio Test")
	public void testGetById() throws Exception {
		when(service.getActivityById(1)).thenReturn(mockActivity1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/activity?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(om.writeValueAsString(mockActivity1)).isEqualTo(result.getResponse().getContentAsString());
	}

	@Test
	@Order(4)
	@DisplayName("4. Get All Activities - Happy Path Scenerio Test")
	public void testGetAll() throws Exception {
		when(service.getAllActivities()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/activity/all");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(om.writeValueAsString(dummyDb)).isEqualTo(result.getResponse().getContentAsString());
	}

	@Test
	@Order(5)
	@DisplayName("5. Update an Existing Activity - Happy Path Scenerio Test")
	// @Disabled("Disabled until CreateActivityTest is up!")
	public void testUpdateActivity() throws Exception {
		when(service.updateActivity(mockActivityModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/activity/update")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockActivityModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL)).isEqualTo(result.getResponse().getContentAsString());
	}

	@Test
	@Order(6)
	@DisplayName("6. Delete Activity - Happy Path Scenerio Test")
	public void testDeleteActivity() throws Exception {
		when(service.deteteActivity(mockActivityDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/activity/delete")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockActivityDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL)).isEqualTo(result.getResponse().getContentAsString());
		
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Unneccessay/Unused Test")
	@Disabled("Disabled until CreateActivityTest is up!") 
	// @Disabled will allow you to ignore this test while debugging other tests
	public void unusedTest() {
		return;
	}
		
		
}
	