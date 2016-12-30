package nl.itopia.modwillie.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.sun.istack.NotNull;

/**
 * A simple user object. It doesn't hold a lot of data, and the most important parts are the username and the password.
 * 
 * @author Robin de Jong
 */
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Where(clause="type='INCOMMING'")
	private Pattern incommingPattern;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Where(clause="type='VIBRATION'")
	private Pattern vibrationPattern;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Where(clause="type='VIBRATION_CONT'")
	private Pattern vibrationContPattern;
	
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
	
	public Pattern getIncommingPattern() {
		return incommingPattern;
	}

	public void setIncommingPattern(Pattern incommingPattern) {
		this.incommingPattern = incommingPattern;
	}

	public Pattern getVibrationPattern() {
		return vibrationPattern;
	}

	public void setVibrationPattern(Pattern vibrationPattern) {
		this.vibrationPattern = vibrationPattern;
	}

	public Pattern getVibrationContPattern() {
		return vibrationContPattern;
	}

	public void setVibrationContPattern(Pattern vibrationContPattern) {
		this.vibrationContPattern = vibrationContPattern;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", rePassword=" + rePassword
				+ ", incommingPattern=" + incommingPattern + ", vibrationPattern=" + vibrationPattern
				+ ", vibrationContPattern=" + vibrationContPattern + "]";
	}
}
