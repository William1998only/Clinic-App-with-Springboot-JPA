package com.lawencon.klinik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "t_m_doctors")
public class Doctors {
	
	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Users idUser;
	
	@Column(name = "licence_number", nullable=false, length = 30)
	private Integer licenceNumber;
	
	@Column(name = "doctor_code", unique=true, nullable=false, length = 15)
	private String doctorCode;

	public String getDoctorCode() {
		return doctorCode;
	}

	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getIdUser() {
		return idUser;
	}

	public void setIdUser(Users idUser) {
		this.idUser = idUser;
	}

	public Integer getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(Integer licenceNumber) {
		this.licenceNumber = licenceNumber;
	}
}
