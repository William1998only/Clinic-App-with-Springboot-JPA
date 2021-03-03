package com.lawencon.klinik.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author WILLIAM
 *
 */
@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "t_h_disease_histories")
public class DiseaseHistories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_dtl_disease")
	private DiseasesDtl idDtlDisease;
	
	@Transient
	private List<MedicinesDtl> meds;

	public List<MedicinesDtl> getMeds() {
		return meds;
	}

	public void setMeds(List<MedicinesDtl> meds) {
		this.meds = meds;
	}

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

}
