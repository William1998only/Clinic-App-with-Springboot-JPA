package com.lawencon.klinik.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Medicines;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="medicineshibernate")
public class MedicinesDaoHibernateImpl extends BaseDao implements MedicinesDao {
	@Override
	public void insertMedicines(Medicines medicine) throws Exception {
		em.persist(medicine);
	}

	@Override
	public List<Medicines> getMedicines() throws Exception {
		List<Medicines> listResult =  em.createQuery("FROM Medicines", Medicines.class).getResultList();
		return listResult;
	}

	@Override
	public Medicines findMedicineByCode(String medCode) {
		List<Medicines> listResult = em.createQuery("FROM Medicines WHERE medCode = ?1 ", Medicines.class).setParameter(1, medCode)
					.getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void updateMedicines(Medicines data) {
		em.createQuery("UPDATE Medicines SET expireDate = ?1" + 
		", idMedType = ?2, medName = ?3, Price = ?4 " +
		"WHERE medCode = ?5")
		.setParameter(1, data.getExpireDate())
		.setParameter(2, data.getIdMedType())
		.setParameter(3, data.getMedName())
		.setParameter(4, data.getPrice())
		.setParameter(5,  data.getMedCode())
		.executeUpdate();
	}

	@Override
	public void deleteMedicines(Medicines m) {
		em.remove(m);
	}

}
