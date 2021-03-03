package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.Medicines;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicinesService {
	void insertMedicines(Medicines medicine) throws Exception;

	List<Medicines> getMedicines() throws Exception;

	Medicines getMedicinesByCode(String inputKode) throws Exception;

	void updateMedicines(Medicines data) throws Exception;

	void deleteMedicines(Medicines data) throws Exception;
}
