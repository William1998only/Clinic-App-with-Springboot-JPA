package com.lawencon.klinik.dao;

import java.util.List;

import com.lawencon.klinik.model.Medicines;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicinesDao {
	void insertMedicines(Medicines medicines) throws Exception;

	List<Medicines> getMedicines() throws Exception;

	Medicines findMedicineByCode(String medCode);

	void updateMedicines(Medicines data);

	void deleteMedicines(Medicines data) throws Exception;
	
}
