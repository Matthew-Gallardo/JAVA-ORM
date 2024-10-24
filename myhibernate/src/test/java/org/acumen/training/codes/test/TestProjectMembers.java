package org.acumen.training.codes.test;

import org.acumen.training.codes.HRMSConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestProjectMembers {
	private HRMSConfiguration config;
	
	@BeforeEach
	public void Setup() {
		config = new HRMSConfiguration();
		
	}
	
	@AfterEach
	public void Teardown() {
		config= null;
		
	}

}
