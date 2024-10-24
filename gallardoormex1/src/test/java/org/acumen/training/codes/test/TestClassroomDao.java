package org.acumen.training.codes.test;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.ClassroomDao;
import org.acumen.training.codes.model.Classroom;
import org.acumen.training.codes.model.ClassroomId;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestClassroomDao {
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
		ClassroomDao dao = new ClassroomDao(sf);
		Classroom classroom = new Classroom();
		ClassroomId classid = new ClassroomId();
		classid.setBuilding("Ayala");
		classid.setRoomNumber("163");
		classroom.setId(classid);
		classroom.setCapacity((short) 101);
		dao.insert(classroom);
	}

}
