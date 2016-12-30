package nl.itopia.modwillie.data.dao;

/**
 * A DAO for the pattern that defines how the objects should be loaded
 * @author Robin de Jong
 */

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.itopia.modwillie.data.data.NotificationType;
import nl.itopia.modwillie.data.model.Pattern;

@Repository
public class PatternDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addPattern(Pattern pattern) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(pattern);
	}
	
	public void updatePattern(Pattern pattern) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(pattern);
	}

	public void deletePattern(Pattern pattern) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(pattern);
	}

	public void removePattern(int id) {
		final Session session = sessionFactory.getCurrentSession();
		Pattern pattern = getPattern(id);
		session.delete(pattern);
	}
	
	public Pattern getPattern(long id) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Pattern p where p.id= :id";
		final Query query = session.createQuery(hql);
		query.setParameter("id", id);
		
		List<?> items = query.list();
		if(items.size() > 0) {
			return (Pattern) items.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pattern> getPatterns() {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Pattern";
		final Query query = session.createQuery(hql);
		return (List<Pattern>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pattern> getPatterns(NotificationType type) {
		final Session session = sessionFactory.getCurrentSession();
		final String hql = "from Pattern p where p.type=:type";
		final Query query = session.createQuery(hql);
		query.setParameter("type", type);
		return (List<Pattern>) query.list();
	}
}