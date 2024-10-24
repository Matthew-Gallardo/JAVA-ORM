package org.acumen.training.codes.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.acumen.training.codes.HRMSConfiguration;
import org.acumen.training.codes.dao.ProjectDao;
import org.acumen.training.codes.model.Project;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestProjectDao {
	
private HRMSConfiguration config;
	
	@BeforeEach
	public void setup() {
		config = new HRMSConfiguration();
	}
	@AfterEach
	public void teardown() {
		config = null;
	}
	@Disabled
	@Test
	public void testInsert() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		Project proj = new Project();
		proj.setId((short) 8);
		proj.setProjname("Feedback System");
		proj.setProjdate(LocalDate.of(2025, 12, 10));
		dao.insert(proj);
		System.out.println("inserted 1 record");
	}
	@Disabled
	@Test
	public void testUpdate() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.updateProjname((short)8,"Complaint Management System");
		System.out.println("updated 1 record");
	}
	@Disabled
	@Test
	public void testDeleteById() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.deleteById((short)8);
		System.out.println("deleted 1 record");
	}
	@Disabled
	@Test
	public void testUpdateNewDate() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.updateProjDateForKeyword("%system%", LocalDate.of(2030, 01, 03));
		System.out.println("updated 1 record");
	}
	@Test
	public void testDeleteByProjname() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.deletebyProjname("Feedback System");
		System.out.println("deleted 1 record");
	}
	
	@Disabled
	@Test
	public void testSelectAllProject() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		System.out.println(dao.selectAllProject());
	}
	@Disabled
	@Test
	public void testSelectAllProjectCriteria() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		System.out.println(dao.selectAllProjectCriteria());
	}
	@Disabled
	@Test
	public void testSelectSingleProject() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		Project rec = dao.selectSingleProject((short)10);
		System.out.println(rec);
	
	}
	
	@Test
	public void testShowSomeProj() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		java.util.List<Object[]> rec = dao.showSomeProject("%System%");
		rec.forEach((r)->{
			System.out.println(Arrays.toString(r));
		});
		
	}
	
	@Disabled
	@Test
	public void testJoinMembersPerProj() {
		config.createConfiguration();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		java.util.List<Project> rec = dao.joinMembersPerProj();
		System.out.println(rec);
		rec.stream().forEach((r)->{
			System.out.print(r.getProjname()+ " ");
			System.out.print(r.getProjMembers());
			
		});
		
	}
		@Test
		public void testJoinMembersPerProjCriteria() {
			config.createConfiguration();
			SessionFactory sf = config.getSessionFactory();
			ProjectDao dao = new ProjectDao(sf);
			java.util.List<Project> rec = dao.joinMembersPerProjCriteria();
			System.out.println(rec);
			rec.stream().forEach((r)->{
				System.out.print(r.getProjname()+ " ");
				System.out.println(r.getProjMembers());
				
			});
			
	}
	
	
	
	
	
	
}
