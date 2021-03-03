package com.lawencon.klinik.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.DiseaseHistories;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DiseaseHistoryService {
	void insertDiseasesHistory(Long id_dtl_disease) throws Exception;

	List<DiseaseHistories> getDiseaseHistory() throws SQLException;

	List<DiseaseHistories> getDiseaseHistoryByPatientCode(String inputKodePasien) throws SQLException;

	DiseaseHistories getAllMeds(Long idDtlPenyakit);
}
