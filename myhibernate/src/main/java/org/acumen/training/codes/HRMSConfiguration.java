package org.acumen.training.codes;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HRMSConfiguration {
	private Configuration cfg;
	
	//Step 1 : Setting up the Configuration instance
	public boolean createConfiguration() {
		try {
			cfg = new Configuration().configure();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Step 2: Setup the SessionFactory
	public SessionFactory getSessionFactory() {
		try {
			SessionFactory sf =cfg.buildSessionFactory();
			return sf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	

}
