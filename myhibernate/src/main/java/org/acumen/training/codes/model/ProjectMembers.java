package org.acumen.training.codes.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "hrms", name = "project_members")
public class ProjectMembers {
	private Integer empid;
	private String firstname;
	private String lastname;
	private Integer projid;
	
	private Project project;
	
	@Id
	@Column(name = "empid" , nullable = false, unique = true)
	public Integer getEmpid() {
		return empid;
	}
	
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	
	@Column(length = 100, nullable = false)
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(length = 100, nullable = false)
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	@Column(name = "projid", nullable = false)
	public Integer getProjid() {
		return projid;
	}
	
	public void setProjid(Integer projid) {
		this.projid = projid;
	}
	
	@ManyToOne
	@JoinColumn(name = "projid" , insertable = false, updatable = false) // foreign key not the primary in parent
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return String.format("ProjectMembers [empid=%s, firstname=%s, lastname=%s, projid=%s]", empid, firstname,
				lastname, projid);
	}
	
	
	
	

}
