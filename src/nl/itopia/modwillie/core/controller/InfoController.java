package nl.itopia.modwillie.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for all the information pages. The controller only shows the views and does nothing more.
 * @author Robin de Jong
 */

@Controller
@RequestMapping("/info")
public class InfoController {
	@RequestMapping("/about")
	public String about() {
		return "info/about";
	}
	
	@RequestMapping("/privacy")
	public String privacy() {
		return "info/privacy";
	}
	
	@RequestMapping("/terms")
	public String terms() {
		return "info/terms";
	}
}
