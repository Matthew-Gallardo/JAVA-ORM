package org.acumen.training.codes.dao;

import org.acumen.training.codes.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

public class DepartmentDao {
	private static final Logger LOGGER = Logger.getLogger(DepartmentDao.class);

	private SessionFactory sf;

	public DepartmentDao(SessionFactory sf) {
		this.sf = sf;
	}

	public boolean insert(Department department) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			LOGGER.infof("Inserting department: %s", department);
			sess.persist(department); // Insert into
			tx.commit();
			LOGGER.infof("Department inserted successfully: %s", department);
			return true;
		} catch (Exception e) {
			LOGGER.errorf("Failed to insert department: %s", department, e);
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}
		return false;
	}
}
