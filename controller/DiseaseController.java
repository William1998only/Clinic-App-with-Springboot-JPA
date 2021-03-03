package com.lawencon.klinik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.klinik.model.Diseases;
import com.lawencon.klinik.service.DiseasesService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/disease")
public class DiseaseController {
	
	@Autowired
	private DiseasesService diseasesService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getDisease() throws Exception{
		List<Diseases> listResult = new ArrayList<>(); 
		try {
			listResult = diseasesService.getDiseases();
			return new ResponseEntity<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getDiseaseByCode(@PathVariable("kode") String kode) {
		Diseases disease = new Diseases();
		try {
			disease = diseasesService.getDiseasesByCode(kode);
			return new ResponseEntity<>(disease, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertDisease(@RequestBody String body) {
		try {
			Diseases disease = new ObjectMapper().readValue(body, Diseases.class);
			diseasesService.insertDiseases(disease);
			return new ResponseEntity<>(disease, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@PatchMapping
	public ResponseEntity<?> updateDisease(@RequestBody String body) {
		try {
			Diseases disease = new ObjectMapper().readValue(body, Diseases.class);
			diseasesService.updateDiseases(disease);
			return new ResponseEntity<>(disease, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteDisease(@PathVariable("kode") String kode) {
		try {
			Diseases disease = diseasesService.getDiseasesByCode(kode);
			diseasesService.deleteDiseases(disease);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		}catch (PersistenceException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data masih terpakai di tabel lain", HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
