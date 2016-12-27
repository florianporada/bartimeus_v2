package nl.itopia.modwillie.core.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import nl.itopia.modwillie.data.data.MenuItem;

/**
 * The base controller will be called with every Controller. So everything that is done in this controller, should happend with every page.
 */
@ControllerAdvice
public class BaseController {
	private static List<MenuItem> crumbs;
	
	static {
		// TODO: The app should be able to run independent of /ModWillie and stuff
		crumbs = new ArrayList<>();
		crumbs.add(new MenuItem("modwillie", "Home", "/ModWillie"));
		crumbs.add(new MenuItem("user", "User", "/ModWillie/user"));
		crumbs.add(new MenuItem("login", "Login", "/ModWillie/user/login"));
	}
	
	private MenuItem getCrumb(String key) {
		for(MenuItem crumb : crumbs) {
			if(crumb.getKey().equals(key)) {
				return crumb;
			}
		}
		
		return null;
	}
	
	@ModelAttribute
	public void index(Model model, HttpServletRequest request) {
		// Get the URI, which will look like: /Willie/user/login
		String uri = request.getRequestURI();
		// We don't need the first '/' when splitting the string to parts 
		uri = uri.substring(1, uri.length() - 1);
		// Make them all lower case, so that we can easily convert them to keys
		uri = uri.toLowerCase();
		String[] parts = uri.split("/");
		MenuItem[] breadcrumbs = new MenuItem[parts.length];
		
		for(int i = 0; i < parts.length; i ++) {
			breadcrumbs[i] = getCrumb(parts[i]);
		}
		
		model.addAttribute("breadcrumbs", breadcrumbs);
	}
}