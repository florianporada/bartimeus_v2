package nl.itopia.modwillie.data.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import nl.itopia.modwillie.data.data.NotificationType;

@Entity
public class Pattern {
	@Id
	@NotNull
	@GeneratedValue
	private long id;
	
	private String pattern;
	
	@Enumerated(EnumType.STRING)
	private NotificationType type;

	public Pattern() {
	}

	public Pattern(String pattern, NotificationType type) {
		super();
		this.pattern = pattern;
		this.type = type;
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

	@Override
	public String toString() {
		return "Pattern [id=" + id + ", pattern=" + pattern + ", type=" + type + "]";
	}
}
