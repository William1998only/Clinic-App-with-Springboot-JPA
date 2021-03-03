package com.lawencon.klinik.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Medicines;
import com.lawencon.klinik.repo.MedicineRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="medicinesjpa")
public class MedicinesDaoJpaImpl extends BaseDao implements MedicinesDao {
	
	@Autowired
	private MedicineRepo medicineRepo;
	
	@Override
	public void insertMedicines(Medicines medicine) throws Exception {
		medicineRepo.save(medicine);
	}

	@Override
	public List<Medicines> getMedicines() throws Exception {
		return medicineRepo.findAll();
	}

	@Override
	public Medicines findMedicineByCode(String medCode) {
		return medicineRepo.findByMedCode(medCode);
	}

	@Override
	public void updateMedicines(Medicines data) {
		medicineRepo.save(data);
	}

	@Override
	public void deleteMedicines(Medicines m) throws Exception{
		medicineRepo.deleteById(m.getId());
	}

}
