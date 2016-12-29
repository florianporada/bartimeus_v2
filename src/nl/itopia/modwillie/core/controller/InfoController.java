package nl.itopia.modwillie.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
