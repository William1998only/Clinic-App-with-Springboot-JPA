package com.lawencon.klinik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.klinik.model.DiseasesDtl;
import com.lawencon.klinik.service.DiseasesDtlService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/dtldisease")
public class DiseaseDtlController {
	
	@Autowired
	private DiseasesDtlService diseasesDtlService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getDiseasesDtl(@PathVariable("id") String id){
		DiseasesDtl diseaseDtl = new DiseasesDtl();
		diseaseDtl = diseasesDtlService.getDiseasesDtlById(Long.valueOf(id));
		return new ResponseEntity<>(diseaseDtl, HttpStatus.OK);
	}
	
	@GetMapping("/code/{code}")
	public ResponseEntity<?> getDiseasesDtlByInvoiceCode(@PathVariable("code") String code){
		List<DiseasesDtl> listDiseasedtl = new ArrayList<>();
		listDiseasedtl = diseasesDtlService.getDiseasesDtlByInvoiceCode(code);
		return new ResponseEntity<>(listDiseasedtl, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> insertDiseasesDtl(@RequestBody String body) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		List<DiseasesDtl> diseaseDtlList = mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, DiseasesDtl.class));
		try {
			diseasesDtlService.insertDiseasesDtl(diseaseDtlList);
			return new ResponseEntity<>("Input berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
