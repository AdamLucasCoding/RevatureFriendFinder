package com.revature.test.controllers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
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
import com.revature.controllers.UserController;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ClientMessageUtil;

/**
 * NOTE TO COHORT:
 * 
 * This set of controller testing are implementing integration testing.
 * I will discuss more about this and more testing with Spring Boot apps
 * on Monday after we talk about HTML & CSS.
 * 
 * Take advantage of this test suite and its code for your P2 server testing! :)
 * Please note that I had to modify the application.properties file along with adding
 * two new configuration classes for this Spring Boot app.
 * 
 * @author Azhya Knox
 **/
// Useful Resource for Unit/Integration Testing: https://softwaretestingfundamentals.com/

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTests {

	private static User mockUser1;
	private static User mockUser2;
	private static User mockUserCreation;
	private static User mockUserModification;
	private static User mockUserDeletion;
	private static List<User> dummyDb;

	ObjectMapper om = new ObjectMapper();

	@Autowired
	@Lazy
	UserController userController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private UserService service;

	/**
	 * This method was used during debugging my controller test to make sure that 
	 * the ObjectMapper was converting properly with my test objects
	 * <p>
	 * Note: <br>
	 * ObjectMapper.getJsonFactory().createJsonParser(json) - Since 2.2; now can use ObjectMapper.getFactory().createParser(json)
	 * </p>
	 * @param json (the string literal of any User or ClientMessage object)
	 * @return boolean (whether if valid JSON or not)
	 * @author Azhya Knox
	 **/
	@SuppressWarnings("deprecation")
	public boolean isValidJSON(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getJsonFactory().createJsonParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return valid;
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass() :: building test objects...");
		mockUser1 = new User(1, "Starbursts", "password", "email.com");
		mockUser2 = new User(2, "Username", "myPassword", "myemail@email.com");
		
		mockUserCreation = new User("testfail", "testfail", "testfail");
		
		mockUserModification = mockUserCreation;
		mockUserModification.setUsername("Pass");
		mockUserModification.setPword("Pass");
		mockUserModification.setEmail("Pass");
		
		mockUserDeletion = new User(4, "Troll123", "badPassword", "troll@troll.com");

		dummyDb = new ArrayList<>();
		dummyDb.add(mockUser1);
		dummyDb.add(mockUser2);
	}

	/*
	 * Sanity Check - if this fails, application context is not loaded and all other
	 * tests should fail
	 */
	@Test
	@Order(1)
	@DisplayName("1. AppContext Sanity Test")
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Test
	@Order(2)
	@DisplayName("2. Create User - Happy Path Scenerio Test")
	public void testCreateUser() throws Exception {
		// id number of this creation should be 3
		mockUserCreation.setId(3);
		//tell Mockito the behavior that I want this method to act like in the mock environment
		when(service.createUser(mockUserCreation)).thenReturn(true);
		
		//act
		RequestBuilder request = MockMvcRequestBuilders.post("/api/user/register")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserCreation))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		//assert
		assertEquals(om.writeValueAsString(ClientMessageUtil.CREATION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}

	@Test
	@Order(3)
	@DisplayName("3. Get User by ID - Happy Path Scenerio Test")
	
	public void testGetById() throws Exception {
		when(service.getUserById(1)).thenReturn(mockUser1);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/user/id?id=1");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(mockUser1), result.getResponse().getContentAsString());
	}

	@Test
	@Order(4)
	@DisplayName("4. Get All Candies - Happy Path Scenerio Test")
	
	public void testGetAll() throws Exception {
		when(service.getAllUsers()).thenReturn(dummyDb);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/user/getall");
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(dummyDb), result.getResponse().getContentAsString());
	}

	@Test
	@Order(5)
	@DisplayName("5. Update an Existing User - Happy Path Scenerio Test")
	
	// @Disabled("Disabled until CreateUserTest is up!")
	public void testUpdateUser() throws Exception {
		when(service.updateUser(mockUserModification)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/user/update")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserModification))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.UPDATE_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}

	@Test
	@Order(6)
	@DisplayName("6. Delete User - Happy Path Scenerio Test")
	
	public void testDeleteUser() throws Exception {
		when(service.deleteUser(mockUserDeletion)).thenReturn(true);
		RequestBuilder request = MockMvcRequestBuilders.delete("/api/user/delete")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(om.writeValueAsString(mockUserDeletion))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(request).andReturn();
		assertEquals(om.writeValueAsString(ClientMessageUtil.DELETION_SUCCESSFUL),
				result.getResponse().getContentAsString());
	}
	
	@Test
	@Order(7)
	@DisplayName("7. Unneccessay/Unused Test")
	@Disabled("Disabled until CreateUserTest is up!") 
	// @Disabled will allow you to ignore this test while debugging other tests
	public void unusedTest() {
		return;
	}
}