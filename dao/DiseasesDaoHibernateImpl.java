package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Diseases;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="diseaseshibernate")
public class DiseasesDaoHibernateImpl extends BaseDao implements DiseasesDao {
	
	@Override
	public void insertDiseases(Diseases disease) throws SQLException {
			em.persist(disease);
	}

	@Override
	public List<Diseases> getDiseases() throws Exception {
		List<Diseases> listResult = em.createQuery("FROM Diseases", Diseases.class).getResultList();
		return listResult;
	}

	@Override
	public Diseases getDiseasesByCode(String kode) throws SQLException {
		List<Diseases> listResult = em.createQuery("FROM Diseases WHERE diseaseCode = ?1 ", Diseases.class)
				.setParameter(1, kode).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void updateDiseases(Diseases disease) throws Exception {
		em.createQuery("UPDATE Diseases set diseaseName = ?1 WHERE diseaseCode = ?2")
		.setParameter(1, disease.getDiseaseName())
		.setParameter(2, disease.getDiseaseCode())
		.executeUpdate();
	}

	@Override
	public void deleteDiseases(Diseases disease) throws Exception {
		em.remove(disease);
	}
}
