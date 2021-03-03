package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.Patients;

/**
 * 
 * @author WILLIAM
 *
 */
public interface PatientsService {
	List<Patients> getPatients() throws Exception;

	Patients getPatientsByCode(String kode) throws Exception;

	void insertPatients(Patients patient) throws Exception;

	void updatePatients(Patients data) throws Exception;

	void deletePatients(Patients data) throws Exception;
	
}
