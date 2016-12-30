package nl.itopia.modwillie.core.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nl.itopia.modwillie.data.dao.PatternDao;
import nl.itopia.modwillie.data.data.NotificationType;
import nl.itopia.modwillie.data.model.Pattern;

@Service
@Transactional
public class PatternService {
	@Autowired
	private PatternDao patternDao;
	
	public void addPattern(Pattern pattern) {
		patternDao.addPattern(pattern);
	}
	
	public void updatePattern(Pattern pattern) {
		patternDao.updatePattern(pattern);
	}
	
	public void deletePattern(Pattern pattern) {
		patternDao.deletePattern(pattern);
	}
	
	public Pattern getPattern(long id) {
		return patternDao.getPattern(id);
	}
	
	public List<Pattern> getPatterns(NotificationType type) {
		return patternDao.getPatterns(type);
	}
	
	public List<Pattern> getPatterns() {
		return patternDao.getPatterns();
	}

	public Pattern getFirstPattern(NotificationType type) {
		return patternDao.getFirstPattern(type);
	}
}