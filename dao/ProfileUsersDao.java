package com.lawencon.klinik.dao;

import java.util.List;

import com.lawencon.klinik.model.ProfileUsers;

/**
 * 
 * @author WILLIAM
 *
 */
public interface ProfileUsersDao {
	List<ProfileUsers> getProfileUsers() throws Exception;
	
	ProfileUsers getProfileUsersById(Long id) throws Exception;
	
	Long insertProfileUser(ProfileUsers profileUsers) throws Exception;

	Long getIDbyFullname(String fullname) throws Exception;
}
