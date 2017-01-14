package nl.itopia.modwillie.data.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import nl.itopia.modwillie.data.data.NotificationType;

/**
 * A pattern is a way the watch vibrates. The user holds a reference on what he prefers. The pattern objects are all predefined. For now they reside in the database.
 * @author Robin de Jong
 */
@Entity
public class Pattern {
	@Id
	@NotNull
	@GeneratedValue
	private long id;
	
	private String pattern;
	
	@Enumerated(EnumType.STRING)
	private NotificationType type;
	
	private int serverId;

	public Pattern() {
	}

	public Pattern(String pattern, NotificationType type, int serverId) {
		super();
		this.pattern = pattern;
		this.type = type;
		this.serverId = serverId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	@Override
	public String toString() {
		return "Pattern [id=" + id + ", pattern=" + pattern + ", type=" + type + ", serverId=" + serverId + "]";
	}
}
