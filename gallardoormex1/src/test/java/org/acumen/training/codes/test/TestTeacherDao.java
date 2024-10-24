package org.acumen.training.codes.test;

import java.math.BigDecimal;
import java.util.List;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.TeacherDao;
import org.acumen.training.codes.model.Department;
import org.acumen.training.codes.model.Instructor;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestTeacherDao {

	private UnivConfiguration config;
	private TeacherDao teacherDao;

	@BeforeEach
	public void setup() {
		config = new UnivConfiguration();
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		teacherDao = new TeacherDao(sf);
	}

	@AfterEach
	public void teardown() {
		config = null;
	}

	@Disabled
	@Test
	public void testInsertInstructor() {
		Instructor instructor = new Instructor();
		instructor.setId("111111");
		instructor.setName("Walter White");
		instructor.setSalary(BigDecimal.valueOf(50000));
		Department department = new Department();
		department.setDeptName("Information Technology");
		instructor.setDepartment(department);
		teacherDao.insertInstructor(instructor);
		System.out.println("Inserted 1 instructor record");
	}

	@Disabled
	@Test
	public void testUpdateSalary() {
		String instructorId = "111111";
		float updatedSalary = 55000f;
		boolean result = teacherDao.updateSalary(instructorId, updatedSalary);
		System.out.println("Updated salary for instructor ID " + instructorId + ": " + result);
	}

	@Disabled
	@Test
	public void testDeleteInstructor() {
		String instructorId = "111111";
		boolean result = teacherDao.deleteInstructor(instructorId);
		System.out.println("Deleted instructor ID " + instructorId + ": " + result);
	}

	@Disabled
	@Test
	public void testFindInstructorsInDepartment() {
		String departmentName = "Comp. Sci.";
		List<Instructor> instructors = teacherDao.findInstructors(departmentName);
		System.out.println("Found instructors in department " + departmentName + ": " + instructors.size());
	}

	@Disabled
	@Test
	public void testFindInstructorNoCourses() {
		List<String> instructorIds = teacherDao.findInstructorNoCourses();
		System.out.println("Found instructors with no courses taught: " + instructorIds.size());
	}
}
