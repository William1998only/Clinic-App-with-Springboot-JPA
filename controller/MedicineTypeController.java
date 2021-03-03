package com.lawencon.klinik.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.klinik.model.MedTypes;
import com.lawencon.klinik.service.MedicineTypeService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/medtype")
public class MedicineTypeController {
	
	@Autowired
	private MedicineTypeService medicineTypeService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getMedTypes() throws Exception{
		List<MedTypes> medTypeList = new ArrayList<>();
		medTypeList = medicineTypeService.getAllMedTypes();
		return new ResponseEntity<>(medTypeList, HttpStatus.OK);
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getMedTypesByCode(@PathVariable("kode") int kode) throws Exception{
		try {
			MedTypes medType = medicineTypeService.getMedTypeByCode(kode);
			return new ResponseEntity<>(medType, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertMedType(@RequestBody String body){
		try {
			MedTypes medType = new ObjectMapper().readValue(body, MedTypes.class);
			medicineTypeService.insertMedType(medType);
			return new ResponseEntity<>("Input berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Input gagal", HttpStatus.OK);
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> updateMedType(@RequestBody String body) {
		try {
			ObjectMapper obj = new ObjectMapper();
			MedTypes type = obj.readValue(body, MedTypes.class);
			medicineTypeService.updateMedType(type);
			return new ResponseEntity<>(type, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteMedType(@PathVariable("kode") int kode) {
		try {
			MedTypes medType = medicineTypeService.getMedTypeByCode(kode);
			medicineTypeService.deleteMedType(medType);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
}
