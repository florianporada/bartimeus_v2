package nl.itopia.modwillie.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for the Dashboard page
 * @author Robin de Jong
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping("")
	public String index() {
		return "dashboard";
	}
}
