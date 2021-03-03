package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.MedTypes;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicineTypeService {

	void insertMedType(MedTypes medType) throws Exception;
	
	MedTypes getMedTypeByCode(int medType) throws Exception;
	
	List<MedTypes> getAllMedTypes() throws Exception;

	void updateMedType(MedTypes type) throws Exception;

	void deleteMedType(MedTypes medType) throws Exception;

}
