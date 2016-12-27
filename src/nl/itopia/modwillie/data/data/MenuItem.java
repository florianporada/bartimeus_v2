package nl.itopia.modwillie.data.data;

/**
 * A data object that is used in the breadcrumbs. It holds three values.
 * The breadcrumbs are automatically generated (when they're defined in the BaseController), and the request URI is used to defined what key it should use.
 * 1) A key, which should correspond the part in the request URI.
 * 2) A name, which will be shown to the user
 * 3) A url, which will be used when the user clicks on a link
 * 
 * @author Robin de Jong
 */

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
