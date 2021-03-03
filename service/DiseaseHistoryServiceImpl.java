package com.lawencon.klinik.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.DiseaseHistoryDao;
import com.lawencon.klinik.model.DiseaseHistories;
import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.MedicinesDtl;

/**
 * 
 * @author WILLIAM
 *
 */
@Service
@Transactional
public class DiseaseHistoryServiceImpl extends BaseService implements DiseaseHistoryService {

	@Autowired
	@Qualifier(value="diseasehistoryjpa")
	DiseaseHistoryDao diseaseHistoryDaoTemplate;
	
	@Autowired
	MedicineDtlService medicineDtlService;

	@Override
	public void insertDiseasesHistory(Long id_dtl_disease) throws Exception {
		DiseaseHistories diseaseHistory = new DiseaseHistories();
		DiseasesDtl diseaseDtl = new DiseasesDtl();
		diseaseDtl.setId(id_dtl_disease);
		diseaseHistory.setIdDtlDisease(diseaseDtl);
		diseaseHistoryDaoTemplate.insertDiseaseHistory(diseaseHistory);
	}

	@Override
	public List<DiseaseHistories> getDiseaseHistory() throws SQLException {
		return diseaseHistoryDaoTemplate.getDiseaseHistory();
	}

	@Override
	public List<DiseaseHistories> getDiseaseHistoryByPatientCode(String inputKodePasien) throws SQLException {
		return diseaseHistoryDaoTemplate.getDiseaseHistoryByPatientCode(inputKodePasien);
	}

	@Override
	public DiseaseHistories getAllMeds(Long idDtlPenyakit) {
		DiseaseHistories diseaseHistory = new DiseaseHistories();
		List<MedicinesDtl> med = medicineDtlService.getByIdDtlDisease(idDtlPenyakit);
		diseaseHistory.setMeds(med);
		return diseaseHistory; 
	}
}
