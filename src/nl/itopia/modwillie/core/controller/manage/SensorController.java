package nl.itopia.modwillie.core.controller.manage;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The controller for the management of sensors.
 * @author Robin de Jong
 */

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nl.itopia.modwillie.core.helper.ResourceNotFoundException;
import nl.itopia.modwillie.core.service.SensorService;
import nl.itopia.modwillie.core.service.UserService;
import nl.itopia.modwillie.data.model.Sensor;
import nl.itopia.modwillie.data.model.User;

@Controller
@RequestMapping("/sensor")
public class SensorController {
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public ModelAndView index(Principal principal) {
		User user = userService.getUser(principal);
		final ModelAndView view = new ModelAndView("sensor/index");
		view.addObject("sensors", sensorService.getSensorsForUser(user));
		return view;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add() {
		final ModelAndView view = new ModelAndView("sensor/add", "sensor", new Sensor());
		return view;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addSensor(@Valid @ModelAttribute("sensor") Sensor sensor, Principal principal, BindingResult result, RedirectAttributes ra) {
		User user = userService.getUser(principal);
		sensor.setUser(user);
		sensorService.addSensor(sensor);
		return "redirect:/sensor/";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id, Principal principal) {
		Sensor sensor = sensorService.getSensor(id);
		// Throw a 404 Exception if the sensor does not exist
		if(sensor == null) {
			throw new ResourceNotFoundException();
		}
		
		// Check if the sensor that is gathered can actually be shown to the current user.
		// If the sensor is not from the user, throw a 404 exception 
		User currentUser = userService.getUser(principal);
		if(sensor.getUser().getId() != currentUser.getId()) {
			throw new ResourceNotFoundException();
		}
		
		final ModelAndView view = new ModelAndView("sensor/edit", "sensor", sensor);
		return view;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String editSensor(@Valid @ModelAttribute("sensor") Sensor sensor, Principal principal, BindingResult result, RedirectAttributes ra) {
		// Throw a 404 Exception if the sensor does not exist
		if(sensor == null) {
			throw new ResourceNotFoundException();
		}
		
		// Check if the sensor that is gathered can actually be shown to the current user.
		// If the sensor is not from the user, throw a 404 exception 
		User currentUser = userService.getUser(principal);
		if(sensor.getUser().getId() != currentUser.getId()) {
			throw new ResourceNotFoundException();
		}
		
		sensorService.updateSensor(sensor);
		return "redirect:/sensor/";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") long id, Principal principal, RedirectAttributes ra) {
		Sensor sensor = sensorService.getSensor(id);
		// Throw a 404 Exception if the sensor does not exist
		if(sensor == null) {
			throw new ResourceNotFoundException();
		}
		
		// Check if the sensor that is gathered can actually be shown to the current user.
		// If the sensor is not from the user, throw a 404 exception 
		User currentUser = userService.getUser(principal);
		if(sensor.getUser().getId() != currentUser.getId()) {
			throw new ResourceNotFoundException();
		}
		
		sensorService.deleteSensor(sensor);
		return "redirect:/sensor/";
	}
}