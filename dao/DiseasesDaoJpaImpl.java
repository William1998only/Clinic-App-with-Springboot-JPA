package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Diseases;
import com.lawencon.klinik.repo.DiseaseRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="diseasesjpa")
public class DiseasesDaoJpaImpl extends BaseDao implements DiseasesDao {

	@Autowired
	private DiseaseRepo diseaseRepo;
	
	@Override
	public void insertDiseases(Diseases disease) throws SQLException {
			diseaseRepo.save(disease);
	}

	@Override
	public List<Diseases> getDiseases() throws Exception {
		return diseaseRepo.findAll();
	}

	@Override
	public Diseases getDiseasesByCode(String kode) throws SQLException {
		return diseaseRepo.findByDiseaseCode(kode);
	}

	@Override
	public void updateDiseases(Diseases disease) throws Exception {
		diseaseRepo.save(disease);
	}

	@Override
	public void deleteDiseases(Diseases disease) throws Exception {
		diseaseRepo.deleteById(disease.getId());
	}
}
