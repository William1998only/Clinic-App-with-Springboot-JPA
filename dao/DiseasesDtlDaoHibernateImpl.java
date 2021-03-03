package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseasesDtl;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="diseasesdtlhibernate")
public class DiseasesDtlDaoHibernateImpl extends BaseDao implements DiseasesDtlDao {
	
	@Override
	public Long insertDiseaseDtl(DiseasesDtl diseasesDtl) throws SQLException {
			em.persist(diseasesDtl);
		return diseasesDtl.getId();
	}

	@Override
	public DiseasesDtl getDiseasesDtlById(Long id) {
		List<DiseasesDtl> listResult = em.createQuery("FROM DiseasesDtl WHERE id = ?1 ", DiseasesDtl.class).setParameter(1, id)
					.getResultList(); 
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public List<DiseasesDtl> getDiseasesDtlByInvoiceCode(String code) {
		List<DiseasesDtl> listResult = em.createQuery("FROM DiseasesDtl WHERE invoice.getInvoiceCode() = ?1 ", DiseasesDtl.class).setParameter(1, code)
				.getResultList(); 
		return listResult;
	}
}
