package nl.itopia.modwillie.data.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import nl.itopia.modwillie.data.model.Sensor;

/**
 * A DAO for the sensor that defines how the objects should be loaded
 * @author Robin de Jong
 */
@Repository
public class SensorDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addSensor(Sensor sensor) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(sensor);
	}
	
	public void updateSensor(Sensor sensor) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(sensor);
	}

	public void deleteSensor(Sensor sensor) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(sensor);
	}

	public void removeSensor(int id) {
		final Session session = sessionFactory.getCurrentSession();
		Sensor sensor = getSensor(id);
		session.delete(sensor);
	}
	
	public Sensor getSensor(long id) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Sensor s where s.id= :id";
		final Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<?> items = query.list();
		if(items.size() > 0) {
			return (Sensor) items.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sensor> getSensors() {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Sensor";
		final Query query = session.createQuery(hql);
		return (List<Sensor>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Sensor> getSensorsForUser(long id) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Sensor s where s.user.id = :id";
		final Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (List<Sensor>) query.list();
	}

	public Sensor getSensorForHash(String hash) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Sensor s where s.hash= :hash";
		final Query query = session.createQuery(hql);
		query.setParameter("hash", hash);
		
		List<?> items = query.list();
		if(items.size() > 0) {
			return (Sensor) items.get(0);
		}
		
		return null;
	}
}