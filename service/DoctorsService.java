package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.Doctors;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DoctorsService {

	void insertDoctor(String inputUsername, int inputNoPraktek) throws Exception;

	List<Doctors> getDoctor();
	
	Doctors getDoctorByCode(String inputKode) throws Exception;

	Doctors getDoctorById(Long id);

	void deleteDoctor(Doctors d);
	
	Doctors getDoctorByIdUser(Long id);

}
