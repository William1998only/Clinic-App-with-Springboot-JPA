package com.lawencon.klinik.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.klinik.model.DiseaseHistories;
import com.lawencon.klinik.service.DiseaseHistoryService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/diseasehistory")
public class DiseaseHistoryController {
	
	@Autowired
	private DiseaseHistoryService diseaseHistoryService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllDiseaseHistory() {
		try {
			List<DiseaseHistories> listResult = diseaseHistoryService.getDiseaseHistory();
			if(listResult.size() == 0) {
				return new ResponseEntity<>("Data belum ada", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(listResult, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/iddtlpenyakit/{iddtlpenyakit}")
	public ResponseEntity<?> getDiseaseHistoryById(@PathVariable("iddtlpenyakit") Long idDtlPenyakit) {
		try {
			DiseaseHistories diseaseHistory = diseaseHistoryService.getAllMeds(idDtlPenyakit);
			if(diseaseHistory.getMeds().size() == 0) {
				return new ResponseEntity<>("Data belum ada", HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(diseaseHistory, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
