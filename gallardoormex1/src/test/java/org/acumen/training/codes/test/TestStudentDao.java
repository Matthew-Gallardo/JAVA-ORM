package org.acumen.training.codes.test;

import java.util.List;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.StudentDao;
import org.acumen.training.codes.model.Department;
import org.acumen.training.codes.model.Student;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestStudentDao {

	private UnivConfiguration config;

	@BeforeEach
	public void setup() {
		config = new UnivConfiguration();
	}

	@AfterEach
	public void teardown() {
		config = null;
	}

	@Disabled
	@Test
	public void testInsertStudent() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		StudentDao dao = new StudentDao(sf);
		Student student = new Student();
		student.setId("123142");
		student.setName("Kai ");
		Department department = new Department();
		department.setDeptName("Information Technology");
		student.setDepartment(department);
		student.setTotalCredits((short) 30);
		boolean result = dao.insertStudent(student);
		System.out.println("Inserted student: " + result);
	}

	@Test
	public void testFindStudentById() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		StudentDao dao = new StudentDao(sf);
		Student student = dao.findStudent("12345");
		System.out.println("Found student: " + student);
	}

	@Test
	public void testFindStudentsPerCourse() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		StudentDao dao = new StudentDao(sf);
		List<Student> students = dao.findStudentsPerCourse("Comp. Sci");
		System.out.println("Students in course: " + students.size());
		students.forEach(System.out::println);
	}
}
