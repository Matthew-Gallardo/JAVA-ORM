package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Student entity representing student information.
 */
@Entity
@Table(name = "student", schema = "public")
public class Student {

	private String id;
	private Department department;
	private String name;
	private Short totalCredits;
	private Set<Takes> takes = new HashSet<>();
	private Advisor advisor;

	public Student() {
	}

	public Student(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Student(String id, Department department, String name, Short totalCredits, Set<Takes> takes,
			Advisor advisor) {
		this.id = id;
		this.department = department;
		this.name = name;
		this.totalCredits = totalCredits;
		this.takes = takes;
		this.advisor = advisor;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 5)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_name")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tot_cred", precision = 3, scale = 0)
	public Short getTotalCredits() {
		return this.totalCredits;
	}

	public void setTotalCredits(Short totalCredits) {
		this.totalCredits = totalCredits;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
	public Set<Takes> getTakes() {
		return this.takes;
	}

	public void setTakes(Set<Takes> takes) {
		this.takes = takes;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "student")
	public Advisor getAdvisor() {
		return this.advisor;
	}

	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s, totalCredits=%s]", id, name, totalCredits);
	}
}
