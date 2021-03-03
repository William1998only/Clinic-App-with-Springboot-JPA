package com.lawencon.klinik.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	Users findByUserName(String username);
	
	@Query(value = "SELECT u.id, u.userName, r.roleName " + "FROM Users as u "
			+ "INNER JOIN u.idRole as r " + "WHERE r.id = 2 or r.id = 3")
	List<Object[]> getUser();

	Users findByUserNameAndPassword(String username, String password);
}
