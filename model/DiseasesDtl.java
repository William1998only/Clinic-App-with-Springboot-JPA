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
@Table(name = "t_r_dtl_diseases")
public class DiseasesDtl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_invoice")
	private InvoicesHdr idInvoice;
	

	@ManyToOne
	@JoinColumn(name = "id_disease")
	private Diseases idDisease;

	@ManyToOne
	@JoinColumn(name = "id_doctor")
	private Doctors idDoctor;

	@Transient
	private List<MedicinesDtl> meds;

	public List<MedicinesDtl> getMeds() {
		return meds;
	}

	public void setMeds(List<MedicinesDtl> meds) {
		this.meds = meds;
	}

	public Doctors getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Doctors idDoctor) {
		this.idDoctor = idDoctor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InvoicesHdr getIdInvoice() {
		return idInvoice;
	}

	public void setIdInvoice(InvoicesHdr idInvoice) {
		this.idInvoice = idInvoice;
	}

	public Diseases getIdDisease() {
		return idDisease;
	}

	public void setIdDisease(Diseases idDisease) {
		this.idDisease = idDisease;
	}

}
