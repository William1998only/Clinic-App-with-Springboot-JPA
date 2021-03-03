package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.MedTypes;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="medicinetypehibernate")
public class MedicineTypeDaoHibernateImpl extends BaseDao implements MedicineTypeDao {
	@Override
	public void insertMedType(MedTypes data) throws SQLException {
			em.persist(data); 
	}
	
	@Override
	public List<MedTypes> getAllMedTypes() throws Exception {
		List<MedTypes> listResult = em.createQuery("FROM MedTypes", MedTypes.class).getResultList();
		return listResult;
	}

	@Override
	public MedTypes getMedTypeByCode(int medType) {
		List<MedTypes> listResult = em.createQuery("FROM MedTypes WHERE medCodeType = ?1 ", MedTypes.class).setParameter(1, medType)
					.getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void updateMedType(MedTypes data) throws Exception {
		em.createQuery("UPDATE MedTypes set medType = ?1 " +
				"WHERE medCodeType = ?2")
				.setParameter(1, data.getMedType())
				.setParameter(2, data.getMedCodeType())
				.executeUpdate();
	}

	@Override
	public void deleteMedType(MedTypes data) throws Exception {
		em.remove(data);
	}

}
