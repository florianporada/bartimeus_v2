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
		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.INCOMING, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.INCOMING, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.INCOMING, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.INCOMING, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.INCOMING, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.INCOMING, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.INCOMING, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.INCOMING, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.INCOMING, 0));
		
		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.DOORBELL_KNOWN, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.DOORBELL_KNOWN, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.DOORBELL_KNOWN, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.DOORBELL_KNOWN, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.DOORBELL_KNOWN, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.DOORBELL_KNOWN, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.DOORBELL_KNOWN, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.DOORBELL_KNOWN, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.DOORBELL_KNOWN, 0));
		
		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.DOORBELL_UNKNOWN, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.DOORBELL_UNKNOWN, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.DOORBELL_UNKNOWN, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.DOORBELL_UNKNOWN, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.DOORBELL_UNKNOWN, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.DOORBELL_UNKNOWN, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.DOORBELL_UNKNOWN, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.DOORBELL_UNKNOWN, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.DOORBELL_UNKNOWN, 0));

		patternService.addPattern(addPattern("One gentle notification tone", NotificationType.MOTION, 2));
		patternService.addPattern(addPattern("Two gentle notification tones", NotificationType.MOTION, 3));
		patternService.addPattern(addPattern("Three long, high-intensity tones ", NotificationType.MOTION, 4));
		patternService.addPattern(addPattern("One long, high-intensity tone ", NotificationType.MOTION, 5));
		patternService.addPattern(addPattern("One high-intensity tone ", NotificationType.MOTION, 6));
		patternService.addPattern(addPattern("Two high-intensity tones ", NotificationType.MOTION, 8));
		patternService.addPattern(addPattern("Three high-intensity tones ", NotificationType.MOTION, 7));
		patternService.addPattern(addPattern("One tone with ascending intensity", NotificationType.MOTION, 1));
		patternService.addPattern(addPattern("One tone with descending intensity ", NotificationType.MOTION, 0));
	
	}
	
	private Pattern addPattern(String pattern, NotificationType type, int serverId) {
		return new Pattern(pattern, type, serverId);
	}
}
