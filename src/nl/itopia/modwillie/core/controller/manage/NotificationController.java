package nl.itopia.modwillie.core.controller.manage;

/**
 * The controller for the management of notifications.
 * @author Robin de Jong
 */
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

		Pattern patternIncoming = user.getIncomingPattern();
		Pattern patternDoorbellKnown = user.getDoorbellKnownPattern();
		Pattern patternDoorbellUnknown = user.getDoorbellUnknownPattern();
		Pattern patternMotion = user.getMotionPattern();
		if(patternIncoming == null) {
			patternIncoming = patternService.getFirstPattern(NotificationType.INCOMING);
		}
		
		if(patternDoorbellKnown == null) {
			patternDoorbellKnown = patternService.getFirstPattern(NotificationType.DOORBELL_KNOWN);
		}
		
		if(patternDoorbellUnknown == null) {
			patternDoorbellUnknown = patternService.getFirstPattern(NotificationType.DOORBELL_UNKNOWN);
		}
		if(patternMotion == null) {
			patternMotion = patternService.getFirstPattern(NotificationType.MOTION);
		}
		System.out.println(patternIncoming+", "+patternDoorbellKnown+", "+patternDoorbellUnknown+", "+patternMotion);
		
		view.addObject("userIncoming", patternIncoming);
		view.addObject("userDoorbellKnown", patternDoorbellKnown);
		view.addObject("userDoorbellUnknown", patternDoorbellUnknown);
		view.addObject("userMotion", patternMotion);
		
		view.addObject("patternsIncoming", patternService.getPatterns(NotificationType.INCOMING));
		view.addObject("patternsDoorbellKnown", patternService.getPatterns(NotificationType.DOORBELL_KNOWN));
		view.addObject("patternsDoorbellUnknown", patternService.getPatterns(NotificationType.DOORBELL_UNKNOWN));
		view.addObject("patternsMotion", patternService.getPatterns(NotificationType.MOTION));
		

		return view;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String updateNotifications(HttpServletRequest request, Principal principal, RedirectAttributes ra) {
		String incoming = request.getParameter("incoming");
		String doorbellKnown = request.getParameter("doorbell_known");
		String doorbellUnknown = request.getParameter("doorbell_unknown");
		String motion = request.getParameter("motion");
		
		System.out.println(incoming+", "+doorbellKnown+", "+doorbellUnknown+", "+motion);
		
		long incomingPattern = Long.parseLong(incoming);
		long doorbellKnownPattern = Long.parseLong(doorbellKnown);
		long doorbellUnknownPattern = Long.parseLong(doorbellUnknown);
		long motionPattern = Long.parseLong(motion);
		
		Pattern patternIncoming = patternService.getPattern(incomingPattern);
		Pattern patternDoorbellKnown = patternService.getPattern(doorbellKnownPattern);
		Pattern patternDoorbellUnknown = patternService.getPattern(doorbellUnknownPattern);
		Pattern patternMotion = patternService.getPattern(motionPattern);
		
		User user = userService.getUser(principal);
		user.setIncomingPattern(patternIncoming);
		user.setDoorbellKnownPattern(patternDoorbellKnown);
		user.setDoorbellUnknownPattern(patternDoorbellUnknown);
		user.setMotionPattern(patternMotion);
		userService.updateUser(user);
		
		return "redirect:/notification/";
	}
}
