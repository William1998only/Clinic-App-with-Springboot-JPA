package com.lawencon.klinik.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.klinik.model.Doctors;
import com.lawencon.klinik.service.DoctorsService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorsService doctorsService;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAllDoctors(){
		List<Doctors> doctors = new ArrayList<>();
		doctors = doctorsService.getDoctor();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/kode/{inputKode}")
	public ResponseEntity<?> getDoctorByCode(@PathVariable("inputKode") String inputKode){
		try {
			Doctors doctor = new Doctors();
			doctor = doctorsService.getDoctorByCode(inputKode);
			return new ResponseEntity<>(doctor, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/iduser/{id}")
	public ResponseEntity<?> getDoctorByCode(@PathVariable("id") Long id){
		try {
			Doctors doctor = new Doctors();
			doctor = doctorsService.getDoctorByIdUser(id);
			return new ResponseEntity<>(doctor, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertDoctor(@RequestBody String body){
		try {
				Doctors doctor = new ObjectMapper().readValue(body, Doctors.class);
				doctorsService.insertDoctor(doctor.getIdUser().getUserName(), doctor.getLicenceNumber());
				return new ResponseEntity<>("Input berhasil", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Input gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<?> deleteDoctor(@PathVariable("kode") String kode) {
		try {
			Doctors doctor = doctorsService.getDoctorByCode(kode);
			doctorsService.deleteDoctor(doctor);;
			return new ResponseEntity<>("Data dihapus", HttpStatus.OK);
		}catch (PersistenceException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Data masih terpakai di tabel lain", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
