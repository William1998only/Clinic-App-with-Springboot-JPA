package com.lawencon.klinik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.klinik.model.Medicines;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicineRepo extends JpaRepository<Medicines, Long>{
	
	Medicines findByMedCode(String kode);
	
}
