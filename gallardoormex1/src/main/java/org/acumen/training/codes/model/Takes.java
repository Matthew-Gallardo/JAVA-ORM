package org.acumen.training.codes.model;
// Generated Oct 24, 2024, 2:15:02 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Takes generated by hbm2java
 */
@Entity
@Table(name = "takes", schema = "public")
public class Takes {

	private TakesId id;
	private Section section;
	private Student student;
	private String grade;

	public Takes() {
	}

	public Takes(TakesId id, Section section, Student student) {
		this.id = id;
		this.section = section;
		this.student = student;
	}

	public Takes(TakesId id, Section section, Student student, String grade) {
		this.id = id;
		this.section = section;
		this.student = student;
		this.grade = grade;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, length = 5)),
			@AttributeOverride(name = "courseId", column = @Column(name = "course_id", nullable = false, length = 8)),
			@AttributeOverride(name = "secId", column = @Column(name = "sec_id", nullable = false, length = 8)),
			@AttributeOverride(name = "semester", column = @Column(name = "semester", nullable = false, length = 6)),
			@AttributeOverride(name = "year", column = @Column(name = "year", nullable = false, precision = 4, scale = 0)) })
	public TakesId getId() {
		return this.id;
	}

	public void setId(TakesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "sec_id", referencedColumnName = "sec_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "semester", referencedColumnName = "semester", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "year", referencedColumnName = "year", nullable = false, insertable = false, updatable = false) })
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Column(name = "grade", length = 2)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
