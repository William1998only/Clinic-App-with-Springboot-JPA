package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.MedicineTypeDao;
import com.lawencon.klinik.model.MedTypes;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class MedicineTypeServiceImpl extends BaseService implements MedicineTypeService {

	@Autowired
	@Qualifier(value="medicinetypejpa")
	MedicineTypeDao medicineTypeDao;

	@Override
	public void insertMedType(MedTypes medType) throws Exception {
		medicineTypeDao.insertMedType(medType);
	}

	@Override
	public MedTypes getMedTypeByCode(int medType) throws Exception{
		MedTypes type = medicineTypeDao.getMedTypeByCode(medType);
		if(null == type) {
			throw new Exception("Tipe obat tidak terdaftar");
		}else {
			return type;
		}
	}

	@Override
	public List<MedTypes> getAllMedTypes() throws Exception{
		return medicineTypeDao.getAllMedTypes();
	}

	@Override
	public void updateMedType(MedTypes data) throws Exception {
		if(null != data.getMedCodeType() && !data.getMedType().isEmpty()) {
			if(null != medicineTypeDao.getMedTypeByCode(data.getMedCodeType())){
				medicineTypeDao.updateMedType(data);
			}else
				throw new Exception("Data tidak terdaftar");
				
		}else
			throw new Exception("Input tidak boleh kosong");
	}

	@Override
	public void deleteMedType(MedTypes medType) throws Exception {
		medicineTypeDao.deleteMedType(medType);
	}
}
