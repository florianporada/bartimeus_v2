package nl.itopia.modwillie.core.controller.manage;

/**
 * The controller for the management of applications.
 * @author Robin de Jong
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nl.itopia.modwillie.core.util.IPUtil;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	private final int DEFAULT_HOST = 1234;
	
	@RequestMapping("/")
	public ModelAndView index() {
		String local = IPUtil.getLocalAddress();
		
		final ModelAndView view = new ModelAndView("application/index");
		System.out.println("The host: "+IPUtil.createAddress(local, DEFAULT_HOST));
		view.addObject("host", IPUtil.createAddress(local, DEFAULT_HOST));
		
		return view;
	}
}
