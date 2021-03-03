package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.klinik.dao.MedicinesDtlDao;
import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.Medicines;
import com.lawencon.klinik.model.MedicinesDtl;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
public class MedicineDtlServiceImpl extends BaseService implements MedicineDtlService {

	@Autowired
	@Qualifier(value="medicinesdtljpa")
	MedicinesDtlDao medicinesDtlDao;
	
	@Autowired
	MedicinesService medicinesService;
	
	@Autowired
	DiseasesDtlService diseasesDtlService;

	@Override
	public void insertMedDtl(Long id, DiseasesDtl ddl) throws Exception {
		for (int i = 0; i < ddl.getMeds().size(); i++) {
			MedicinesDtl medDtl = new MedicinesDtl();
			Medicines med = new Medicines();
			DiseasesDtl diseaseDtl = new DiseasesDtl();
			diseaseDtl = diseasesDtlService.getDiseasesDtlById(id);
			medDtl.setIdDtlDisease(diseaseDtl);
			med = medicinesService.getMedicinesByCode(ddl.getMeds().get(i).getIdMedicine().getMedCode());
			medDtl.setIdMedicine(med);
			medicinesDtlDao.insertMedicinesDtl(medDtl);
		}
	}

	@Override
	public List<MedicinesDtl> getAllMedDtl() {
		return medicinesDtlDao.getAllMedDtl();
	}

	@Override
	public List<MedicinesDtl> getByIdDtlDisease(Long idDtlPenyakit) {
		return medicinesDtlDao.getByIdDtlDisease(idDtlPenyakit);
	}

}
