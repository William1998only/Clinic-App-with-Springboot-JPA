package com.lawencon.klinik.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.klinik.model.Roles;
import com.lawencon.klinik.model.Users;

/**
 * 
 * @author WILLIAM
 *
 */

@Repository(value="usershibernate")
public class UsersDaoHibernateImpl extends BaseDao implements UsersDao {
	@Override
	public List<Users> getUsers() throws Exception {
		List<Users> listResult = new ArrayList<>();
		List<Object[]> listObj = em.createQuery("SELECT u.id, u.userName, r.roleName " + "from Users as u "
				+ "INNER JOIN u.idRole as r " + "WHERE r.id = 2 or r.id = 3", Object[].class).getResultList();
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
		List<Users> listResult = em.createQuery("FROM Users where id = ?1 ", Users.class).setParameter(1, id)
				.getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public Users getUsersByPassword(String username, String password) throws Exception {
		List<Users> listResult = em
				.createQuery("FROM Users WHERE userName = ?1 and password = ?2", Users.class).setParameter(1, username)
				.setParameter(2, password).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void insertUser(Users data) throws Exception {
		em.persist(data);
	}

	@Override
	public Users getUserByName(String inputUsername) throws SQLException {
		List<Users> listResult = em.createQuery("FROM Users WHERE userName = ?1 ", Users.class)
				.setParameter(1, inputUsername).getResultList();
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

	@Override
	public void updatePassword(Users userDb) {
		em.createQuery("UPDATE Users SET password = ?1 WHERE userName = ?2")
		.setParameter(1, userDb.getPassword())
		.setParameter(2, userDb.getUserName())
		.executeUpdate();
	}

	@Override
	public void deleteUser(Users u) {
		em.remove(u);
	}
}
