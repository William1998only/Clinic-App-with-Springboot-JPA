package com.lawencon.klinik.controller;

import java.sql.SQLException;
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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.klinik.model.Schedules;
import com.lawencon.klinik.service.SchedulesService;

/**
 * 
 * @author WILLIAM
 *
 */
@RestController
@RequestMapping("/schedule")
public class SchedulesController {

	@Autowired
	private SchedulesService schedulesService;

	@GetMapping("/getall")
	public ResponseEntity<?> getSchedules() {
		try {
			List<Schedules> schedules = schedulesService.getSchedule();
			if (schedules.size() == 0) {
				return new ResponseEntity<>("Data belum ada", HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(schedules, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/day/{day}")
	public ResponseEntity<?> getSchedules(@PathVariable("day") String day) {
		try {
			List<Schedules> schedules = schedulesService.getScheduleByDay(day);
			if (schedules.size() == 0) {
				return new ResponseEntity<>("Data belum ada", HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(schedules, HttpStatus.OK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> insertSchedule(@RequestBody String body) {
		ObjectMapper obj = new ObjectMapper();
		obj.registerModule(new JavaTimeModule());
		try {
			Schedules schedule = obj.readValue(body, Schedules.class);
			schedulesService.insertSchedule(schedule, schedule.getIdDoctor().getDoctorCode());
			return new ResponseEntity<>("Input berhasil", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Input gagal", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> updateDisease(@RequestBody String body) {
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			Schedules data = obj.readValue(body, Schedules.class);
			schedulesService.updateSchedules(data);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDisease(@PathVariable("id") Long id) {
		try {
			schedulesService.deleteSchedules(id);
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
