package com.lawencon.klinik.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author WILLIAM
 *
 */

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "t_m_schedules")
public class Schedules {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_doctor")
	private Doctors idDoctor;
	
	@Column(name = "practice_Day", nullable=false, length=15)
	private String practiceDay;
	
	@Column(name = "practice_begin_time", nullable=false)
	private LocalTime practiceBeginTime;
	
	@Column(name = "practice_end_time", nullable=false)
	private LocalTime practiceEndTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctors getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Doctors idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getPracticeDay() {
		return practiceDay;
	}

	public void setPracticeDay(String practiceDay) {
		this.practiceDay = practiceDay;
	}

	public LocalTime getPracticeBeginTime() {
		return practiceBeginTime;
	}

	public void setPracticeBeginTime(LocalTime practiceBeginTime) {
		this.practiceBeginTime = practiceBeginTime;
	}

	public LocalTime getPracticeEndTime() {
		return practiceEndTime;
	}

	public void setPracticeEndTime(LocalTime practiceEndTime) {
		this.practiceEndTime = practiceEndTime;
	}
}
