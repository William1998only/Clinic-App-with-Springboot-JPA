package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Doctors;
import com.lawencon.klinik.model.ProfileUsers;
import com.lawencon.klinik.model.Schedules;
import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="scheduleshibernate")
public class SchedulesDaoHibernateImpl extends BaseDao implements SchedulesDao {

	@Override
	public void insertSchedule(Schedules data, String inputKode) throws SQLException {
		em.persist(data);
	}

	@Override
	public List<Schedules> getSchedule() throws SQLException {
		List<Schedules> listResult = new ArrayList<>();
		List<Object[]> listObj = em.createQuery(
				bBuilder("SELECT s.id, d.id , d.doctorCode, s.practiceDay, ",
						"s.practiceBeginTime, s.practiceEndTime, pu.fullName, u.id, pu.id from Schedules s ",
						"INNER JOIN s.idDoctor as d ", "INNER JOIN d.idUser as u ", "INNER JOIN u.idProfile as pu")
								.toString(), // sama dengan INNER JOIN universitas as u on u.id = m.universitas_id
				Object[].class).getResultList(); // createNativeQuery utk menggunakan query pgsql
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
	public List<Schedules> getScheduleByDay(String inputHari) throws SQLException {
		List<Schedules> listResult = new ArrayList<>();
		List<Object[]> listObj = em.createQuery(bBuilder(
				"SELECT s.id, s.idDoctor.id , d.doctorCode, s.practiceDay, ",
				"s.practiceBeginTime, s.practiceEndTime, pu.fullName, d.idUser.id, u.idProfile.id from Schedules s ",
				"INNER JOIN s.idDoctor as d ", "INNER JOIN d.idUser as u ", "INNER JOIN u.idProfile as pu ",
				"WHERE s.practiceDay = ?1").toString(), Object[].class).setParameter(1, inputHari).getResultList();
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
		return em.createQuery(bBuilder(" FROM Schedules WHERE id = ?1").toString(), Schedules.class)
				.setParameter(1, id)
				.getSingleResult();
	}

	@Override
	public void updateSchedules(Schedules data) {
		em.createQuery(bBuilder("UPDATE FROM Schedules SET practiceBeginTime = ?1 ",
				", practiceEndTime = ?2, practiceDay = ?3 ",
				"WHERE id = ?4").toString())
				.setParameter(1, data.getPracticeBeginTime())
				.setParameter(2, data.getPracticeEndTime())
				.setParameter(3, data.getPracticeDay())
				.setParameter(4, data.getId())
				.executeUpdate();
	}

	@Override
	public void deleteSchedules(Schedules data) {
		em.remove(data);
	}

}
