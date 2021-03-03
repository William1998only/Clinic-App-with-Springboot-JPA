package com.lawencon.klinik.dao;

import java.util.List;

import com.lawencon.klinik.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */
public interface RolesDao {
	List<Roles> getRole() throws Exception;
	
	Roles getRoleById(Long id) throws Exception;

	Roles getRolesByCode(int kode);

	void insertRole(Roles role);

	void updateRoles(Roles role);

	void deleteRole(Roles data);
}
