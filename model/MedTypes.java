package com.lawencon.klinik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "t_m_med_type")
public class MedTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "med_type", nullable=false, length=20)
	private String medType;
	
	@Column(name = "med_code_type", unique = true, nullable=false)
	private Integer medCodeType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public Integer getMedCodeType() {
		return medCodeType;
	}

	public void setMedCodeType(Integer medCodeType) {
		this.medCodeType = medCodeType;
	}
}
