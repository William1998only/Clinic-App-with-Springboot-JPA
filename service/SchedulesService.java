package com.lawencon.klinik.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.Schedules;

/**
 * 
 * @author WILLIAM
 *
 */
public interface SchedulesService {

	void insertSchedule(Schedules data, String inputKodeDokter) throws Exception;

	List<Schedules> getSchedule() throws SQLException;

	List<Schedules> getScheduleByDay(String inputHari) throws SQLException;

	void updateSchedules(Schedules data);

	void deleteSchedules(Long id) throws Exception;

}
