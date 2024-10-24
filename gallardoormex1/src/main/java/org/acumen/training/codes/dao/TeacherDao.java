package org.acumen.training.codes.dao;

import java.math.BigDecimal;
import java.util.List;

import org.acumen.training.codes.model.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

public class TeacherDao {
	private static final Logger LOGGER = Logger.getLogger(TeacherDao.class);

	private SessionFactory sf;

	public TeacherDao(SessionFactory sf) {
		this.sf = sf;
	}

	// Method to update a teacher's salary
	public boolean updateSalary(String instructorId, float updatedSalary) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			Instructor instructor = sess.get(Instructor.class, instructorId);
			if (instructor != null) {
				instructor.setSalary(BigDecimal.valueOf(updatedSalary));
				sess.merge(instructor);
				tx.commit();
				LOGGER.infof("Updated salary for instructor ID %d to %.2f", instructorId, updatedSalary);
				return true;
			} else {
				LOGGER.warnf("Instructor with ID %d not found.", instructorId);
			}
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception rollbackEx) {
				LOGGER.errorf("Error during rollback: %s", rollbackEx.getMessage());
			}
			LOGGER.errorf("Error updating salary for instructor ID %d: %s", instructorId, e.getMessage());
		} finally {
			sess.close();
		}
		return false;
	}

	// Method to delete an instructor by ID
	public boolean deleteInstructor(String instructorId) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			Instructor instructor = sess.get(Instructor.class, instructorId);
			if (instructor != null) {
				sess.remove(instructor);
				tx.commit();
				LOGGER.infof("Instructor with ID %d deleted successfully.", instructorId);
				return true;
			} else {
				LOGGER.warnf("Instructor with ID %d not found.", instructorId);
			}
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception rollbackEx) {
				LOGGER.errorf("Error during rollback: %s", rollbackEx.getMessage());
			}
			LOGGER.errorf("Error deleting instructor ID %d: %s", instructorId, e.getMessage());
		} finally {
			sess.close();
		}
		return false;
	}

	// Method to insert three instructors
	public void insertInstructor(Instructor instructor) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			sess.persist(instructor);
			tx.commit();
			LOGGER.infof("Instructor %s inserted successfully.", instructor.getName());
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception rollbackEx) {
				LOGGER.errorf("Error during rollback: %s", rollbackEx.getMessage());
			}
			LOGGER.errorf("Error inserting instructor %s: %s", instructor.getName(), e.getMessage());
		} finally {
			sess.close();
		}
	}

	// Method to find all instructors in a certain department
	public List<Instructor> findInstructors(String department) {
		List<Instructor> instructors = null;
		Session sess = sf.openSession();
		try {
			String hql = "from Instructor i where i.department.deptName = :deptName";
			instructors = sess.createQuery(hql, Instructor.class).setParameter("deptName", department).getResultList();
			LOGGER.infof("Found %d instructors in department %s.", instructors.size(), department);
		} catch (Exception e) {
			LOGGER.errorf("Error finding instructors in department %s: %s", department, e.getMessage());
		} finally {
			sess.close();
		}
		return instructors;
	}

	// Method to find all IDs of instructors who have never taught a course
	public List<String> findInstructorNoCourses() {
		List<String> instructorIds = null;
		Session sess = sf.openSession();
		try {
			String hql = "select i.id from Instructor i where i.sections is empty";
			instructorIds = sess.createQuery(hql, String.class).getResultList();
			LOGGER.infof("Found %d instructors with no courses taught.", instructorIds.size());
		} catch (Exception e) {
			LOGGER.errorf("Error finding instructors with no courses: %s", e.getMessage());
		} finally {
			sess.close();
		}
		return instructorIds;
	}
}
