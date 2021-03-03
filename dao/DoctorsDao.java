package com.lawencon.klinik.dao;

import java.util.List;

import com.lawencon.klinik.model.Doctors;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DoctorsDao {

	void insertDoctor(Doctors doctor);
	
	List<Doctors> getDoctor();
	
	Doctors getDoctorByCode(String inputKode);

	Doctors getDoctorById(Long id);

	void deleteDoctor(Doctors d);
	
	Doctors getDoctorByIdUser(Long id);

}
