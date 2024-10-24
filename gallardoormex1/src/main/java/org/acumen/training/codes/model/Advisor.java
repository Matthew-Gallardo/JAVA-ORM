package org.acumen.training.codes.model;
// Generated Oct 24, 2024, 2:15:02 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * Advisor generated by hbm2java
 */
@Entity
@Table(name = "advisor", schema = "public")
public class Advisor {

	private String SId;
	private Instructor instructor;
	private Student student;

	public Advisor() {
	}

	public Advisor(Student student) {
		this.student = student;
	}

	public Advisor(Instructor instructor, Student student) {
		this.instructor = instructor;
		this.student = student;
	}

	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "s_id", unique = true, nullable = false, length = 5)
	public String getSId() {
		return this.SId;
	}

	public void setSId(String SId) {
		this.SId = SId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "i_id")
	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
