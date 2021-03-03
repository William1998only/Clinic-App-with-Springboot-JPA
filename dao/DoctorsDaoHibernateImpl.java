package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Doctors;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="doctorshibernate")
public class DoctorsDaoHibernateImpl extends BaseDao implements DoctorsDao {

	@Override
	public void insertDoctor(Doctors doctor) {
		em.persist(doctor);
	}

	@Override
	public List<Doctors> getDoctor() {
		List<Doctors> listResult = em.createQuery("FROM Doctors", Doctors.class).getResultList();
		return listResult;
	}

	@Override
	public Doctors getDoctorByCode(String inputKode) {
		List<Doctors> listResult = em.createQuery("FROM Doctors WHERE doctorCode = ?1 ", Doctors.class)
				.setParameter(1, inputKode).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public Doctors getDoctorById(Long id) {
		List<Doctors> listResult = em.createQuery("FROM Doctors WHERE id = ?1 ", Doctors.class)
				.setParameter(1, id).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void deleteDoctor(Doctors d) {
		em.remove(d);
	}

	@Override
	public Doctors getDoctorByIdUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
