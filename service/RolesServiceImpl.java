package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.RolesDao;
import com.lawencon.klinik.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class RolesServiceImpl extends BaseService implements RolesService {

	@Autowired
	@Qualifier(value="rolesjpa")
	private RolesDao rolesDao;

	@Override
	public List<Roles> getRole() throws Exception {
		return rolesDao.getRole();
	}

	@Override
	public Roles getRoleById(Long id) throws Exception {
		return rolesDao.getRoleById(id);
	}

	@Override
	public Roles getRolesByCode(int kode) {
		return rolesDao.getRolesByCode(kode);
	}

	@Override
	public void insertRole(Roles role) {
		rolesDao.insertRole(role);
	}

	@Override
	public void updateRoles(Roles role) {
		Roles data = rolesDao.getRolesByCode(role.getRoleCode());
		rolesDao.updateRoles(data);
	}

	@Override
	public void deleteRole(int kode) {
		Roles data = rolesDao.getRolesByCode(kode);
		rolesDao.deleteRole(data);
	}
}
