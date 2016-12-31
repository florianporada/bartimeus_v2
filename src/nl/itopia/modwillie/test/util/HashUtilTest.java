package nl.itopia.modwillie.test.util;

import static org.junit.Assert.*;

import org.junit.Test;
import nl.itopia.modwillie.core.util.HashUtil;

public class HashUtilTest {
	
	@Test
	public void testSimpleHash() {
		String hash = HashUtil.simpleHash("1");
		assertEquals(hash, "6B86B273");
		
		hash = HashUtil.simpleHash("2");
		assertEquals(hash, "D4735E3A");
		
		hash = HashUtil.simpleHash("3");
		assertEquals(hash, "4E074085");
	}
	
	@Test
	public void testHash() {
		String hash = HashUtil.hash("1");
		assertEquals(hash, "6B86B273FF34FCE19D6B804EFF5A3F5747ADA4EAA22F1D49C01E52DDB7875B4B");
		
		hash = HashUtil.hash("2");
		assertEquals(hash, "D4735E3A265E16EEE03F59718B9B5D03019C07D8B6C51F90DA3A666EEC13AB35");
		
		hash = HashUtil.hash("3");
		assertEquals(hash, "4E07408562BEDB8B60CE05C1DECFE3AD16B72230967DE01F640B7E4729B49FCE");
	}
	
	@Test
	public void testHashPassword() {
		String pass = HashUtil.hashPassword("really strong password!");
		assertNotEquals(pass, "$2a$10$XoPpcPAyfC2NKZT1l0m5Ke2KZDDmZ7ZW/3BmGnOHOYgyQKoI93Eu6");
	}
}
