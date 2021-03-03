package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.ProfileUsers;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="profileusershibernate")
public class ProfileUsersDaoHibernateImpl extends BaseDao implements ProfileUsersDao {
	@Override
	public List<ProfileUsers> getProfileUsers() throws Exception {
		List<ProfileUsers> listResult = em.createQuery("FROM ProfileUsers", ProfileUsers.class).getResultList();
		return listResult;
	}

	@Override
	public ProfileUsers getProfileUsersById(Long id) throws Exception {
		List<ProfileUsers> listResult = em.createQuery("FROM ProfileUsers WHERE id = ?1 ", ProfileUsers.class)
				.setParameter(1, id).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public Long insertProfileUser(ProfileUsers profileUsers) throws Exception {
		em.persist(profileUsers);
		return profileUsers.getId();
	}

	@Override
	public Long getIDbyFullname(String fullname) throws Exception {
		List<ProfileUsers> listResult = em
				.createQuery("FROM ProfileUsers WHERE fullName = ?1 ", ProfileUsers.class).setParameter(1, fullname)
				.getResultList();
		return listResult.size() > 0 ? listResult.get(0).getId() : null;
	}
}
