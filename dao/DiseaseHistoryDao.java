package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.DiseaseHistories;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DiseaseHistoryDao {

	List<DiseaseHistories> getDiseaseHistory() throws SQLException;

	List<DiseaseHistories> getDiseaseHistoryByPatientCode(String inputKodePasien) throws SQLException;

	void insertDiseaseHistory(DiseaseHistories data) throws SQLException;

}