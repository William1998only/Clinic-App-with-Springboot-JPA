package com.lawencon.klinik.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.ProfileUsers;

/**
 * 
 * @author WILLIAM
 *
 */
@Repository
public interface ProfileUserRepo extends JpaRepository<ProfileUsers, Long> {
	
	ProfileUsers findByFullName(String fullname);
	
}
