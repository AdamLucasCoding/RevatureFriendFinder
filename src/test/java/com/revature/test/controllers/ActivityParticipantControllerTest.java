package com.revature.test.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.ActivityParticipantController;
import com.revature.controllers.MessageController;
import com.revature.models.Activity;
import com.revature.models.ActivityParticipant;
import com.revature.models.User;
import com.revature.services.ActivityParticipantService;
import com.revature.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActivityParticipantControllerTest {

	private static ActivityParticipant mockAp1;
	private static ActivityParticipant mockAp2;
	private static ActivityParticipant mockApCreation;
	private static ActivityParticipant mockApModification;
	private static ActivityParticipant mockApDeletion;
	private static List<ActivityParticipant> dummyDb;

	ObjectMapper om = new ObjectMapper()		
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);;

	@Autowired
	@Lazy
	ActivityParticipantController apController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private ActivityParticipantService apService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: building test objects...");

		LocalDate createdDate = LocalDate.now();
		LocalDate activityDate = LocalDate.now();

		User user1 = new User(1, "satyanadala", "password", "satyanadala1@microsoft.com");
		User user2 = new User(2, "johnsmith", "password", "johnsmith@microsoft.com");
		User user3 = new User(3, "johndoe", "password", "johndoe@microsoft.com");
		User user4 = new User(4, "MarleneMcKenny", "password", "MarleneMcKenny@microsoft.com");

		Activity activity1 = new Activity(1, "Singing", "Hobby", "Atlanta", createdDate, activityDate, user1, 10);
		Activity activity2 = new Activity(2, "Playing Chess", "Hobby", "Atlanta", createdDate, activityDate, user2, 10);
		Activity activity3 = new Activity(3, "Playing cards", "Hobby", "Atlanta", createdDate, activityDate, user3, 10);
		Activity activity4 = new Activity(4, "Cooking Indian", "Hobby", "Atlanta", createdDate, activityDate, user4, 10);

		mockAp1 = new ActivityParticipant(1, user1, activity1);
		mockAp2 = new ActivityParticipant(2, user2, activity2);

		mockApCreation = new ActivityParticipant(user3, activity3);

		mockApModification = mockApCreation;
		mockApModification.setParticipant(user4);
		mockApModification.setActivity(activity4);

		mockApDeletion = new ActivityParticipant(4, user2, activity2);

		dummyDb = new ArrayList<>();
		dummyDb.add(mockAp1);
		dummyDb.add(mockAp2);
	}

	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(apController).isNotNull();
	}

	@Test
	@Order(2)
	@DisplayName("2. Create ActivityParticipant - Happy Path Scenerio Test")
	public void testCreateAp() throws Exception {
		
		// Id for created Ap should be 3
		mockApCreation.setId(3);
		
		//tell Mockito the behavior that I want this method to act like in the mock environment
		when(apService.createAp(mockApCreation)).thenReturn(true);
		
		//act
		RequestBuilder request = MockMvcRequestBuilders.post("/api/ap/create")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockApCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		
		assertThat(result.getResponse().getContentAsString()).isEqualTo(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL));

	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get ActivityParticipant by ID - Happy Path Scenerio Test")
	public void testGetById() throws Exception {
		when(apService.getApById(1)).thenReturn(mockAp1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/ap?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(om.writeValueAsString(mockAp1)).isEqualTo(result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get All ActivityParticipant - Happy Path Scenerio Test")
	public void testGetAll() throws Exception {
		when(apService.getAllAp()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/ap/all");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(om.writeValueAsString(dummyDb)).isEqualTo(result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update an Existing ActivityParticipant - Happy Path Scenerio Test")
	public void testUpdateMessage() throws Exception {

		LocalDate createdDate = LocalDate.now();
		LocalDate activityDate = LocalDate.now();

		User user4 = new User(4, "satyanadala1", "password", "satyanadala1@microsoft.com");
		Activity activity4 = new Activity(4, "Singing", "Hobby", "Atlanta", createdDate, activityDate, user4, 10);

		mockAp2 = new ActivityParticipant(2, user4, activity4);
		
		when(apService.updateAp(mockApModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/ap/update")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockApModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Message - Happy Path Scenerio Test")
	public void testDeleteMessage() throws Exception {
		when(apService.deleteAp(mockApDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/ap/delete")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockApDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL));
		
	}
	
}
