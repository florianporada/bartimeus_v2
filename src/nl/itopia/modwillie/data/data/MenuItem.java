package nl.itopia.modwillie.data.data;

public class MenuItem {
	private String key;
	private String name;
	private String url;
	
	public MenuItem() {
		
	}
	
	public MenuItem(String key, String name, String url) {
		this.key = key;
		this.name = name;
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "MenuItem [key=" + key + ", name=" + name + ", url=" + url + "]";
	}
}
