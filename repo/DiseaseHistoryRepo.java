package com.lawencon.klinik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseaseHistories;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface DiseaseHistoryRepo extends JpaRepository<DiseaseHistories, Long>{
	
	@Query(value = "SELECT p.name, d.diseaseName, i.checkUpDate, " +
			"dh.idDtlDisease.id, dd.idInvoice.id " + "FROM DiseaseHistories dh " +
			"INNER JOIN dh.idDtlDisease as dd " +
			"INNER JOIN dd.idInvoice as i " +
			"INNER JOIN i.idPatient as p " +
			"INNER JOIN dd.idDisease as d " + "WHERE p.patientCode = ?1")
	List<Object[]> findByPatientCode(String patientCode);
}
