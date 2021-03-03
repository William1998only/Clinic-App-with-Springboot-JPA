package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.Diseases;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DiseasesDao {
	void insertDiseases(Diseases disease) throws SQLException;

	List<Diseases> getDiseases() throws Exception;

	Diseases getDiseasesByCode(String kode) throws SQLException;
	
	void updateDiseases(Diseases disease) throws Exception;

	void deleteDiseases(Diseases disease) throws Exception;
}
