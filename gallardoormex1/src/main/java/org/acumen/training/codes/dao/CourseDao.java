package org.acumen.training.codes.dao;

import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

public class CourseDao {
	private static final Logger LOGGER = Logger.getLogger(CourseDao.class);
	private SessionFactory sf;

	public CourseDao(SessionFactory sf) {
		this.sf = sf;
	}

	// Method to insert courses into the database
	public boolean insertCourse(Course course) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			sess.persist(course); // Insert into
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.errorf("Failed to insert course: %s", course);

		} finally {
			sess.close();
		}
		return false;
	}

	// Method to find courses by credits
	public List<String> findCourses(int credits) {
		Session sess = sf.openSession();
		try {
			String hql = "select c.title from Course c where c.credits = :credits";
			Query<String> query = sess.createQuery(hql, String.class);
			query.setParameter("credits", (byte) credits);
			return query.getResultList();
		} catch (Exception e) {
			LOGGER.errorf("Failed to find courses for credits: %d", credits);

		} finally {
			sess.close();
		}
		return Collections.emptyList();
	}

	// Method to find the number of credits taken by a student
	public int findNumCourses(String studentId) {
		Session sess = sf.openSession();
		try {
			String hql = "select sum(c.credits) from Takes t join t.course c where t.student.id = :studentId";
			Query<Long> query = sess.createQuery(hql, Long.class);
			query.setParameter("studentId", studentId);
			Long totalCredits = query.uniqueResult();
			return totalCredits != null ? totalCredits.intValue() : 0;
		} catch (Exception e) {
			LOGGER.errorf("Failed to find number of courses for student ID: %d", studentId);

		} finally {
			sess.close();
		}
		return 0;
	}

	// Method to find total credits for each student
	public List<Object[]> findTotalUnitsPerStud() {
		Session sess = sf.openSession();
		try {
			String hql = "select t.student.id, sum(c.credits) from Takes t join t.course c group by t.student.id";
			Query<Object[]> query = sess.createQuery(hql, Object[].class);
			return query.getResultList();
		} catch (Exception e) {
			LOGGER.error("Failed to find total credits per student.");

		} finally {
			sess.close();
		}
		return Collections.emptyList();
	}
}
