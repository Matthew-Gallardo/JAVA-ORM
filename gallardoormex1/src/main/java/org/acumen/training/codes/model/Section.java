package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Section entity representing section information.
 */
@Entity
@Table(name = "section", schema = "public")
public class Section {

	private SectionId id;
	private Classroom classroom;
	private Course course;
	private String timeSlotId;
	private Set<Instructor> instructors = new HashSet<>();
	private Set<Takes> takeses = new HashSet<>();

	public Section() {
	}

	public Section(SectionId id, Course course) {
		this.id = id;
		this.course = course;
	}

	public Section(SectionId id, Classroom classroom, Course course, String timeSlotId, Set<Instructor> instructors,
			Set<Takes> takeses) {
		this.id = id;
		this.classroom = classroom;
		this.course = course;
		this.timeSlotId = timeSlotId;
		this.instructors = instructors;
		this.takeses = takeses;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "courseId", column = @Column(name = "course_id", nullable = false, length = 8)),
			@AttributeOverride(name = "secId", column = @Column(name = "sec_id", nullable = false, length = 8)),
			@AttributeOverride(name = "semester", column = @Column(name = "semester", nullable = false, length = 6)),
			@AttributeOverride(name = "year", column = @Column(name = "year", nullable = false, precision = 4, scale = 0)) })
	public SectionId getId() {
		return this.id;
	}

	public void setId(SectionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "building", referencedColumnName = "building"),
			@JoinColumn(name = "room_number", referencedColumnName = "room_number") })
	public Classroom getClassroom() {
		return this.classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "time_slot_id", length = 4)
	public String getTimeSlotId() {
		return this.timeSlotId;
	}

	public void setTimeSlotId(String timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sections")
	public Set<Instructor> getInstructors() {
		return this.instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
	public Set<Takes> getTakeses() {
		return this.takeses;
	}

	public void setTakeses(Set<Takes> takeses) {
		this.takeses = takeses;
	}

	@Override
	public String toString() {
		return String.format("Section [id=%s, course=%s, timeSlotId=%s]", id, course, timeSlotId);
	}
}
