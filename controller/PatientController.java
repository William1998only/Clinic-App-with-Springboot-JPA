package com.lawencon.klinik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawencon.klinik.model.Patients;
import com.lawencon.klinik.service.PatientsService;

/**
 * 
 * @author WILLIAM
 *
 */

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientsService patientsService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllPatient(){
		try {
			List<Patients> patientList = patientsService.getPatients();
			return new ResponseEntity<>(patientList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.OK);
		}
	}
	
	@GetMapping("/kode/{kode}")
	public ResponseEntity<?> getPatientByCode(@PathVariable("kode") String kode){
		Patients patient = new Patients();
		try {
			patient = patientsService.getPatientsByCode(kode);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertPatient(@RequestBody String body){
		try {
			Patients patient = new ObjectMapper().readValue(body, Patients.class);
			patientsService.insertPatients(patient);
			return new ResponseEntity<>("Input Berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data sudah ada/ input kosong", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@PutMapping
	public ResponseEntity<?> updatePatient(@RequestBody String body) {
		try {
			Patients patient = new ObjectMapper().readValue(body, Patients.class);
			patientsService.updatePatients(patient);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Update gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deletePatient(@PathVariable("kode") String kode) {
		try {
			Patients patient = patientsService.getPatientsByCode(kode);
			patientsService.deletePatients(patient);
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Delete gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
