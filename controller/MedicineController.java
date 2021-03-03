package com.lawencon.klinik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.klinik.model.Medicines;
import com.lawencon.klinik.service.MedicinesService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicinesService medicinesService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllMedicine(){
		try {
			List<Medicines> medList = medicinesService.getMedicines();
			if(medList.size() == 0) {
				return new ResponseEntity<>("Data belum ada", HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(medList, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getMedicineByCode(@PathVariable("kode") String kode){
		try {
			Medicines medicine = medicinesService.getMedicinesByCode(kode);
			return new ResponseEntity<>(medicine, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> insertMedicine(@RequestBody String body){
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			Medicines med = obj.readValue(body, Medicines.class);
			medicinesService.insertMedicines(med);
			return new ResponseEntity<>("Input berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateMedicine(@RequestBody String body) {
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			Medicines med = obj.readValue(body, Medicines.class);
			medicinesService.updateMedicines(med);
			return new ResponseEntity<>(med, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteMedicine(@PathVariable("kode") String kode) {
		try {
			Medicines med = medicinesService.getMedicinesByCode(kode);
			medicinesService.deleteMedicines(med);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		}catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			System.out.println("osjajsoajsajo");
			return new ResponseEntity<>("Data masih terpakai di tabel lain", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
