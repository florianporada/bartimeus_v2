package nl.itopia.modwillie.data.dao;

/**
 * A DAO for the user that defines how the objects should be loaded
 * @author Robin de Jong
 */

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import nl.itopia.modwillie.data.model.User;

@Repository
public class UserDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addUser(User user) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	public void updateUser(User user) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	public void removeUser(int id) {
		final Session session = sessionFactory.getCurrentSession();
		User user = getUser(id);
		session.delete(user);
	}
	
	public User getUserWithName(String name) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from User u where u.username= :username";
		final Query query = session.createQuery(hql);
		query.setParameter("username", name);
		
		List<?> items = query.list();
		if(items.size() > 0) {
			return (User) items.get(0);
		}
		
		return null;
	}
	
	public User getUser(long id) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from User u where u.id= :id";
		final Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<?> items = query.list();
		if(items.size() > 0) {
			return (User) items.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from User";
		final Query query = session.createQuery(hql);
		return (List<User>) query.list();
	}
}