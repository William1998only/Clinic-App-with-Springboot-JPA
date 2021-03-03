package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */
public interface UsersService {
	List<Users> getUser() throws Exception;

	Users getUserById(Long id) throws Exception;

	Users getUserByPassword(String username, String password) throws Exception;

	void insertUser(Users data) throws Exception;

	Users getUserByName(String inputUsername) throws Exception;

	void updatePassword(Users userDb);

	void deleteUser(Users data);
}
