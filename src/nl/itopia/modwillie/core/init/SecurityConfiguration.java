package nl.itopia.modwillie.core.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import nl.itopia.modwillie.core.service.CustomUserDetailsService;

/**
 * The configuration for the security aspect of the application.
 * @author Robin de Jong
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/info/**").permitAll() // Allow the information pages to be shown to everyone
				.antMatchers("/assets/**").permitAll() // Allow everyone to receive the assets
				.antMatchers("/user/create/").permitAll() // Allow everyone to create an account
				.anyRequest().authenticated() // The rest of the pages should be hidden
				.and()
			.formLogin()
				.loginPage("/user/login/").permitAll() // Allow the user to login
				.and().csrf() // Add CSRF tokens so you can't call the URL from a script
				.and()
			.logout()
				.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	} 
}
