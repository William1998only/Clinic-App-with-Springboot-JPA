package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.DiseasesDtlDao;
import com.lawencon.klinik.model.Diseases;
import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.Doctors;
import com.lawencon.klinik.model.InvoicesHdr;

/**
 * 
 * @author WILLIAM
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DiseasesDtlServiceImpl extends BaseService implements DiseasesDtlService {

	@Autowired
	@Qualifier(value="diseasesdtljpa")
	private DiseasesDtlDao diseasesDtlDao;
	@Autowired
	private MedicineDtlService medicineDtlService;
	@Autowired
	private DiseaseHistoryService diseaseHistoryService;
	@Autowired
	private InvoicesHdrService invoicesHdrService;
	@Autowired
	private DiseasesService diseasesService;
	@Autowired
	private DoctorsService doctorsService;

	@Override
	public void insertDiseasesDtl(List<DiseasesDtl> listDiseaseDtl) throws Exception {
		for (int i = 0; i < listDiseaseDtl.size(); i++) {
			InvoicesHdr invoiceHdr = new InvoicesHdr();
			Diseases diseases = new Diseases();
			Long idDtlDisease = null;
			Long idDoctor = listDiseaseDtl.get(i).getIdDoctor().getId();
			Doctors doctor = new Doctors();
			doctor = doctorsService.getDoctorById(idDoctor);
			invoicesHdrService.getInvoiceHdrByCode(listDiseaseDtl.get(i).getIdInvoice().getInvoiceCode());
			diseasesService.getDiseasesByCode(listDiseaseDtl.get(i).getIdDisease().getDiseaseCode());
			invoiceHdr = invoicesHdrService.getInvoiceHdrByCode(listDiseaseDtl.get(i).getIdInvoice().getInvoiceCode());
			listDiseaseDtl.get(i).setIdInvoice(invoiceHdr);
			diseases = diseasesService.getDiseasesByCode(listDiseaseDtl.get(i).getIdDisease().getDiseaseCode());
			listDiseaseDtl.get(i).setIdDisease(diseases);
			listDiseaseDtl.get(i).setIdDoctor(doctor);
			System.out.println("kosakoskaoaso");
			idDtlDisease = diseasesDtlDao.insertDiseaseDtl(listDiseaseDtl.get(i));
			try {
				medicineDtlService.insertMedDtl(idDtlDisease, listDiseaseDtl.get(i));
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			invoicesHdrService.updateTotalCost(listDiseaseDtl.get(i).getIdInvoice().getInvoiceCode());
			diseaseHistoryService.insertDiseasesHistory(idDtlDisease);
		}
	}

	@Override
	public DiseasesDtl getDiseasesDtlById(Long id) {
		return diseasesDtlDao.getDiseasesDtlById(id);
	}

	@Override
	public List<DiseasesDtl> getDiseasesDtlByInvoiceCode(String code) {
		return diseasesDtlDao.getDiseasesDtlByInvoiceCode(code); 
	}

}
