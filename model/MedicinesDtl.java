package com.lawencon.klinik.model;

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
@Table(name = "t_r_dtl_medicines")
public class MedicinesDtl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_dtl_disease")
	private DiseasesDtl idDtlDisease;
	
	@ManyToOne
	@JoinColumn(name = "id_med")
	private Medicines idMedicine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiseasesDtl getIdDtlDisease() {
		return idDtlDisease;
	}

	public void setIdDtlDisease(DiseasesDtl idDtlDisease) {
		this.idDtlDisease = idDtlDisease;
	}

	public Medicines getIdMedicine() {
		return idMedicine;
	}

	public void setIdMedicine(Medicines idMedicine) {
		this.idMedicine = idMedicine;
	}
}
