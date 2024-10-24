package org.acumen.training.codes.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	//to fetch the project members
	private Set<ProjectMembers>projMembers = new HashSet<>();
	
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
	
																//If cascade on update and on delete					
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER , cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	public Set<ProjectMembers> getProjMembers() {
		return projMembers;
	}
	public void setProjMembers(Set<ProjectMembers> projMembers) {
		this.projMembers = projMembers;
	}
	
	@Override
	public String toString() {
		return String.format("Project [id=%s, projname=%s, projdate=%s]", projid, projname, projdate);
	}
	
}
