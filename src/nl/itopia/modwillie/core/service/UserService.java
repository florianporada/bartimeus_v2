package nl.itopia.modwillie.core.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nl.itopia.modwillie.data.dao.UserDao;
import nl.itopia.modwillie.data.model.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	public String hashPassword(User user) {
		return hashPassword(user.getPassword());
	}
	
	public String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		return encoder.encode(password);
	}
	
	public User getUserWithName(String username) {
		return userDao.getUserWithName(username);
	}
	
	public User getUser(long id) {
		return userDao.getUser(id);
	}
	
	public List<User> getUsers() {
		return userDao.getUsers();
	}
}