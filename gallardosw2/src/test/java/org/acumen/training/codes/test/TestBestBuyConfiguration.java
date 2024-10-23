package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.acumen.training.codes.BestBuyConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBestBuyConfiguration {
	private BestBuyConfiguration config;
	
	@BeforeEach
	public void setup() {
		config = new BestBuyConfiguration();
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
