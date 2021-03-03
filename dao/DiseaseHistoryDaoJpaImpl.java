package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseaseHistories;
import com.lawencon.klinik.model.Diseases;
import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.InvoicesHdr;
import com.lawencon.klinik.model.Patients;
import com.lawencon.klinik.repo.DiseaseHistoryRepo;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository(value="diseasehistoryjpa")
public class DiseaseHistoryDaoJpaImpl extends BaseDao implements DiseaseHistoryDao {

	@Autowired
	private DiseaseHistoryRepo diseaseHistoryRepo;
	
	@Override
	public void insertDiseaseHistory(DiseaseHistories data) throws SQLException {
			diseaseHistoryRepo.save(data);
	}

	@Override
	public List<DiseaseHistories> getDiseaseHistory() throws SQLException {
		return diseaseHistoryRepo.findAll();
	}

	@Override
	public List<DiseaseHistories> getDiseaseHistoryByPatientCode(String inputKodePasien) throws SQLException {
		List<DiseaseHistories> listResult = new ArrayList<>();
			List<Object[]> listObj = diseaseHistoryRepo.findByPatientCode(inputKodePasien);
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
