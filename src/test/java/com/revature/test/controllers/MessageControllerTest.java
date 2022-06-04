package com.revature.test.controllers;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.revature.controllers.MessageController;
import com.revature.models.*;
import com.revature.services.MessageService;
import com.revature.util.ClientMessageUtil;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageControllerTest {
	
	private static Message mockMessage1;
	private static Message mockMessage2;
	private static Message mockMessageCreation;
	private static Message mockMessageModification;
	private static Message mockMessageDeletion;
	private static List<Message> dummyDb;

	
	ObjectMapper om = new ObjectMapper()		
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	
	@Autowired
	MessageController messageController;
	
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private MessageService service;


@BeforeAll
static void setUpBeforeClass() throws Exception {
	System.out.println("setUpBeforeClass() :: building test objects...");

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
    
	User author = new User(1,"satyanadala1","password","satyanadala1@microsoft.com");
	Activity activity = new Activity(1, "Singing","Hobby", "Atlanta", createdDate, activityDate, author, 10);
	
	mockMessage1 = new Message(1,"I am going to sing",activity , author,createdDate);
	mockMessage2 = new Message(2,"I am going to dance",activity , author,createdDate);
	
	
	mockMessageCreation =new Message("I am going to cook",activity , author,createdDate );
	
	mockMessageModification = mockMessageCreation;
	mockMessageModification.setText("I am going to play game");
	
	
	mockMessageDeletion = new Message("I am going to cook",activity , author,createdDate);
	

	dummyDb = new ArrayList<>();
	dummyDb.add(mockMessage1);
	dummyDb.add(mockMessage2);
}
@Test
@Order(1)
@DisplayName("1. AppContext Sanity Test")
public void contextLoads() throws Exception {
	assertThat(messageController).isNotNull();
}
@Test
@Order(2)
@DisplayName("2. Create Message - Happy Path Scenerio Test")
public void testCreateMessage() throws Exception {
	// id number of this creation should be 3
	mockMessageCreation.setId(3);
	//tell Mockito the behavior that I want this method to act like in the mock environment
	when(service.createMessage(mockMessageCreation)).thenReturn(true);
	
	//act
	RequestBuilder request = MockMvcRequestBuilders.post("/api/message/createmessage")
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.content(om.writeValueAsString(mockMessageCreation))
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockmvc.perform(request).andReturn();
	
	assertThat(result.getResponse().getContentAsString()).isEqualTo(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL));
}
@Test
@Order(3)
@DisplayName("3. Get Message by ID - Happy Path Scenerio Test")
public void testGetById() throws Exception {
	when(service.getMessageById(1)).thenReturn(mockMessage1);
	RequestBuilder request = MockMvcRequestBuilders.get("/api/message?id=1");
	MvcResult result = mockmvc.perform(request).andReturn();
	assertThat(om.writeValueAsString(mockMessage1)).isEqualTo(result.getResponse().getContentAsString());
}
@Test
@Order(4)
@DisplayName("4. Get All Messages - Happy Path Scenerio Test")
public void testGetAll() throws Exception {
	when(service.getAllMessages()).thenReturn(dummyDb);
	RequestBuilder request = MockMvcRequestBuilders.get("/api/message/all");
	MvcResult result = mockmvc.perform(request).andReturn();
	assertThat(om.writeValueAsString(dummyDb)).isEqualTo(result.getResponse().getContentAsString());
}
@Test
@Order(5)
@DisplayName("5. Update an Existing Activity - Happy Path Scenerio Test")
// @Disabled("Disabled until CreateMessageTest is up!")
public void testUpdateMessage() throws Exception {
	when(service.updateMessage(mockMessageModification)).thenReturn(true);
	RequestBuilder request = MockMvcRequestBuilders.put("/api/message/update")
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.content(om.writeValueAsString(mockMessageModification))
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockmvc.perform(request).andReturn();
	assertThat(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL)).isEqualTo(result.getResponse().getContentAsString());
}
@Test
@Order(6)
@DisplayName("6. Delete Message - Happy Path Scenerio Test")
public void testDeleteMessage() throws Exception {
	//when(service.delete(mockActivityDeletion)).thenReturn();
	RequestBuilder request = MockMvcRequestBuilders.delete("/api/message/delete")
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.content(om.writeValueAsString(mockMessageDeletion))
			.contentType(MediaType.APPLICATION_JSON);
	MvcResult result = mockmvc.perform(request).andReturn();
	assertThat(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL)).isEqualTo(result.getResponse().getContentAsString());
	
}
@Test
@Order(7)
@DisplayName("7. Unneccessay/Unused Test")
@Disabled("Disabled until CreateMessageTest is up!") 
// @Disabled will allow you to ignore this test while debugging other tests
public void unusedTest() {
	return;
}
}