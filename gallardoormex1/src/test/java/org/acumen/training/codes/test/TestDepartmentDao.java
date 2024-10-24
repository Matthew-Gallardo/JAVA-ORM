package org.acumen.training.codes.test;

import java.math.BigDecimal;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.DepartmentDao;
import org.acumen.training.codes.model.Department;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDepartmentDao {

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
	public void testInsert() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		DepartmentDao dao = new DepartmentDao(sf);
		Department department = new Department();
		department.setDeptName("New ");
		department.setBuilding("Building");
		department.setBudget(new BigDecimal(90000.00));
		dao.insert(department);
	}
}
