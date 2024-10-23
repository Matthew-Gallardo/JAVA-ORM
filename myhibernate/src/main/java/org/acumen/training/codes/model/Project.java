package org.acumen.training.codes.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//JAXB parsing

@Entity
@Table(catalog = "hrms", name = "project")
public class Project {
	private Short projid;
	private String projname;
	private LocalDate projdate;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id" , nullable =  false, unique = true)
	public Short getId() {
		return projid;
	}
	public void setId(Short id) {
		this.projid = id;
	}
	@Column(nullable = false, length = 200)
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	//@Temporal(TemporalType.DATE) If the data is java.sql.data
	@Column(nullable = false)
	public LocalDate getProjdate() {
		return projdate;
	}
	public void setProjdate(LocalDate projdate) {
		this.projdate = projdate;
	}
	@Override
	public String toString() {
		return String.format("Project [id=%s, projname=%s, projdate=%s]", projid, projname, projdate);
	}
	
}
