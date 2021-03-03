package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.Diseases;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DiseasesService {
	void insertDiseases(Diseases disease) throws Exception;

	List<Diseases> getDiseases() throws Exception;

	Diseases getDiseasesByCode(String inputKode) throws Exception;
	
	void updateDiseases(Diseases disease) throws Exception;
	
	void deleteDiseases(Diseases disease) throws Exception;

}
