package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.DiseasesDtl;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DiseasesDtlService {
	void insertDiseasesDtl(List<DiseasesDtl> diseasesDtl) throws Exception;

	DiseasesDtl getDiseasesDtlById(Long id);

	List<DiseasesDtl> getDiseasesDtlByInvoiceCode(String code);
}
