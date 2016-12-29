package nl.itopia.modwillie.core.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nl.itopia.modwillie.data.dao.SensorDao;
import nl.itopia.modwillie.data.model.Sensor;
import nl.itopia.modwillie.data.model.User;

@Service
@Transactional
public class SensorService {
	@Autowired
	private SensorDao sensorDao;
	
	public void addSensor(Sensor sensor) {
		sensorDao.addSensor(sensor);
	}
	
	public void updateSensor(Sensor sensor) {
		sensorDao.updateSensor(sensor);
	}
	
	public void deleteSensor(Sensor sensor) {
		sensorDao.deleteSensor(sensor);
	}
	
	public String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		return encoder.encode(password);
	}	
	
	public Sensor getSensor(long id) {
		return sensorDao.getSensor(id);
	}
	
	public List<Sensor> getSensors() {
		return sensorDao.getSensors();
	}

	public List<Sensor> getSensorsForUser(User user) {
		return getSensorsForUser(user.getId());
	}
	
	public List<Sensor> getSensorsForUser(long id) {
		return sensorDao.getSensorsForUser(id);
	}
}