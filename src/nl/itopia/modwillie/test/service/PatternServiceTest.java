package nl.itopia.modwillie.test.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.junit.Assert;
import nl.itopia.modwillie.core.init.Initializer;
import nl.itopia.modwillie.core.init.TestConfiguration;
import nl.itopia.modwillie.core.service.PatternService;
import nl.itopia.modwillie.data.data.NotificationType;
import nl.itopia.modwillie.data.model.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Initializer.class, TestConfiguration.class})
@WebAppConfiguration
public class PatternServiceTest {
	@Autowired
	private PatternService patternService;
	
	private Pattern pattern;
	
	private String defaultPattern;
	private NotificationType defaultType;
	
	@Before
	public void setup() {
		defaultPattern = "H - H - Z";
		defaultType = NotificationType.INCOMMING;
		
		pattern = new Pattern();
		pattern.setPattern(defaultPattern);
		pattern.setType(defaultType);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAddPattern() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);
		Assert.assertEquals(pattern.getPattern(), patterns.get(0).getPattern());
		Assert.assertEquals(pattern.getType(), patterns.get(0).getType());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdatePattern() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);
		
		Pattern updatePattern = patterns.get(0);
		updatePattern.setPattern("Z - H - H");
		updatePattern.setType(NotificationType.VIBRATION);
		patternService.updatePattern(updatePattern);
		patterns = patternService.getPatterns();
		
		Pattern updatedPattern = patterns.get(0);
		Assert.assertEquals(updatedPattern.getId(), pattern.getId());
		Assert.assertNotEquals(updatedPattern.getPattern(), defaultPattern);
		Assert.assertNotEquals(updatedPattern.getType(), defaultType);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeletePattern() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);
		patternService.deletePattern(pattern);
		patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 0);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetPattern() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);
		
		Pattern getPattern = patternService.getPattern(pattern.getId());
		Assert.assertEquals(getPattern.getId(), pattern.getId());
		Assert.assertEquals(getPattern.getPattern(), pattern.getPattern());
		Assert.assertEquals(getPattern.getType(), pattern.getType());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetPatternsWithType() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);
		
		List<Pattern> getPatterns = patternService.getPatterns(NotificationType.INCOMMING);
		Assert.assertEquals(getPatterns.size(), 1);
		getPatterns = patternService.getPatterns(NotificationType.VIBRATION);
		Assert.assertEquals(getPatterns.size(), 0);
		getPatterns = patternService.getPatterns(NotificationType.VIBRARTION_CONT);
		Assert.assertEquals(getPatterns.size(), 0);
		
		Pattern updatePattern = patterns.get(0);
		updatePattern.setPattern("Z - H - H");
		updatePattern.setType(NotificationType.VIBRATION);
		patternService.updatePattern(updatePattern);
		patterns = patternService.getPatterns();
		
		Pattern updatedPattern = patterns.get(0);
		Assert.assertEquals(updatedPattern.getId(), pattern.getId());
		Assert.assertNotEquals(updatedPattern.getPattern(), defaultPattern);
		Assert.assertNotEquals(updatedPattern.getType(), defaultType);
		
		getPatterns = patternService.getPatterns(NotificationType.INCOMMING);
		Assert.assertEquals(getPatterns.size(), 0);
		getPatterns = patternService.getPatterns(NotificationType.VIBRATION);
		Assert.assertEquals(getPatterns.size(), 1);
		getPatterns = patternService.getPatterns(NotificationType.VIBRARTION_CONT);
		Assert.assertEquals(getPatterns.size(), 0);		
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetPatterns() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);	
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetFirstPattern() {
		patternService.addPattern(pattern);
		List<Pattern> patterns = patternService.getPatterns();
		Assert.assertEquals(patterns.size(), 1);
		
		Pattern getPattern = patternService.getFirstPattern(NotificationType.INCOMMING);
		Assert.assertEquals(getPattern.getId(), pattern.getId());
		Assert.assertEquals(getPattern.getPattern(), pattern.getPattern());
		Assert.assertEquals(getPattern.getType(), pattern.getType());
	}
}
