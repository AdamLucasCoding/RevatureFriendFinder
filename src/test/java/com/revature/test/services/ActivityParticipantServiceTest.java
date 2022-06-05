package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Activity;
import com.revature.models.ActivityParticipant;
import com.revature.models.User;
import com.revature.repositories.ActivityParticipantRepository;
import com.revature.services.ActivityParticipantServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivityParticipantServiceTest {

	@Mock
	private static ActivityParticipantRepository mockdao;
	
	@InjectMocks
	private static ActivityParticipantServiceImpl apserv;
	
	private static ActivityParticipant ap1, ap2;
	static List<ActivityParticipant> dummyDB;
	
	@BeforeAll
	static void setupBeforeCall() throws Exception {
		mockdao = Mockito.mock(ActivityParticipantRepository.class);
		apserv = new ActivityParticipantServiceImpl(mockdao);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
	    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
		
		ap1 = new ActivityParticipant(1, new User(1, "uname1", "pwrd1", "email1"), new Activity(1, "bbal", "Sports", "Gym", createdDate, activityDate, new User(2, "uname2", "pwrd2", "email2"), 5));	
		ap2 = new ActivityParticipant(2, new User(3, "uname3", "pwrd3", "email3"), new Activity(2, "bbal2", "Sports", "Gym", createdDate, activityDate, new User(4, "uname4", "pwrd4", "email4"), 5));
		
		dummyDB = new ArrayList<ActivityParticipant>();
		dummyDB.add(ap1);
		dummyDB.add(ap2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(mockdao).isNotNull();
		assertThat(apserv).isNotNull();
	}
	
	@Test
	@Order(2)
	@DisplayName("2. Create AP - Happy Path Test")
	void testCreateAP() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
	    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
	    
		ActivityParticipant ap3 = new ActivityParticipant(3, new User(5, "uname5", "pwrd5", "email5"), new Activity(3, "bbal3", "Sports", "Gym", createdDate, activityDate, new User(6, "uname6", "pwrd6", "email6"), 5));
		
		when(mockdao.save(ap3)).thenReturn(ap3);
		assertEquals(true, apserv.createAp(ap3));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get AP by Id - Happy Path Test")
	void testGetApById() {
		when(apserv.getApById(2)).thenReturn(ap2);
		assertEquals(ap2, apserv.getApById(2));
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get All APs - Happy Path Test")
	void testGetAllAps() {
		when(apserv.getAllAp()).thenReturn(dummyDB);
		assertEquals(dummyDB, apserv.getAllAp());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update AP - Happy Path Test")
	void testUpdatAp() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
	    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
	    
		ap2.setActivity(new Activity(4, "bbal4", "Sports", "Gym", createdDate, activityDate, new User(7, "uname7", "pwrd7", "email7"), 5));
		
		when(apserv.getApById(2)).thenReturn(ap2);
		when(mockdao.save(ap2)).thenReturn(ap2);
		assertEquals(true, apserv.updateAp(ap2));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete user - Happy Path Test")
	void testDeleteAp() {
		doNothing().when(mockdao).delete(ap2);
		assertEquals(true, apserv.deleteAp(ap2));
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Unneccessary/UnusedTest")
	@Disabled("Disabled until CreateUserTest is up!")
	public void unusedTest() {
		return;
	}
	
	
}
