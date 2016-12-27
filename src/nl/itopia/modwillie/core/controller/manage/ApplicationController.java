package nl.itopia.modwillie.core.controller.manage;

/**
 * The controller for the management of applications.
 * @author Robin de Jong
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	@RequestMapping("/")
	public ModelAndView index() {
		final ModelAndView view = new ModelAndView("application/index");
		return view;
	}
}
