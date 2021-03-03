package com.lawencon.klinik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.MedicinesDtl;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface MedDtlRepo extends JpaRepository<MedicinesDtl, Long>{

	@Query(value=" SELECT m.medName " + "from MedicinesDtl dm " +
	"INNER JOIN dm.idDtlDisease dd " + "INNER JOIN dd.idInvoice hi " + 
	"INNER JOIN hi.idPatient p " + "INNER JOIN dm.idMedicine m " +
	"WHERE p.patientCode = ?1 and dm.idDtlDisease = ?2")
	List<MedicinesDtl> findByPatientCodeAndIdDtlDisease(String kodePasien, Long idDtlPenyakit);

	@Query(value=" SELECT m.medName, m.price " + "from MedicinesDtl as dm " +
			"INNER JOIN dm.idDtlDisease as dd " + "INNER JOIN dd.idInvoice as hi " + 
			"INNER JOIN hi.idPatient as p " + "INNER JOIN dm.idMedicine m " +
			"WHERE dm.idDtlDisease = ?1")
	List<Object[]> findByIdDtlDisease(DiseasesDtl dd);
	
}
