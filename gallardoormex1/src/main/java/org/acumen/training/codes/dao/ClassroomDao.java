package org.acumen.training.codes.dao;

import org.acumen.training.codes.model.Classroom;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

public class ClassroomDao {
	private static final Logger LOGGER = Logger.getLogger(ClassroomDao.class);

	private SessionFactory sf;

	public ClassroomDao(SessionFactory sf) {
		this.sf = sf;
	}

	public boolean insert(Classroom classroom) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			LOGGER.infof("Inserting classroom: %s", classroom);
			sess.persist(classroom); // Insert into
			tx.commit();
			LOGGER.infof("classroom inserted successfully: %s", classroom);
			return true;
		} catch (Exception e) {
			LOGGER.errorf("Failed to insert classroom: %s", classroom, e);
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
