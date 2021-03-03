package com.lawencon.klinik.service;

import java.util.List;

import com.lawencon.klinik.model.ProfileUsers;

/**
 * 
 * @author WILLIAM
 *
 */
public interface ProfileUsersService {
	List<ProfileUsers> getProfileUsers() throws Exception;

	ProfileUsers getProfileUsersById(Long id) throws Exception;

	Long insertProfileUser(ProfileUsers profileUsers) throws Exception;

	Long getIDbyFullname(String fullname) throws Exception;
}
