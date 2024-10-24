package org.acumen.training.codes.model;
// Generated Oct 24, 2024, 2:15:02 PM by Hibernate Tools 4.3.6.Final

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * TimeSlot generated by hbm2java
 */
@Entity
@Table(name = "time_slot", schema = "public")
public class TimeSlot {

	private TimeSlotId id;
	private Byte endHr;
	private Byte endMin;

	public TimeSlot() {
	}

	public TimeSlot(TimeSlotId id) {
		this.id = id;
	}

	public TimeSlot(TimeSlotId id, Byte endHr, Byte endMin) {
		this.id = id;
		this.endHr = endHr;
		this.endMin = endMin;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "timeSlotId", column = @Column(name = "time_slot_id", nullable = false, length = 4)),
			@AttributeOverride(name = "day", column = @Column(name = "day", nullable = false, length = 1)),
			@AttributeOverride(name = "startHr", column = @Column(name = "start_hr", nullable = false, precision = 2, scale = 0)),
			@AttributeOverride(name = "startMin", column = @Column(name = "start_min", nullable = false, precision = 2, scale = 0)) })
	public TimeSlotId getId() {
		return this.id;
	}

	public void setId(TimeSlotId id) {
		this.id = id;
	}

	@Column(name = "end_hr", precision = 2, scale = 0)
	public Byte getEndHr() {
		return this.endHr;
	}

	public void setEndHr(Byte endHr) {
		this.endHr = endHr;
	}

	@Column(name = "end_min", precision = 2, scale = 0)
	public Byte getEndMin() {
		return this.endMin;
	}

	public void setEndMin(Byte endMin) {
		this.endMin = endMin;
	}

}
