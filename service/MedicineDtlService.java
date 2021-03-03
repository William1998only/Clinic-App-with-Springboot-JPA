package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.MedicinesDtl;

/**
 * 
 * @author WILLIAM
 *
 */
public interface MedicineDtlService {
	
	void insertMedDtl(Long id, DiseasesDtl ddl) throws Exception;
	
	List<MedicinesDtl> getAllMedDtl();
	
	List<MedicinesDtl> getByIdDtlDisease(Long idDtlPenyakit);
}
