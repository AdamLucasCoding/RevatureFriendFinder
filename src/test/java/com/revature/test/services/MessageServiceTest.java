package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Order;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Activity;
import com.revature.models.Message;
import com.revature.models.User;
import com.revature.repositories.MessageRepository;
import com.revature.services.MessageServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MessageServiceTest {

	@Mock
	private static MessageRepository mockdao;
	
	@InjectMocks
	private static MessageServiceImpl mserv;
	
	private static Message m1, m2;
	static List<Message> dummyDB;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockdao = Mockito.mock(MessageRepository.class);
		mserv = new MessageServiceImpl(mockdao);
		
		LocalDate createdDate = LocalDate.now();
		LocalDate activityDate = LocalDate.now();

		m1 = new Message(1, "Look @ my message",
				new Activity(1, "bball", "Sports", "Gym", createdDate, activityDate, 
						new User(2, "uname2", "pwrd2", "email2"), 5 ), 
				new User(1, "uname1", "pwrd1", "email1"), createdDate);
		
		m2 = new Message(2, "Look @ my message",
				new Activity(2, "bball", "Sports", "Gym", createdDate, activityDate, 
						new User(3, "uname3", "pwrd3", "email3"), 5 ), 
				new User(4, "uname4", "pwrd4", "email4"), createdDate);
		
		dummyDB = new ArrayList<Message>();
		dummyDB.add(m1);
		dummyDB.add(m2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(mockdao).isNotNull();
		assertThat(mserv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Create Message - Happy Path Test")
	void testCreateMessage() {
		
		LocalDate createdDate = LocalDate.now();
		LocalDate activityDate = LocalDate.now();
		
		Message m3 = new Message(3, "Look @ my message",
				new Activity(3, "bball", "Sports", "Gym", createdDate, activityDate, 
						new User(5, "uname5", "pwrd5", "email5"), 5 ), 
				new User(6, "uname6", "pwrd6", "email6"), createdDate);
		when(mockdao.save(m3)).thenReturn(m3);
		assertEquals(true, mserv.createMessage(m3));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get Message by Id - Happy Path Test")
	void testGetMessageById() {
		when(mserv.getMessageById(1)).thenReturn(m1);
		assertEquals(m1, mserv.getMessageById(1));
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get all Messages - Happy Path Test")
	void testGetAllMessages() {
		when(mserv.getAllMessages()).thenReturn(dummyDB);
		assertEquals(dummyDB, mserv.getAllMessages());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update Message - Happy Path Test")
	void testUpdateMessate() {
		m2.setText("look at the new text");
		
		when(mserv.getMessageById(2)).thenReturn(m2);
		when(mockdao.save(m2)).thenReturn(m2);
		
		assertEquals(true, mserv.updateMessage(m2));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Message - Happy Path Test")
	void testDeleteMessage() {
		doNothing().when(mockdao).delete(m2);
		assertEquals(true, mserv.deleteMessage(m2));
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Unneccessary/UnusedTest")
	@Disabled("Disabled until CreateMessageTest is up!")
	public void unusedTest() {
		return;
	}
}
