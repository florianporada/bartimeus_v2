package nl.itopia.modwillie.data.data;

import com.ibm.json.java.JSONObject;

/**
 * A Java object of the JSON send by the Arduino
 * @author Robin de Jong
 */
public class ChannelData {
	private Integer id;
	private Integer action;
	private Integer value;
	
	public ChannelData() {
	}

	public ChannelData(Integer id, Integer action, Integer value) {
		this.id = id;
		this.action = action;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("action", action);
		json.put("value", value);
		
		return json;
	}
	
	@Override
	public String toString() {
		// Because the Socket IO client isn't that good as the server (different libraries). We need to convert it to JSON
		return toJSON().toString();
	}
}
