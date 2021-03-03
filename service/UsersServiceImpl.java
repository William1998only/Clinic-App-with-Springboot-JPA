package com.lawencon.klinik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.klinik.dao.UsersDao;
import com.lawencon.klinik.model.ProfileUsers;
import com.lawencon.klinik.model.Roles;
import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Service
@Transactional
public class UsersServiceImpl extends BaseService implements UsersService {

	@Autowired
	@Qualifier(value="usersjpa")
	private UsersDao usersDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ProfileUsersService profileUsersService;

	@Autowired
	private RolesService rolesService;

	@Override
	public List<Users> getUser() throws Exception {
		return usersDao.getUsers();
	}

	@Override
	public Users getUserById(Long id) throws Exception {
		System.out.println("aaaaaa");
		return usersDao.getUsersById(id);
	}

	@Override
	public Users getUserByPassword(String username, String password) throws Exception {
		return usersDao.getUsersByPassword(username, password);
	}

	@Override
	public void insertUser(Users user) throws Exception {
		ProfileUsers profile = new ProfileUsers();
		Roles role = new Roles();
		Long idProfile;
		idProfile = profileUsersService.insertProfileUser(user.getIdProfile());
		profile = profileUsersService.getProfileUsersById(idProfile);
		user.setIdProfile(profile);
		role = rolesService.getRoleById(user.getIdRole().getRoleCode().longValue());
		user.setIdRole(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		usersDao.insertUser(user);
	}

	@Override
	public Users getUserByName(String inputUsername) throws Exception {
		return usersDao.getUserByName(inputUsername);
	}

	@Override
	public void updatePassword(Users userDb) {
		userDb.setPassword(passwordEncoder.encode(userDb.getPassword()));
		usersDao.updatePassword(userDb);
	}

	@Override
	public void deleteUser(Users data) {
		usersDao.deleteUser(data);
	}

}
