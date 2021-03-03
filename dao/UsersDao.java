package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
public interface UsersDao {
	List<Users> getUsers() throws Exception;

	Users getUsersById(Long id) throws Exception;

	Users getUsersByPassword(String username, String password) throws Exception;

	Users getUserByName(String inputUsername) throws SQLException;

	void insertUser(Users data) throws Exception;

	void updatePassword(Users data);

	void deleteUser(Users data);
}
