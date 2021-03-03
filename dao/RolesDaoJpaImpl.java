package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Roles;
import com.lawencon.klinik.repo.RoleRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="rolesjpa")
public class RolesDaoJpaImpl extends BaseDao implements RolesDao {
	
	@Autowired
	RoleRepo roleRepo;

	@Override
	public List<Roles> getRole() throws Exception {
		return roleRepo.findAll();
	}

	@Override
	public Roles getRoleById(Long id) throws Exception {
		return roleRepo.findById(id).get();
	}

	@Override
	public Roles getRolesByCode(int kode) {
		return roleRepo.findByRoleCode(kode);
	}

	@Override
	public void insertRole(Roles role) {
		roleRepo.save(role);
	}

	@Override
	public void updateRoles(Roles role) {
		roleRepo.save(role);
	}

	@Override
	public void deleteRole(Roles data) {
		roleRepo.deleteById(data.getId());
	}

}
