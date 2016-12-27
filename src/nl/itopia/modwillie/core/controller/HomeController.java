package nl.itopia.modwillie.core.controller;

/**
 * The controller for the Dashboard page
 * @author Robin de Jong
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping("")
	public String index() {
		return "dashboard";
	}
}
