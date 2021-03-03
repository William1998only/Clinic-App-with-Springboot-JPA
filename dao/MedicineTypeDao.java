package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.MedTypes;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicineTypeDao {

	void insertMedType(MedTypes data) throws SQLException;

	List<MedTypes> getAllMedTypes() throws Exception;

	MedTypes getMedTypeByCode(int medType);

	void updateMedType(MedTypes data) throws Exception;
	
	void deleteMedType(MedTypes data) throws Exception;

}
