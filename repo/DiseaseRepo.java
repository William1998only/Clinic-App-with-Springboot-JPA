package com.lawencon.klinik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Diseases;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface DiseaseRepo extends JpaRepository<Diseases, Long>{
	
	Diseases findByDiseaseCode(String kode);

}
