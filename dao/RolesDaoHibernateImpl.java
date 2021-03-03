package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Roles;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="roleshibernate")
public class RolesDaoHibernateImpl extends BaseDao implements RolesDao {

	@Override
	public List<Roles> getRole() throws Exception {
		List<Roles> listResult = em.createQuery("FROM Roles", Roles.class).getResultList();
		return listResult;
	}

	@Override
	public Roles getRoleById(Long id) throws Exception {
		List<Roles> listResult = em.createQuery("FROM Roles WHERE id = ?1 ", Roles.class).setParameter(1, id)
				.getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public Roles getRolesByCode(int kode) {
		List<Roles> listResult = em.createQuery("FROM Roles WHERE roleCode = ?1 ", Roles.class).setParameter(1, kode)
				.getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void insertRole(Roles role) {
		em.persist(role);
	}

	@Override
	public void updateRoles(Roles role) {
		em.createQuery("UPDATE Roles set roleName = ?1 WHERE roleCode = ?2")
		.setParameter(1, role.getRoleName())
		.setParameter(2, role.getRoleCode())
		.executeUpdate();
	}

	@Override
	public void deleteRole(Roles data) {
		em.remove(data);
	}
}
