package org.acumen.training.codes.test;

import java.util.List;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.CourseDao;
import org.acumen.training.codes.model.Course;
import org.acumen.training.codes.model.Department;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCourseDao {

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
	public void testInsertCourse() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		CourseDao dao = new CourseDao(sf);
		Course course = new Course();
		course.setCourseId("C001");
		course.setTitle("Introduction to Programming");
		course.setCredits((byte) 3);
		Department department = new Department();
		department.setDeptName("Information Technology");
		course.setDepartment(department);
		boolean result = dao.insertCourse(course);
		System.out.println("Inserted course: " + result);
	}

	@Test
	public void testFindCoursesByCredits() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		CourseDao dao = new CourseDao(sf);
		List<String> courses = dao.findCourses(3);
		System.out.println("Courses with 3 credits: " + courses.size());
		courses.forEach(System.out::println);
	}

	@Test
	public void testFindNumCourses() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		CourseDao dao = new CourseDao(sf);
		int totalCredits = dao.findNumCourses("00128");
		System.out.println("Total credits for student ID 00128: " + totalCredits);
	}

	@Test
	public void testFindTotalUnitsPerStud() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		CourseDao dao = new CourseDao(sf);
		List<Object[]> totals = dao.findTotalUnitsPerStud();
		System.out.println("Total credits per student:");
		totals.forEach(total -> System.out.println("Student ID: " + total[0] + ", Total Credits: " + total[1]));
	}
}
