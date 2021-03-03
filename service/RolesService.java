package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */
public interface RolesService {
	List<Roles> getRole() throws Exception;

	Roles getRoleById(Long id) throws Exception;

	Roles getRolesByCode(int kode);

	void insertRole(Roles role);

	void updateRoles(Roles role);

	void deleteRole(int kode);
}
