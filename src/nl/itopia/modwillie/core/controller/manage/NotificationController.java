package nl.itopia.modwillie.core.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The controller for the management of notifications.
 * @author Robin de Jong
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nl.itopia.modwillie.core.service.PatternService;
import nl.itopia.modwillie.data.data.NotificationType;
import nl.itopia.modwillie.data.model.Pattern;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private PatternService patternService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index() {
		final ModelAndView view = new ModelAndView("notification/index");
		view.addObject("patternsIncomming", patternService.getPatterns(NotificationType.INCOMMING));
		view.addObject("patternsVibration", patternService.getPatterns(NotificationType.VIBRATION));
		view.addObject("patternsVibrationCont", patternService.getPatterns(NotificationType.VIBRARTION_CONT));
		return view;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String updateNotifications(HttpServletRequest request, RedirectAttributes ra) {
		String incomming = request.getParameter("incomming");
		String vibration = request.getParameter("vibration");
		String vibrationCont = request.getParameter("vibration_cont");
		
		int incommingPattern = Integer.parseInt(incomming);
		int vibrationPattern = Integer.parseInt(vibration);
		int vibrationContPattern = Integer.parseInt(vibrationCont);
		
		System.out.println("Should update with: "+incommingPattern+", "+vibrationPattern+", "+vibrationContPattern);
		return "redirect:/notification/";
	}
}
