package nl.itopia.modwillie.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * A sensor holds the hash, name, and the user. This will be used when processing the doorbell, so the server knows who to notify.
 * @author Robin de Jong
 */
@Entity
public class Sensor {
	@Id
	@NotNull
	@GeneratedValue
	private long id;
	
	@Column(length=64)
	private String hash;
	
	private String name;
	
	@ManyToOne
	private User user;
	
	public Sensor() {
		
	}

	public Sensor(long id, String hash, User user) {
		this.id = id;
		this.hash = hash;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", hash=" + hash + ", name=" + name + ", user=" + user + "]";
	}
}
