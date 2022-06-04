package com.revature.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Order;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

	@Mock
	private static UserRepository mockdao;
	
	@InjectMocks
	private static UserServiceImpl userv;
	
	private static User u1, u2;
	static List<User> dummyDB;
	
	@BeforeAll
	static void setupBeforeCall() throws Exception {
		
		mockdao = Mockito.mock(UserRepository.class);
		
		userv = new UserServiceImpl(mockdao);
		
		u1 = new User(1, "uname1", "pwrd1", "email1@email.com");
		u2 = new User(2, "uname2", "pwrd2", "email2@email.com");
		
		dummyDB = new ArrayList<User>();
		dummyDB.add(u1);
		dummyDB.add(u2);
		
	}
	
	@Test
	@Order(1)
	@DisplayName("1. Mock Validation Sanity Test")
	void checkMockInjection() {
		assertThat(mockdao).isNotNull();
		assertThat(userv).isNotNull();
	}
	
	
	@Test
	@Order(2)
	@DisplayName("2. Create User - Happy Path Test")
	void testCreateUser() {
		User u3 = new User("uname3", "pwrd3", "email3@email.com");
		u3.setId(3);
		
		when(mockdao.save(u3)).thenReturn(u3);
		
		assertEquals(true, userv.createUser(u3));
	}
	
	@Test
	@Order(3)
	@DisplayName("3. Get User by Id - Happy Path Test")
	void testGetUserById() {
		when(userv.getUserById(1)).thenReturn(u1);
		
		assertEquals(u1, userv.getUserById(1));
	}
	
	@Test
	@Order(4)
	@DisplayName("4. Get all Users - Happy Path Test")
	void testGetAllUsers() {
		
		when(userv.getAllUsers()).thenReturn(dummyDB);
		
		assertEquals(dummyDB, userv.getAllUsers());
	}
	
	@Test
	@Order(5)
	@DisplayName("5. Update User - Happy Path Test")
	void testUpdateUser() {
		u2.setUsername("Bobby");
		u2.setEmail("boby@email.com");
		
		when(userv.getUserById(2)).thenReturn(u2);
		when(mockdao.save(u2)).thenReturn(u2);
		
		assertEquals(true, userv.updateUser(u2));
	}
	
	@Test
	@Order(6)
	@DisplayName("6. Delete User - Happy Path Test")
	void testDeleteUser() {
		doNothing().when(mockdao).delete(u2);
		
		assertEquals(true, userv.deleteUser(u2));
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Unneccessay/UnusedTest")
	@Disabled("Disabled until CreateUserTest is up!")
	public void unusedTest() {
		return;
	}
}


