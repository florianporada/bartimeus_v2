package nl.itopia.modwillie.core.controller.manage;

/**
 * The controller for the management of applications.
 * @author Robin de Jong
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nl.itopia.modwillie.core.util.IPUtil;
import nl.itopia.modwillie.service.server.Server;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/application")
public class ApplicationController {	
	@RequestMapping("/")
	public ModelAndView index() {
		String local = IPUtil.getLocalAddress();
		
		System.out.println(local);
		
		final ModelAndView view = new ModelAndView("application/index");
		view.addObject("host", IPUtil.createAddress(local, Server.DEFAULT_PORT));
		
		return view;
	}
}
