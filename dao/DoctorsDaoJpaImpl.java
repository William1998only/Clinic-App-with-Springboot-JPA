package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Doctors;
import com.lawencon.klinik.repo.DoctorRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="doctorsjpa")
public class DoctorsDaoJpaImpl extends BaseDao implements DoctorsDao {

	@Autowired
	private DoctorRepo doctorRepo;
	
	@Override
	public void insertDoctor(Doctors doctor) {
		doctorRepo.save(doctor);
	}

	@Override
	public List<Doctors> getDoctor() {
		return doctorRepo.findAll();
	}

	@Override
	public Doctors getDoctorByCode(String inputKode) {
		return doctorRepo.findByDoctorCode(inputKode);
	}

	@Override
	public Doctors getDoctorById(Long id) {
		return doctorRepo.findById(id).get();
	}

	@Override
	public void deleteDoctor(Doctors data) {
		doctorRepo.deleteById(data.getId());;
	}

	@Override
	public Doctors getDoctorByIdUser(Long id) {
		return doctorRepo.findByIdUserId(id);
	}

}
