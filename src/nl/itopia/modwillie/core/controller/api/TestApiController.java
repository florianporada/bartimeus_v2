package nl.itopia.modwillie.core.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import com.ibm.json.java.JSONObject;

import nl.itopia.modwillie.core.service.PatternService;
import nl.itopia.modwillie.data.model.Pattern;
import nl.itopia.modwillie.service.doorbell.DoorbellManager;

/**
 * A API controller handling the test commands send.
 * This controller is not REST, because we need the current logged in user!
 */
@RestController
@RequestMapping("/api/test")
public class TestApiController {
	@Autowired
	private DoorbellManager doorbellManager;
	
	@Autowired
	private PatternService patternService;
	
	@RequestMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public String test(@PathVariable("id") int id, Principal principal) {
		Pattern pattern = patternService.getPattern(id);
		doorbellManager.sendTestNotification(pattern);
		
		return getSuccess("Sended the TEST notification");
	}
	private String getSuccess(String msg) {
		return getResponse("ok", msg);
	}
	
	private String getError(String msg) {
		return getResponse("error", msg);
	}
	
	private String getResponse(String status, String msg) {
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		obj.put("message", msg);
		return obj.toString();
	}
	
}
