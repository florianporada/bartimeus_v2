package nl.itopia.modwillie.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.itopia.modwillie.core.service.PatternService;
import nl.itopia.modwillie.data.data.NotificationType;
import nl.itopia.modwillie.data.model.Pattern;

@Controller
@RequestMapping("/init")
public class InitController {
	@Autowired
	private PatternService patternService;
	
	@RequestMapping("")
	@ResponseBody
	public String init() {
		String status = "Checking if there are currently patterns in the system<br/>";
		List<Pattern> patterns = patternService.getPatterns();
		
		if(patterns.size() > 0) {
			status += "There are patterns<br/>";
			status += "Make sure all the patterns have been removed<br/>";
		} else {
			status += "There are currently no patterns<br/>";
			addPatterns();
			status += "Added the new patterns<br/>";
			
			patterns = patternService.getPatterns();

			if(patterns.size() == 15) {
				status += "Successfully added the patterns<br/>";
			} else {
				status += "Current pattern length: "+patterns.size()+", should be: "+15+"<br/>";	
			}
		}
		
		return status;
	}
	
	private void addPatterns() {
		patternService.addPattern(addPattern("H - H - H", NotificationType.INCOMMING));
		patternService.addPattern(addPattern("Z - Z - Z", NotificationType.INCOMMING));
		patternService.addPattern(addPattern("Z - H - Z", NotificationType.INCOMMING));
		patternService.addPattern(addPattern("Z - Z - H", NotificationType.INCOMMING));
		patternService.addPattern(addPattern("H - H - Z", NotificationType.INCOMMING));
		
		patternService.addPattern(addPattern("H - H - H", NotificationType.VIBRATION));
		patternService.addPattern(addPattern("Z - Z - Z", NotificationType.VIBRATION));
		patternService.addPattern(addPattern("Z - H - Z", NotificationType.VIBRATION));
		patternService.addPattern(addPattern("Z - Z - H", NotificationType.VIBRATION));
		patternService.addPattern(addPattern("H - H - Z", NotificationType.VIBRATION));
		
		patternService.addPattern(addPattern("H - H - H", NotificationType.VIBRARTION_CONT));
		patternService.addPattern(addPattern("Z - Z - Z", NotificationType.VIBRARTION_CONT));
		patternService.addPattern(addPattern("Z - H - Z", NotificationType.VIBRARTION_CONT));
		patternService.addPattern(addPattern("Z - Z - H", NotificationType.VIBRARTION_CONT));
		patternService.addPattern(addPattern("H - H - Z", NotificationType.VIBRARTION_CONT));
	}
	
	private Pattern addPattern(String pattern, NotificationType type) {
		return new Pattern(pattern, type);
	}
}
