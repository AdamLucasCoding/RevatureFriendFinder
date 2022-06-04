package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Order;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.models.Activity;
import com.revature.models.User;
import com.revature.repositories.ActivityRepository;
import com.revature.services.ActivityServiceImpl;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivityServiceTest {

	@Mock
	private static ActivityRepository mockdao;
	
	@InjectMocks
	private static ActivityServiceImpl aserv;
	
	private static Activity a1, a2;
	static List<Activity> dummyDB;

	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
	
	@BeforeAll
	static void setupBeforeCall() throws Exception {
		mockdao = Mockito.mock(ActivityRepository.class);
		aserv = new ActivityServiceImpl(mockdao);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate createdDate = LocalDate.parse("2022-10-10", formatter);
	    LocalDate activityDate = LocalDate.parse("2022-11-11", formatter);
		
		a1 = new Activity(1, "bbal", "Sports", "Gym", createdDate, activityDate, new User(1, "uname1", "pwrd1", "email1"), 5);
		a2 = new Activity(2, "bbal2", "Sports", "Gym", createdDate, activityDate, new User(2, "uname2", "pwrd2", "email2"), 5);
		
		dummyDB = new ArrayList<Activity>();
		dummyDB.add(a1);
		dummyDB.add(a2);
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(mockdao).isNotNull();
		assertThat(aserv).isNotNull();
	}
	
	
	@Test
	@Order(2)
	@DisplayName("2. Create Activity - Happy Path Test")
	void testCreateActivity() {
		Activity a3 = new Activity(3, "bbal3", "Sports", "Gym", createdDate, activityDate, new User(3, "uname3", "pwrd3", "email3"), 5);
		when(mockdao.save(a3)).thenReturn(a3);
		assertEquals(true, aserv.createActivity(a3));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get Activity by Id - Happy Path Test")
	void testGetActivityById() {
		when(aserv.getActivityById(1)).thenReturn(a1);
		assertEquals(a1, aserv.getActivityById(1));
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get all Activities - Happy Path Test")
	void testGetAllActivities() {
		when(aserv.getAllActivities()).thenReturn(dummyDB);
		assertEquals(dummyDB, aserv.getAllActivities());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update Activity - Happy Path Test")
	void testUpdateuser() {
		a2.setLocation("bbal court");
		
		when(aserv.getActivityById(2)).thenReturn(a2);
		when(mockdao.save(a2)).thenReturn(a2);
		
		assertEquals(true, aserv.updateActivity(a2));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete Activity - Happy Path Test")
	void testDeleteUser() {
		doNothing().when(mockdao).delete(a2);
		assertEquals(true, aserv.deteteActivity(a2));
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Unneccesary/UnusedTest")
	@Disabled("Disabled until CreateApplicationTest is up")
	public void unusedTest() {
		return;
	}
	
}
























































