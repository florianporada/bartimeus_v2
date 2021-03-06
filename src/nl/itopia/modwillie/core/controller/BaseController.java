package nl.itopia.modwillie.core.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import nl.itopia.modwillie.data.data.MenuItem;

/**
 * The base controller will be called with every Controller. So everything that is done in this controller, should happend with every page.
 * @author Robin de Jong
 */
@ControllerAdvice
public class BaseController {
	private static List<MenuItem> crumbs;
	
	static {
		// TODO: The app should be able to run independent of /ModWillie and stuff
		crumbs = new ArrayList<>();
		crumbs.add(new MenuItem("modwillie", "{{ 'home_title' | translate }}", "/"));
//		crumbs.add(new MenuItem("user", "{{ 'user_title' | translate }}", "/ModWillie/user/"));
		crumbs.add(new MenuItem("login", "{{ 'login_title' | translate }}", "/user/login/"));
		crumbs.add(new MenuItem("logout", "{{ 'logout_title' | translate }}", "/user/logout/"));
		
		crumbs.add(new MenuItem("application", "{{ 'manage_app' | translate }}", "/application/"));
		crumbs.add(new MenuItem("notification", "{{ 'manage_not' | translate }}", "/notification/"));
		crumbs.add(new MenuItem("sensor", "{{ 'manage_sensor' | translate }}", "/sensor/"));
		
		crumbs.add(new MenuItem("about", "{{ 'about_title' | translate }}", "/info/about"));
		crumbs.add(new MenuItem("terms", "{{ 'terms_title' | translate }}", "/info/terms"));
		crumbs.add(new MenuItem("privacy", "{{ 'privacy_title' | translate }}", "/info/privacy"));
		
		crumbs.add(new MenuItem("add", "{{ 'add' | translate }}", "/"));
		crumbs.add(new MenuItem("edit", "{{ 'edit' | translate }}", "/"));
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
	public void index(Model model, Principal principal, HttpServletRequest request) {
		boolean isLoggedIn  = principal != null;
		model.addAttribute("isLoggedIn", isLoggedIn);
		if(isLoggedIn) {
			model.addAttribute("user", principal.getName());
		}
		
		// If we create an array with the parts lengths, it will always be that length.
		// This is not always necessary.
		// So we use a ArrayList which has dynamic sizing.
		List<MenuItem> breadcrumbs = new ArrayList<>();
		breadcrumbs.add(crumbs.get(0));
		
		// Get the URI, which will look like: /ModWillie/user/login
		String uri = request.getRequestURI();
		// Remove ModWillie from the URI, I can't come up with a faster solution to debug it
		// This behaviour won't be there on the server
		uri = uri.replace("/ModWillie", "");
		System.out.println(uri);
		
		if(uri.length() > 1) {
			if(uri.endsWith("/")) {
				uri = uri.substring(1, uri.length() - 1);
			} else {
				uri = uri.substring(1, uri.length());	
			}
					
			// Make them all lower case, so that we can easily convert them to keys
			uri = uri.toLowerCase();
			
			String[] parts = uri.split("/");
			
			for(String part : parts) {
				MenuItem crumb = getCrumb(part);
				if(crumb != null) {
					breadcrumbs.add(crumb);
				}
			}
		}
		
		// http://stackoverflow.com/a/4042464
		MenuItem[] breadcrumbsArray = breadcrumbs.toArray(new MenuItem[0]);
		model.addAttribute("breadcrumbs", breadcrumbsArray);
	}
}