package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "classroom", schema = "public")
public class Classroom {

	private ClassroomId id;
	private Short capacity;
	private Set<Section> sections = new HashSet<>();

	public Classroom() {
	}

	public Classroom(ClassroomId id) {
		this.id = id;
	}

	public Classroom(ClassroomId id, Short capacity, Set<Section> sections) {
		this.id = id;
		this.capacity = capacity;
		this.sections = sections;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "building", column = @Column(name = "building", nullable = false, length = 15)),
			@AttributeOverride(name = "roomNumber", column = @Column(name = "room_number", nullable = false, length = 7)) })
	public ClassroomId getId() {
		return this.id;
	}

	public void setId(ClassroomId id) {
		this.id = id;
	}

	@Column(name = "capacity", precision = 4)
	public Short getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Short capacity) {
		this.capacity = capacity;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom")
	public Set<Section> getSections() {
		return this.sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		return String.format("Classroom [id=%s, capacity=%s]", id, capacity);
	}
}
