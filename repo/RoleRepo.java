package com.lawencon.klinik.repo;
/**
 * 
 * @author WILLIAM
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Roles;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Long>{

	Roles findByRoleCode(int kode);
	
}
