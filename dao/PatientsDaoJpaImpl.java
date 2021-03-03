package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Patients;
import com.lawencon.klinik.repo.PatientRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="patientsjpa")
public class PatientsDaoJpaImpl extends BaseDao implements PatientsDao {
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Override
	public Patients getPatientsByCode(String kode) throws Exception {
		return patientRepo.findByPatientCode(kode);
	}

	@Override
	public void insertPatients(Patients patient) throws Exception {
		patientRepo.save(patient);
	}

	@Override
	public List<Patients> getPatients() throws Exception {
		return patientRepo.findAll();
	}

	@Override
	public void updatePatients(Patients data) {
		patientRepo.save(data);
	}

	@Override
	public void deletePatients(Patients p) {
		patientRepo.deleteById(p.getId());
	}
}
