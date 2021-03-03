package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Roles;
import com.lawencon.klinik.model.Users;
import com.lawencon.klinik.repo.UserRepo;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="usersjpa")
public class UsersDaoJpaImpl extends BaseDao implements UsersDao {
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<Users> getUsers() throws Exception {
		List<Users> listResult = new ArrayList<>();
		List<Object[]> listObj = userRepo.getUser();
		listObj.forEach(objArr -> {
			Users user = new Users();
			user.setId((Long) objArr[0]);
			user.setUserName((String) objArr[1]);
			Roles role = new Roles();
			role.setRoleName((String) objArr[2]);
			user.setIdRole(role);
			listResult.add(user);
		});
		return listResult;
	}

	@Override
	public Users getUsersById(Long id) throws Exception {
		return userRepo.findById(id).get();
	}

	@Override
	public Users getUsersByPassword(String username, String password) throws Exception {
		return userRepo.findByUserNameAndPassword(username, password);
	}

	@Override
	public void insertUser(Users data) throws Exception {
		userRepo.save(data);
	}

	@Override
	public Users getUserByName(String inputUsername) throws SQLException {
		return userRepo.findByUserName(inputUsername);
	}

	@Override
	public void updatePassword(Users userDb) {
		userRepo.save(userDb);
	}

	@Override
	public void deleteUser(Users data) {
		userRepo.deleteById(data.getId());
	}
}
