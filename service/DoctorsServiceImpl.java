package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.DoctorsDao;
import com.lawencon.klinik.model.Doctors;
import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class DoctorsServiceImpl extends BaseService implements DoctorsService {

	@Autowired
	@Qualifier(value = "doctorsjpa")
	private DoctorsDao doctorsDao;

	@Autowired
	private UsersService usersService;

	@Override
	public void insertDoctor(String inputUsername, int inputNoPraktek) throws Exception {
		Doctors doctor = new Doctors();
		Users user = usersService.getUserByName(inputUsername);
		doctor.setId(user.getId());
		doctor.setLicenceNumber(inputNoPraktek);
		doctor.setIdUser(user);
		doctor.setDoctorCode(user.getUserName());
		doctorsDao.insertDoctor(doctor);
	}

	@Override
	public List<Doctors> getDoctor() {
		return doctorsDao.getDoctor();
	}

	@Override
	public Doctors getDoctorByCode(String inputKode) throws Exception{
		Doctors data = doctorsDao.getDoctorByCode(inputKode);
		if(null == data) {
			throw new Exception("Dokter tidak terdaftar");
		}else {
			return data;
		}
	}

	@Override
	public Doctors getDoctorById(Long id) {
		return doctorsDao.getDoctorById(id);
	}

	@Override
	public void deleteDoctor(Doctors data) {
		doctorsDao.deleteDoctor(data);
	}

	@Override
	public Doctors getDoctorByIdUser(Long id) {
		return doctorsDao.getDoctorByIdUser(id);
	}

}
