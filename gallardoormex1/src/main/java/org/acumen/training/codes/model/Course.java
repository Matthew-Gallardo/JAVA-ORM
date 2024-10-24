package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Course entity representing course information.
 */
@Entity
@Table(name = "course", schema = "public")
public class Course {

	private String courseId;
	private Department department;
	private String title;
	private Byte credits;

	private Set<Course> coursesForCourseId = new HashSet<>();
	private Set<Section> sections = new HashSet<>();
	private Set<Course> coursesForPrereqId = new HashSet<>();

	public Course() {
	}

	public Course(String courseId) {
		this.courseId = courseId;
	}

	public Course(String courseId, Department department, String title, Byte credits, Set<Course> coursesForCourseId,
			Set<Section> sections, Set<Course> coursesForPrereqId) {
		this.courseId = courseId;
		this.department = department;
		this.title = title;
		this.credits = credits;
		this.coursesForCourseId = coursesForCourseId;
		this.sections = sections;
		this.coursesForPrereqId = coursesForPrereqId;
	}

	@Id
	@Column(name = "course_id", unique = true, nullable = false, length = 8)
	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_name")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "credits", precision = 2)
	public Byte getCredits() {
		return this.credits;
	}

	public void setCredits(Byte credits) {
		this.credits = credits;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "coursesForPrereqId")
	public Set<Course> getCoursesForCourseId() {
		return this.coursesForCourseId;
	}

	public void setCoursesForCourseId(Set<Course> coursesForCourseId) {
		this.coursesForCourseId = coursesForCourseId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	public Set<Section> getSections() {
		return this.sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "coursesForCourseId")
	public Set<Course> getCoursesForPrereqId() {
		return this.coursesForPrereqId;
	}

	public void setCoursesForPrereqId(Set<Course> coursesForPrereqId) {
		this.coursesForPrereqId = coursesForPrereqId;
	}

	@Override
	public String toString() {
		return String.format("Course [courseId=%s, title=%s, credits=%s]", courseId, title, credits);
	}
}
