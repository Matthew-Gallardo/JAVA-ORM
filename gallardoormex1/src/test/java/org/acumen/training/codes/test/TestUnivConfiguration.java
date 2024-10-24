package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.acumen.training.codes.UnivConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUnivConfiguration {
	private UnivConfiguration config;

	@BeforeEach
	public void setup() {
		config = new UnivConfiguration();
	}

	@AfterEach
	public void teardown() {
		config = null;
	}

	@Test
	public void testDbConnectivity() {
		assertEquals(true, config.createConfiguration());
		assertNotNull(config.getSessionFactory());
		System.out.println("Database connection OK");
	}
}
