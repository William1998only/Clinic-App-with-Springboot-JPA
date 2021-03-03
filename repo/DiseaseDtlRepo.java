package com.lawencon.klinik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseasesDtl;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface DiseaseDtlRepo extends JpaRepository<DiseasesDtl, Long>{
	
	@Query(value = "SELECT p.name, d.diseaseName, i.checkUpDate, " +
			"dd.id, dd.idInvoice.id, dd.idInvoice.invoiceCode " + "FROM DiseasesDtl dd " +
			"INNER JOIN dd.idInvoice as i " +
			"INNER JOIN i.idPatient as p " +
			"INNER JOIN dd.idDisease as d " + "WHERE i.invoiceCode = ?1")
	List<Object[]> findByInvoiceCode(String invoiceCode);
	
}
