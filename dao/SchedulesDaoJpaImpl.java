package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Doctors;
import com.lawencon.klinik.model.ProfileUsers;
import com.lawencon.klinik.model.Schedules;
import com.lawencon.klinik.model.Users;
import com.lawencon.klinik.repo.ScheduleRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="schedulesjpa")
public class SchedulesDaoJpaImpl extends BaseDao implements SchedulesDao {

	@Autowired 
	ScheduleRepo scheduleRepo;
	
	@Override
	public void insertSchedule(Schedules schedule, String inputKode) throws SQLException {
		scheduleRepo.save(schedule);
	}

	@Override
	public List<Schedules> getSchedule() throws SQLException {
		return scheduleRepo.findAll();
	}

	@Override
	public List<Schedules> getScheduleByDay(String inputHari) throws SQLException {
		List<Schedules> listResult = new ArrayList<>();
		List<Object[]> listObj = scheduleRepo.findByPracticeDay(inputHari);
		listObj.forEach(objArr -> {
			Schedules schedule = new Schedules();
			Doctors doctor = new Doctors();
			doctor.setId((Long) objArr[1]);
			doctor.setDoctorCode((String) objArr[2]);
			Users user = new Users();
			user.setId((Long) objArr[7]);
			doctor.setIdUser(user);
			ProfileUsers profile = new ProfileUsers();
			profile.setId((Long) objArr[8]);
			profile.setFullName((String) objArr[6]);
			user.setIdProfile(profile);
			schedule.setIdDoctor(doctor);
			schedule.setPracticeDay((String) objArr[3]);
			schedule.setPracticeBeginTime((LocalTime) objArr[4]);
			schedule.setPracticeEndTime((LocalTime) objArr[5]);
			listResult.add(schedule);
		});
		return listResult;
	}

	@Override
	public Schedules getScheduleById(Long id) throws Exception {
		return scheduleRepo.getById(id);
	}

	@Override
	public void updateSchedules(Schedules data) {
		scheduleRepo.save(data);
	}

	@Override
	public void deleteSchedules(Schedules data) {
		scheduleRepo.deleteById(data.getId());
	}

}
