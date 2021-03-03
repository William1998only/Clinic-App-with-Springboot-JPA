package com.lawencon.klinik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author WILLIAM
 *
 */

@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(allowSetters = true, value = {"password"})
@Table(name = "t_m_users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique=true, length=100, nullable=false)
	private String userName;
	
	@Column(name = "password", length=100, nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Roles idRole;
	
	@OneToOne
	@JoinColumn(name = "id_profile")
	private ProfileUsers idProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getIdRole() {
		return idRole;
	}

	public void setIdRole(Roles idRole) {
		this.idRole = idRole;
	}

	public ProfileUsers getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(ProfileUsers idProfile) {
		this.idProfile = idProfile;
	}
}
