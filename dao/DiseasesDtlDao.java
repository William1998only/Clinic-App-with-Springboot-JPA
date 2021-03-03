package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.DiseasesDtl;

/**
 * 
 * @author WILLIAM
 *
 */
public interface DiseasesDtlDao {

	Long insertDiseaseDtl(DiseasesDtl diseasesDtl) throws SQLException;

	DiseasesDtl getDiseasesDtlById(Long id);

	List<DiseasesDtl> getDiseasesDtlByInvoiceCode(String code);
}
