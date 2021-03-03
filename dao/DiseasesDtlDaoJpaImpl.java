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
import com.lawencon.klinik.repo.DiseaseDtlRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value = "diseasesdtljpa")
public class DiseasesDtlDaoJpaImpl extends BaseDao implements DiseasesDtlDao {

	@Autowired
	DiseaseDtlRepo diseaseDtlRepo;

	@Override
	public Long insertDiseaseDtl(DiseasesDtl diseasesDtl) throws SQLException {
		diseaseDtlRepo.save(diseasesDtl);
		return diseasesDtl.getId();
	}

	@Override
	public DiseasesDtl getDiseasesDtlById(Long id) {
		return diseaseDtlRepo.findById(id).get();
	}

	@Override
	public List<DiseasesDtl> getDiseasesDtlByInvoiceCode(String code) {
		List<DiseasesDtl> listResult = new ArrayList<>();
		List<Object[]> listObj = diseaseDtlRepo.findByInvoiceCode(code);
		listObj.forEach(objArr -> {
			DiseasesDtl diseaseDtl = new DiseasesDtl();
			diseaseDtl.setId((Long) objArr[3]);
			Diseases disease = new Diseases();
			disease.setDiseaseName((String) objArr[1]);
			diseaseDtl.setIdDisease(disease);
			InvoicesHdr invoice = new InvoicesHdr();
			invoice.setId((Long) objArr[4]);
			invoice.setCheckUpDate((LocalDate) objArr[2]);
			invoice.setInvoiceCode((String) objArr[5]);
			Patients patient = new Patients();
			patient.setName((String) objArr[0]);
			invoice.setIdPatient(patient);
			diseaseDtl.setIdInvoice(invoice);
			listResult.add(diseaseDtl);
//			diseaseHistory.setIdDtlDisease(diseaseDtl);
//			listResult.add(diseaseHistory);
		});
		return listResult;
	}
}
