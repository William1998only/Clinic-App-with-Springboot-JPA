package com.lawencon.klinik.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "t_r_hdr_invoices")
public class InvoicesHdr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_patient")
	private Patients idPatient;
	
	@Column(name = "sick_fee")
	private BigDecimal sickFee;
	
	@Column(name = "invoice_code", unique=true, nullable=false, length=20)
	private String invoiceCode;
	
	@Column(name = "queue_number", nullable=false)
	private Integer queueNumber;
	
	@Column(name = "checkup_date", nullable=false)
	private LocalDate checkUpDate;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Users idUser;

	public Integer getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(Integer queueNumber) {
		this.queueNumber = queueNumber;
	}

	public LocalDate getCheckUpDate() {
		return checkUpDate;
	}

	public void setCheckUpDate(LocalDate checkUpDate) {
		this.checkUpDate = checkUpDate;
	}

	public Users getIdUser() {
		return idUser;
	}

	public void setIdUser(Users idUser) {
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patients getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Patients idPatient) {
		this.idPatient = idPatient;
	}

	public BigDecimal getSickFee() {
		return sickFee;
	}

	public void setSickFee(BigDecimal sickFee) {
		this.sickFee = sickFee;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
}
