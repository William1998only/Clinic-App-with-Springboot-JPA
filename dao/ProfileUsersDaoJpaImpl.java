package com.lawencon.klinik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.ProfileUsers;
import com.lawencon.klinik.repo.ProfileUserRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="profileusersjpa")
public class ProfileUsersDaoJpaImpl extends BaseDao implements ProfileUsersDao {
	
	@Autowired
	ProfileUserRepo profileUserRepo;
	
	@Override
	public List<ProfileUsers> getProfileUsers() throws Exception {
		return profileUserRepo.findAll();
	}

	@Override
	public ProfileUsers getProfileUsersById(Long id) throws Exception {
		return profileUserRepo.findById(id).get();
	}

	@Override
	public Long insertProfileUser(ProfileUsers profileUsers) throws Exception {
		profileUserRepo.save(profileUsers);
		return profileUsers.getId();
	}

	@Override
	public Long getIDbyFullname(String fullname) throws Exception {
		return profileUserRepo.findByFullName(fullname).getId();
	}
}
