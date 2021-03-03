package com.lawencon.klinik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Schedules;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface ScheduleRepo extends JpaRepository<Schedules, Long>{
	
	@Query(value = "SELECT s.id, s.idDoctor.id , d.doctorCode, s.practiceDay, " +
			"s.practiceBeginTime, s.practiceEndTime, pu.fullName, d.idUser.id, u.idProfile.id FROM Schedules s " +
			"INNER JOIN s.idDoctor as d " + "INNER JOIN d.idUser as u " +
			"INNER JOIN u.idProfile as pu " +
			"WHERE s.practiceDay = ?1")
	List<Object[]> findByPracticeDay(String inputHari);
	
	@Query(value = " FROM Schedules WHERE id = ?1")
	Schedules getById(Long id);
	
}
