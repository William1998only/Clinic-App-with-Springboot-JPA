package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.Schedules;

/**
 * 
 * @author WILLIAM
 *
 */
public interface SchedulesDao {

	void insertSchedule(Schedules data, String inputKode) throws SQLException;

	List<Schedules> getSchedule() throws SQLException;

	List<Schedules> getScheduleByDay(String inputHari) throws SQLException;
	
	Schedules getScheduleById(Long id) throws Exception;

	void updateSchedules(Schedules data);

	void deleteSchedules(Schedules data);

}
