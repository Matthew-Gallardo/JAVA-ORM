package org.acumen.training.codes;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UnivConfiguration {
	private Configuration cfg;

	public boolean createConfiguration() {
		try {
			cfg = new Configuration().configure();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public SessionFactory getSessionFactory() {
		try {
			SessionFactory sf = cfg.buildSessionFactory();
			return sf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
