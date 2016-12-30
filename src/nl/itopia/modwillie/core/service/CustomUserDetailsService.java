package nl.itopia.modwillie.core.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import nl.itopia.modwillie.data.model.User;

/**
 * The service for getting the user from the database, used by Spring Security 
 * @author Robin de Jong
 */
@Service
public class CustomUserDetailsService  implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserWithName(username);

		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
			user.getUsername(), user.getPassword(), Arrays.asList(authority)
		);
		return userDetails;
	}
}
