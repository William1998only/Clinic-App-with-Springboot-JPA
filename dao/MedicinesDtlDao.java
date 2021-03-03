package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.MedicinesDtl;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicinesDtlDao {
	void insertMedicinesDtl(MedicinesDtl meds) throws SQLException;
	
	List<MedicinesDtl> getAllMedDtl();

	List<MedicinesDtl> getByIdDtlDisease(Long idDtlPenyakit);
}
