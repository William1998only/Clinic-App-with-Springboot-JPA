package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.DiseasesDao;
import com.lawencon.klinik.model.Diseases;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class DiseasesServiceImpl extends BaseService implements DiseasesService {

	@Autowired
	@Qualifier(value= "diseasesjpa")
	DiseasesDao diseasesDao;

	@Override
	public void insertDiseases(Diseases disease) throws Exception {
		if(disease.getDiseaseName().isEmpty() || disease.getDiseaseCode().isEmpty()){
			throw new Exception("input tidak boleh kosong");
		}else {
			if(null != disease.getId()) {
				throw new Exception("Tidak boleh memasukkan ID");
			}
			if(null == diseasesDao.getDiseasesByCode(disease.getDiseaseCode())) {
				diseasesDao.insertDiseases(disease);
			}else {
				throw new Exception("Data penyakit sudah ada");
			}
		}
	}

	@Override
	public List<Diseases> getDiseases() throws Exception {
		return diseasesDao.getDiseases();
	}

	@Override
	public Diseases getDiseasesByCode(String inputKode) throws Exception {
		Diseases data = diseasesDao.getDiseasesByCode(inputKode);
		if(null == data) {
			throw new Exception("data penyakit tidak ada");
		}else {
			return data;
		}
	}

	@Override
	public void updateDiseases(Diseases disease) throws Exception {
		if(!disease.getDiseaseCode().isEmpty() && !disease.getDiseaseName().isEmpty()) {
			if(null != diseasesDao.getDiseasesByCode(disease.getDiseaseCode())) {
				diseasesDao.updateDiseases(disease);
			}else 
				throw new Exception("Penyakit tidak terdaftar");
		}else {
			throw new Exception("Input tidak boleh kosong");
		}
	}

	@Override
	public void deleteDiseases(Diseases disease) throws Exception {
		if(null != diseasesDao.getDiseasesByCode(disease.getDiseaseCode())) {
			diseasesDao.deleteDiseases(disease);
		}else
			throw new Exception("Data tidak terdaftar");
	}
}
