package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.PatientsDao;
import com.lawencon.klinik.model.Patients;

/**
 * 
 * @author WILLIAM
 *
 */
@Service
@Transactional
public class PatientsServiceImpl extends BaseService implements PatientsService {

	@Autowired
	@Qualifier(value="patientsjpa")
	private PatientsDao patientsDao;

	@Override
	public Patients getPatientsByCode(String kode) throws Exception {
		Patients data = patientsDao.getPatientsByCode(kode);
		if(null == data) {
			throw new Exception("Pasien tidak terdaftar");
		}else {
			return data;
		}
	}

	@Override
	public void insertPatients(Patients patient) throws Exception {
		if(null == patientsDao.getPatientsByCode(patient.getPatientCode()) &&
				null == patient.getId()) {
			patientsDao.insertPatients(patient);
		}else {
			throw new Exception("Pasien sudah terdaftar");
		}
	}

	@Override
	public List<Patients> getPatients() throws Exception {
		return patientsDao.getPatients();
	}

	@Override
	public void updatePatients(Patients data) throws Exception{
		if(!data.getAddress().isEmpty() && !data.getName().isEmpty()
				&& !data.getPatientCode().isEmpty()) {
			if(null != patientsDao.getPatientsByCode(data.getPatientCode())) {
				patientsDao.updatePatients(data);
			}else {
				throw new Exception("Pasien tidak terdaftar");
			}
		}else 
			throw new Exception("data tidak boleh kosong");
	}

	@Override
	public void deletePatients(Patients data) throws Exception{
		if(null != patientsDao.getPatientsByCode(data.getPatientCode())) {
			patientsDao.deletePatients(data);
		}else {
			throw new Exception("Data tidak terdaftar");
		}
	}
}
