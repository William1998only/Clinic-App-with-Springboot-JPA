package com.lawencon.klinik.dao;

import java.util.List;

import com.lawencon.klinik.model.Patients;

/**
 * 
 * @author WILLIAM
 *
 */
public interface PatientsDao {
	List<Patients> getPatients() throws Exception;

	Patients getPatientsByCode(String kode) throws Exception;

	void insertPatients(Patients patient) throws Exception;

	void updatePatients(Patients data);

	void deletePatients(Patients data);
	
}
