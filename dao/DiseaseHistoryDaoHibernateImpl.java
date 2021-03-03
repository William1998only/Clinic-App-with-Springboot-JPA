package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseaseHistories;
import com.lawencon.klinik.model.Diseases;
import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.InvoicesHdr;
import com.lawencon.klinik.model.Patients;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="diseasehistoryhibernate")
public class DiseaseHistoryDaoHibernateImpl extends BaseDao implements DiseaseHistoryDao {

	@Override
	public void insertDiseaseHistory(DiseaseHistories data) throws SQLException {
			em.persist(data);
	}

	@Override
	public List<DiseaseHistories> getDiseaseHistory() throws SQLException {
		List<DiseaseHistories> listResult = em.createQuery("FROM DiseaseHistories", DiseaseHistories.class).getResultList();
		return listResult;
	}

	@Override
	public List<DiseaseHistories> getDiseaseHistoryByPatientCode(String inputKodePasien) throws SQLException {
		List<DiseaseHistories> listResult = new ArrayList<>();
			List<Object[]> listObj = em
					.createQuery(bBuilder("SELECT p.name, d.diseaseName, i.checkUpDate, ",
							"dh.idDtlDisease.id, dd.idInvoice.id ", "FROM DiseaseHistories dh ",
							"INNER JOIN dh.idDtlDisease as dd ",
							"INNER JOIN dd.idInvoice as i ",
							"INNER JOIN i.idPatient as p ",
							"INNER JOIN dd.idDisease as d ", "where p.patientCode = ?1").toString(), //sama dengan INNER JOIN universitas as u on u.id = m.universitas_id
							Object[].class).setParameter(1, inputKodePasien)
					.getResultList();
			listObj.forEach(objArr -> {
				DiseaseHistories diseaseHistory = new DiseaseHistories();
				DiseasesDtl diseaseDtl = new DiseasesDtl();
				diseaseDtl.setId((Long) objArr[3]);
				Diseases disease = new Diseases();
				disease.setDiseaseName((String) objArr[1]);
				diseaseDtl.setIdDisease(disease);
				InvoicesHdr invoice = new InvoicesHdr();
				invoice.setId((Long) objArr[4]);
				invoice.setCheckUpDate((LocalDate) objArr[2]);
				Patients patient = new Patients();
				patient.setName((String) objArr[0]);
				invoice.setIdPatient(patient);
				diseaseDtl.setIdInvoice(invoice);
				diseaseHistory.setIdDtlDisease(diseaseDtl);
				listResult.add(diseaseHistory);
		});
		return listResult;
	}

}
