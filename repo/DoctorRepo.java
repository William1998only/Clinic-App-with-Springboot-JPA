package com.lawencon.klinik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lawencon.klinik.model.Doctors;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DoctorRepo extends JpaRepository<Doctors, Long> {
	
	Doctors findByDoctorCode(String kode);
	
	Doctors findByIdUserId(Long id);
	
}
