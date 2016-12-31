package nl.itopia.modwillie.test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Assert;
import nl.itopia.modwillie.core.init.Initializer;
import nl.itopia.modwillie.core.init.TestConfiguration;
import nl.itopia.modwillie.core.service.SensorService;
import nl.itopia.modwillie.core.service.UserService;
import nl.itopia.modwillie.core.util.HashUtil;
import nl.itopia.modwillie.data.model.Sensor;
import nl.itopia.modwillie.data.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Initializer.class, TestConfiguration.class})
@WebAppConfiguration
public class SensorServiceTest {
	@Autowired
	private SensorService sensorService;
	@Autowired
	private UserService userService;
	
	private int defaultId;
	private String defaultHash;
	private String defaultName;
	
	private Sensor sensor;
	private User user;
	
	@Before
	public void setup() {		
		defaultId = HashUtil.getNewID();
		defaultHash = HashUtil.simpleHash(""+defaultId);
		defaultName = "test_sensor";
		
		user = new User();
		user.setUsername("test_username");
		user.setPassword(HashUtil.hashPassword("test_password"));
		
		sensor = new Sensor();
		sensor.setHash(defaultHash);
		sensor.setName(defaultName);
		sensor.setUser(user);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddSensor() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		Assert.assertEquals(sensor.getId(), sensors.get(0).getId());
		Assert.assertEquals(sensor.getHash(), sensors.get(0).getHash());
		Assert.assertEquals(sensor.getName(), sensors.get(0).getName());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateSensor() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		
		Sensor updateSensor = sensors.get(0);
		updateSensor.setHash(HashUtil.simpleHash(""+HashUtil.getNewID()));
		updateSensor.setName("test_sensor1");
		sensorService.updateSensor(updateSensor);
		sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		
		Sensor updatedSensor = sensors.get(0);
		Assert.assertEquals(updatedSensor.getId(), sensor.getId());
		Assert.assertNotEquals(updatedSensor.getHash(), defaultHash);
		Assert.assertNotEquals(updatedSensor.getName(), defaultName);
		
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteSensor() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		sensorService.deleteSensor(sensor);
		sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 0);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetSensor() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		
		Sensor getSensor = sensorService.getSensor(sensor.getId());
		Assert.assertEquals(getSensor.getId(), sensor.getId());
		Assert.assertEquals(getSensor.getHash(), sensor.getHash());
		Assert.assertEquals(getSensor.getName(), sensor.getName());
		Assert.assertEquals(getSensor.getUser(), sensor.getUser());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetSensors() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetSensorsForUser() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensorsForUser(user.getId());
		Assert.assertEquals(sensors.size(), 1);		
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetSensorForId() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		
		Sensor getSensor = sensorService.getSensorForId(defaultId);
		Assert.assertEquals(getSensor.getId(), sensor.getId());
		Assert.assertEquals(getSensor.getHash(), sensor.getHash());
		Assert.assertEquals(getSensor.getName(), sensor.getName());
		Assert.assertEquals(getSensor.getUser(), sensor.getUser());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetSensorForHash() {
		userService.addUser(user);
		sensorService.addSensor(sensor);
		List<Sensor> sensors = sensorService.getSensors();
		Assert.assertEquals(sensors.size(), 1);
		
		Sensor getSensor = sensorService.getSensorForHash(defaultHash);
		Assert.assertEquals(getSensor.getId(), sensor.getId());
		Assert.assertEquals(getSensor.getHash(), sensor.getHash());
		Assert.assertEquals(getSensor.getName(), sensor.getName());
		Assert.assertEquals(getSensor.getUser(), sensor.getUser());
	}
}
