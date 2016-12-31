package nl.itopia.modwillie.core.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nl.itopia.modwillie.core.service.UserService;
import nl.itopia.modwillie.core.util.HashUtil;
import nl.itopia.modwillie.data.model.User;

/**
 * The controller for user page. The user page has a few functions: Login, Logout, and Create.
 * @author Robin de Jong
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(Principal principal) {
		ModelAndView view;
		
		if(principal != null) {
			view = new ModelAndView("redirect:/");
		} else {
			view = new ModelAndView("user/login", "user", new User());
		}
		
		return view;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "user/logout";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView view = new ModelAndView("user/create", "user", new User());
		return view;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		boolean thereIsAnError = false;
		User userWithName = userService.getUserWithName(user.getUsername());
		
		if(userWithName != null) {
			result.rejectValue("username", "Username already exists");
			thereIsAnError = true;
		}
		
		if(!thereIsAnError && user.getPassword().equals(user.getRePassword())) {			
			String hashedPassword = HashUtil.hashPassword(user.getPassword());
			user.setPassword(hashedPassword);
			userService.addUser(user);
			
			return "redirect:/user/login";
		} else {
			result.rejectValue("password", "Password did not match");
			thereIsAnError = true;
		}
		
		return "redirect:/user/create";
	}
}
