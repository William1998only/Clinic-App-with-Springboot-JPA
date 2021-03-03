package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.MedTypes;
import com.lawencon.klinik.repo.MedicineTypeRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="medicinetypejpa")
public class MedicineTypeDaoJpaImpl extends BaseDao implements MedicineTypeDao {
	
	@Autowired
	private MedicineTypeRepo medicineTypeRepo;
	
	@Override
	public void insertMedType(MedTypes mt) throws SQLException {
			medicineTypeRepo.save(mt);
	}
	
	@Override
	public List<MedTypes> getAllMedTypes() throws Exception {
		return medicineTypeRepo.findAll();
	}

	@Override
	public MedTypes getMedTypeByCode(int medType) {
		return medicineTypeRepo.findByMedCodeType(medType);
	}

	@Override
	public void updateMedType(MedTypes data) throws Exception {
		medicineTypeRepo.save(data);
	}

	@Override
	public void deleteMedType(MedTypes data) throws Exception {
		medicineTypeRepo.deleteById(data.getId());
	}

}
