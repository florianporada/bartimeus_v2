package nl.itopia.modwillie.data.data;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class ChannelData {
	private Long id;
	private Long[] channels;
	
	public ChannelData() {
		this(-1L, new Long[]{});
	}
	
	public ChannelData(Long id, Long[] channels) {
		this.id = id;
		this.channels = channels;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long[] getChannels() {
		return channels;
	}
	
	public void setChannels(Long[] channels) {
		this.channels = channels;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		
		JSONArray array = new JSONArray();
		for(Long channel : channels) {
			array.add(channel);
		}
		json.put("channels", array);
		
		return json;
	}
	
	@Override
	public String toString() {
		// Because the Socket IO client isn't that good as the server (different libraries). We need to convert it to JSON
		return toJSON().toString();
	}
}
