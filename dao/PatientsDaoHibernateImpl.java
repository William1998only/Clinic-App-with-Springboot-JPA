package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Patients;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="patientshibernate")
public class PatientsDaoHibernateImpl extends BaseDao implements PatientsDao {
	@Override
	public Patients getPatientsByCode(String kode) throws Exception {
		List<Patients> listResult = em.createQuery("FROM Patients where patientCode = ?1 ", Patients.class)
				.setParameter(1, kode).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void insertPatients(Patients patient) throws Exception {
		em.persist(patient);
	}

	@Override
	public List<Patients> getPatients() throws Exception {
		List<Patients> listResult = em.createQuery("FROM Patients", Patients.class).getResultList();
		return listResult;
	}

	@Override
	public void updatePatients(Patients p) {
		em.createQuery("UPDATE Patients set name = ?1, address = ?2, age = ?3 " +
		"WHERE patientCode = ?4")
		.setParameter(1, p.getName())
		.setParameter(2, p.getAddress())
		.setParameter(3, p.getAge())
		.setParameter(4, p.getPatientCode())
		.executeUpdate();
	}

	@Override
	public void deletePatients(Patients p) {
		em.remove(p);
	}
}
