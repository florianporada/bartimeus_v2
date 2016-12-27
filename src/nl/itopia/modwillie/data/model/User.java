package nl.itopia.modwillie.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

@Entity
public class User {
	@Id
	@NotNull
	@GeneratedValue
	private long id;
	
	private String username;
	
	@Column(length=60)
	private String password;
	
	@Transient
	private String rePassword;
	
	public User() {}
	
	public User(String username, String password, String rePassword) {
		this.username = username;
		this.password = password;
		this.rePassword = rePassword;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", rePassword=" + rePassword
				+ "]";
	}
}
