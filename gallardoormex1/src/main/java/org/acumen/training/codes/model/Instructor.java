package org.acumen.training.codes.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor", schema = "public")
public class Instructor {

	private String id;
	private Department department;
	private String name;
	private BigDecimal salary;
	private Set<Section> sections = new HashSet<>();
	private Set<Advisor> advisors = new HashSet<>();

	public Instructor() {
	}

	public Instructor(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Instructor(String id, Department department, String name, BigDecimal salary, Set<Section> sections,
			Set<Advisor> advisors) {
		this.id = id;
		this.department = department;
		this.name = name;
		this.salary = salary;
		this.sections = sections;
		this.advisors = advisors;
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

	@Column(name = "salary", precision = 8)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "teaches", schema = "public", joinColumns = @JoinColumn(name = "id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "course_id", nullable = false, updatable = false))
	public Set<Section> getSections() {
		return this.sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "instructor")
	public Set<Advisor> getAdvisors() {
		return this.advisors;
	}

	public void setAdvisors(Set<Advisor> advisors) {
		this.advisors = advisors;
	}

	@Override
	public String toString() {
		return String.format("Instructor [id=%s, name=%s, salary=%s]", id, name, salary);
	}
}
