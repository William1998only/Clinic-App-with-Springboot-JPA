package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Medicines;
import com.lawencon.klinik.model.MedicinesDtl;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="medicinesdtlhibernate")
public class MedicinesDtlDaoHibernateImpl extends BaseDao implements MedicinesDtlDao{
	@Override
	public void insertMedicinesDtl(MedicinesDtl meds) throws SQLException {
			em.persist(meds);
	}

	@Override
	public List<MedicinesDtl> getAllMedDtl() {
		List<MedicinesDtl> listResult = em.createQuery("FROM MedicinesDtl", MedicinesDtl.class).getResultList();
		return listResult;
	}

	@Override
	public List<MedicinesDtl> getByIdDtlDisease(Long idDtlPenyakit) {
		List<MedicinesDtl> listMed = new ArrayList<>();
		List<?> listObj = em
				.createQuery(bBuilder("SELECT m.medName from MedicinesDtl as dm "
						+ "INNER JOIN dm.idDtlDisease as dd INNER JOIN dd.idInvoice as hi "
						+ "INNER JOIN hi.idPatient as p INNER JOIN dm.idMedicine m "
						+ "WHERE dm.idDtlDisease.id = ?1").toString())
				.setParameter(1, idDtlPenyakit)
				.getResultList();
		for(int i = 0; i < listObj.size(); i++) {
			Medicines medicine = new Medicines();
			medicine.setMedName(String.valueOf(listObj.get(i)));
			MedicinesDtl medDtl = new MedicinesDtl();
			medDtl.setIdMedicine(medicine);
			listMed.add(medDtl);
		}
	return listMed;
	}
}
