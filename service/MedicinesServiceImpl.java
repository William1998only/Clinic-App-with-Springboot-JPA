package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.MedicinesDao;
import com.lawencon.klinik.model.Medicines;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class MedicinesServiceImpl extends BaseService implements MedicinesService {

	@Autowired
	@Qualifier(value="medicinesjpa")
	private MedicinesDao medicinesDao;

	@Autowired
	private MedicineTypeService medicineTypeService;

	@Override
	public void insertMedicines(Medicines medicine) throws Exception {
		if(null == medicinesDao.findMedicineByCode(medicine.getMedCode())) {
			if(null == medicine.getId()) {
				medicine.setIdMedType(medicineTypeService.getMedTypeByCode(medicine.getIdMedType().getMedCodeType()));
				medicinesDao.insertMedicines(medicine);
			}else {
				throw new Exception("Tidak boleh memasukkan ID");
			}
		}else {
			throw new Exception("Data obat sudah terdaftar");
		}
	}

	@Override
	public List<Medicines> getMedicines() throws Exception {
		List<Medicines> listResult = medicinesDao.getMedicines();
		return listResult;
	}

	@Override
	public Medicines getMedicinesByCode(String inputKode) throws Exception {
		Medicines med = medicinesDao.findMedicineByCode(inputKode);
		if(null == med) {
			throw new Exception("Obat tidak terdaftar");
		}else {
			return med;
		}
	}

	@Override
	public void updateMedicines(Medicines data) throws Exception{
		if(!data.getMedCode().isEmpty() && !data.getMedName().isEmpty() 
				&& null != data.getPrice() && null != data.getExpireDate()) {
			if(null != medicinesDao.findMedicineByCode(data.getMedCode())) {
				medicinesDao.updateMedicines(data);
			}else {
				throw new Exception("Obat tidak terdaftar");
			}
		}else {
			throw new Exception("data tidak boleh kosong");
		}
	}

	@Override
	public void deleteMedicines(Medicines m) throws Exception{
		medicinesDao.deleteMedicines(m);
	}

}
