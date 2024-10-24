package org.acumen.training.codes.dao;

import java.util.List;
import org.acumen.training.codes.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

public class StudentDao {
	private static final Logger LOGGER = Logger.getLogger(StudentDao.class);

	private SessionFactory sf;

	public StudentDao(SessionFactory sf) {
		this.sf = sf;
	}

	// Method to insert a student
	public boolean insertStudent(Student student) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			sess.persist(student); // Insert into the student table
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				LOGGER.errorf("Error rolling back transaction: %s", e1.getMessage());
			}
			LOGGER.errorf("Error inserting student: %s", e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return false;
	}

	// Method to find a student by their ID
	public Student findStudent(String studentId) {
		Student student = null;
		try (Session sess = sf.openSession()) {
			student = sess.get(Student.class, studentId);
			if (student != null) {
				LOGGER.infof("Student found: %s", student.toString());
			} else {
				LOGGER.warnf("Student with ID %s not found.", studentId);
			}
		} catch (Exception e) {
			LOGGER.errorf("Error finding student with ID %s: %s", studentId, e.getMessage());
		}
		return student;
	}

	// Method to find students by course name
	public List<Student> findStudentsPerCourse(String course) {
		List<Student> students = null;
		try (Session sess = sf.openSession()) {
			String hql = "select s from Student s join s.takes t join t.course c where c.courseName = :courseName";
			Query<Student> query = sess.createQuery(hql, Student.class);
			query.setParameter("courseName", course);
			students = query.getResultList();
			LOGGER.infof("Number of students in course %s: %d", course, students.size());
		} catch (Exception e) {
			LOGGER.errorf("Error finding students for course %s: %s", course, e.getMessage());
		}
		return students;
	}
}
