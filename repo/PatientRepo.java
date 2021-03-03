package com.lawencon.klinik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Patients;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface PatientRepo extends JpaRepository<Patients, Long>{
	
	Patients findByPatientCode(String kode);

}
