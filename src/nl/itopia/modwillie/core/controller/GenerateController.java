package nl.itopia.modwillie.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nl.itopia.modwillie.core.util.HashUtil;
/**
 * The controller for generating a new sensor.
 * @author Robin de Jong
 */
@Controller
@RequestMapping("/generate")
public class GenerateController {
	@RequestMapping("")
	public ModelAndView index() {
		int newSensor = HashUtil.getNewID();
		
		String hash = HashUtil.simpleHash(""+newSensor);
		
		final ModelAndView view = new ModelAndView("generate");
		view.addObject("hash", hash);
		view.addObject("sensor", newSensor);
		
		return view;
	}
}
