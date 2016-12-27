package nl.itopia.modwillie.core.controller.manage;

/**
 * The controller for the management of sensors.
 * @author Robin de Jong
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sensor")
public class SensorController {
	@RequestMapping("/")
	public ModelAndView index() {
		final ModelAndView view = new ModelAndView("sensor/index");
		return view;
	}
}
