package nl.itopia.modwillie.core.controller.manage;

import java.security.Principal;
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
import nl.itopia.modwillie.core.service.UserService;
import nl.itopia.modwillie.data.data.NotificationType;
import nl.itopia.modwillie.data.model.Pattern;
import nl.itopia.modwillie.data.model.User;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private PatternService patternService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		final ModelAndView view = new ModelAndView("notification/index");
		User user = userService.getUser(principal);

		Pattern patternIncomming = user.getIncommingPattern();
		Pattern patternVibration = user.getVibrationPattern();
		Pattern patternVivrationCont = user.getVibrationContPattern();
		if(patternIncomming == null) {
			patternIncomming = patternService.getFirstPattern(NotificationType.INCOMMING);
		}
		
		if(patternVibration == null) {
			patternVibration = patternService.getFirstPattern(NotificationType.VIBRATION);
		}
		
		if(patternVivrationCont == null) {
			patternVivrationCont = patternService.getFirstPattern(NotificationType.VIBRARTION_CONT);
		}
		
		view.addObject("userIncomming", patternIncomming);
		view.addObject("userVibration", patternVibration);
		view.addObject("userVibrationCont", patternVivrationCont);
		
		view.addObject("patternsIncomming", patternService.getPatterns(NotificationType.INCOMMING));
		view.addObject("patternsVibration", patternService.getPatterns(NotificationType.VIBRATION));
		view.addObject("patternsVibrationCont", patternService.getPatterns(NotificationType.VIBRARTION_CONT));
				
		return view;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String updateNotifications(HttpServletRequest request, Principal principal, RedirectAttributes ra) {
		String incomming = request.getParameter("incomming");
		String vibration = request.getParameter("vibration");
		String vibrationCont = request.getParameter("vibration_cont");
		
		long incommingPattern = Long.parseLong(incomming);
		long vibrationPattern = Long.parseLong(vibration);
		long vibrationContPattern = Long.parseLong(vibrationCont);
		
		Pattern patternIncomming = patternService.getPattern(incommingPattern);
		Pattern patternVibration = patternService.getPattern(vibrationPattern);
		Pattern patternVivrationCont = patternService.getPattern(vibrationContPattern);
		
		User user = userService.getUser(principal);
		user.setIncommingPattern(patternIncomming);
		user.setVibrationPattern(patternVibration);
		user.setVibrationContPattern(patternVivrationCont);		
		userService.updateUser(user);
		
		return "redirect:/notification/";
	}
}
