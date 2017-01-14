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
		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.INCOMMING, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.INCOMMING, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.INCOMMING, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.INCOMMING, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.INCOMMING, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.INCOMMING, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.INCOMMING, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.INCOMMING, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.INCOMMING, 0));
		
		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.VIBRATION, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.VIBRATION, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.VIBRATION, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.VIBRATION, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.VIBRATION, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.VIBRATION, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.VIBRATION, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.VIBRATION, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.VIBRATION, 0));
		
		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.VIBRARTION_CONT, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.VIBRARTION_CONT, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.VIBRARTION_CONT, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.VIBRARTION_CONT, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.VIBRARTION_CONT, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.VIBRARTION_CONT, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.VIBRARTION_CONT, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.VIBRARTION_CONT, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.VIBRARTION_CONT, 0));
	}
	
	private Pattern addPattern(String pattern, NotificationType type, int serverId) {
		return new Pattern(pattern, type, serverId);
	}
}
