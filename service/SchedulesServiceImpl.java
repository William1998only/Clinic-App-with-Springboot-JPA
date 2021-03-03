package com.lawencon.klinik.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.SchedulesDao;
import com.lawencon.klinik.model.Schedules;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class SchedulesServiceImpl extends BaseService implements SchedulesService {
	
	@Autowired
	@Qualifier(value="schedulesjpa")
	SchedulesDao schedulesDao;
	
	@Autowired
	DoctorsService doctorsService;

	@Override
	public void insertSchedule(Schedules data, String inputKode) throws Exception {
		data.setIdDoctor(doctorsService.getDoctorByCode(inputKode));
		schedulesDao.insertSchedule(data, inputKode);
	}

	@Override
	public List<Schedules> getSchedule() throws SQLException {
		return schedulesDao.getSchedule();
	}

	@Override
	public List<Schedules> getScheduleByDay(String inputHari) throws SQLException {
		return schedulesDao.getScheduleByDay(inputHari);
	}

	@Override
	public void updateSchedules(Schedules data) {
		schedulesDao.updateSchedules(data);
	}

	@Override
	public void deleteSchedules(Long id) throws Exception {
		Schedules data = schedulesDao.getScheduleById(id);
		schedulesDao.deleteSchedules(data);
	}

}
