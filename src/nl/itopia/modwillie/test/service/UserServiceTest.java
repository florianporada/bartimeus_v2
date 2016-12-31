package nl.itopia.modwillie.test.service;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Assert;

import nl.itopia.modwillie.core.init.Initializer;
import nl.itopia.modwillie.core.init.TestConfiguration;
import nl.itopia.modwillie.core.service.UserService;
import nl.itopia.modwillie.core.util.HashUtil;
import nl.itopia.modwillie.data.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Initializer.class, TestConfiguration.class})
@WebAppConfiguration
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	private User user;
	
	private String defaultUsername;
	private String defaultPassword;
	
	@Before
	public void setup() {
		defaultUsername = "test_username";
		defaultPassword = HashUtil.hashPassword("test_password");
		
		user = new User();
		user.setUsername(defaultUsername);
		user.setPassword(defaultPassword);		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetUsers() {
		userService.addUser(user);
		List<User> users = userService.getUsers();		
		Assert.assertEquals(1, users.size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetUser() {
		userService.addUser(user);
		List<User> users = userService.getUsers();
		Assert.assertEquals(users.size(), 1);
		
		User getUser = userService.getUser(user.getId());
		Assert.assertNotEquals(getUser, null);
		
		Assert.assertEquals(user.getId(), getUser.getId());
		Assert.assertEquals(user.getUsername(), getUser.getUsername());
		Assert.assertEquals(user.getPassword(), getUser.getPassword());
	}
	
		
	@Test
	@Transactional
	@Rollback(true)
	public void testAddUser() {
		userService.addUser(user);
		
		List<User> users = userService.getUsers();
		
		Assert.assertEquals(users.size(), 1);
		Assert.assertEquals(user.getId(), users.get(0).getId());
		Assert.assertEquals(user.getUsername(), users.get(0).getUsername());
		Assert.assertEquals(user.getPassword(), users.get(0).getPassword());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveUser() {
		userService.addUser(user);
		Assert.assertEquals(userService.getUsers().size(), 1);
		userService.removeUser(user);
		Assert.assertEquals(userService.getUsers().size(), 0);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateUser() {
		userService.addUser(user);
		List<User> users = userService.getUsers();
		Assert.assertEquals(users.size(), 1);
		
		User updateThisUser = users.get(0);
		updateThisUser.setUsername("test_username1");
		updateThisUser.setPassword(HashUtil.hashPassword("test_password1"));
		userService.updateUser(updateThisUser);
		users = userService.getUsers();
		Assert.assertEquals(users.size(), 1);
		
		
		User updatedUser = users.get(0);
		Assert.assertEquals(updatedUser.getId(), this.user.getId());
		Assert.assertNotEquals(defaultUsername, updatedUser.getUsername());
		Assert.assertNotEquals(defaultPassword, updatedUser.getPassword());
		
	}	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testGetUserWithName() {
		userService.addUser(user);
		List<User> users = userService.getUsers();
		Assert.assertEquals(users.size(), 1);
		
		User getUser = userService.getUserWithName(user.getUsername());
		
		Assert.assertEquals(getUser.getId(), user.getId());
		Assert.assertEquals(getUser.getPassword(), user.getPassword());
		Assert.assertEquals(getUser.getUsername(), user.getUsername());
	}
}
