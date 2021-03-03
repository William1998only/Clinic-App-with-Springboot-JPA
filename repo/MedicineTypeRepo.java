package com.lawencon.klinik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.klinik.model.MedTypes;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicineTypeRepo extends JpaRepository<MedTypes, Long>{
	
	MedTypes findByMedCodeType(int medType);

}
