package com.lawencon.klinik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.klinik.model.MedicinesDtl;
import com.lawencon.klinik.service.MedicineDtlService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/dtlmed")
public class MedicineDtlController {
	
	@Autowired
	private MedicineDtlService medicineDtlService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getMedicineDtl(){
		List<MedicinesDtl> dtlMeds = new ArrayList<>();
		dtlMeds = medicineDtlService.getAllMedDtl();
		return new ResponseEntity<>(dtlMeds, HttpStatus.OK);
	}
	
	@GetMapping("/iddtl/{id}")
	public ResponseEntity<?> getMedicinesDtlByIdDtl(@PathVariable("id") Long id){
		List<MedicinesDtl> listMedDtl = new ArrayList<>();
		listMedDtl = medicineDtlService.getByIdDtlDisease(id);
		return new ResponseEntity<>(listMedDtl, HttpStatus.OK);
	}

}
