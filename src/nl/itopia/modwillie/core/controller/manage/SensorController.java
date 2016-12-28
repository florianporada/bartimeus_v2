package nl.itopia.modwillie.core.controller.manage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The controller for the management of sensors.
 * @author Robin de Jong
 */

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import nl.itopia.modwillie.core.service.SensorService;
import nl.itopia.modwillie.data.model.Sensor;

@Controller
@RequestMapping("/sensor")
public class SensorController {
	@Autowired
	private SensorService sensorService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		final ModelAndView view = new ModelAndView("sensor/index");
		return view;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add() {
		final ModelAndView view = new ModelAndView("sensor/add", "sensor", new Sensor());
		return view;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addSensor(@Valid @ModelAttribute("user") Sensor sensor, BindingResult result) {
		sensorService.addSensor(sensor);
		return "";
	}
}
