package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.ProfileUsersDao;
import com.lawencon.klinik.model.ProfileUsers;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class ProfileUsersServiceImpl extends BaseService implements ProfileUsersService {

	@Autowired
	@Qualifier(value = "profileusersjpa")
	private ProfileUsersDao profileUsersDao;

	@Override
	public List<ProfileUsers> getProfileUsers() throws Exception {
		return profileUsersDao.getProfileUsers();
	}

	@Override
	public ProfileUsers getProfileUsersById(Long id) throws Exception {
		return profileUsersDao.getProfileUsersById(id);
	}

	@Override
	public Long insertProfileUser(ProfileUsers profileUsers) throws Exception {
		return profileUsersDao.insertProfileUser(profileUsers);
	}

	@Override
	public Long getIDbyFullname(String fullname) throws Exception {
		return profileUsersDao.getIDbyFullname(fullname);
	}

}
