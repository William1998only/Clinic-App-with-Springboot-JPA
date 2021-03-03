package com.lawencon.klinik.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.model.Medicines;
import com.lawencon.klinik.model.MedicinesDtl;
import com.lawencon.klinik.repo.MedDtlRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="medicinesdtljpa")
public class MedicinesDtlDaoJpaImpl extends BaseDao implements MedicinesDtlDao{
	
	@Autowired
	private MedDtlRepo medDtlRepo;
	
	@Override
	public void insertMedicinesDtl(MedicinesDtl meds) throws SQLException {
			medDtlRepo.save(meds);
	}

	@Override
	public List<MedicinesDtl> getAllMedDtl() {
		return medDtlRepo.findAll();
	}

	@Override
	public List<MedicinesDtl> getByIdDtlDisease(Long idDtlPenyakit) {
		List<MedicinesDtl> listMed = new ArrayList<>();
		DiseasesDtl diseaseDtl = new DiseasesDtl();
		diseaseDtl.setId(idDtlPenyakit);
		List<Object[]> listObj = medDtlRepo.findByIdDtlDisease(diseaseDtl);
		listObj.forEach(obj -> {
			Medicines med = new Medicines();
			med.setMedName((String) obj[0]);
			med.setPrice((BigDecimal) obj[1]);
			MedicinesDtl medDtl = new MedicinesDtl();
			medDtl.setIdMedicine(med);
			listMed.add(medDtl);
		});
		return listMed;
	}
}
