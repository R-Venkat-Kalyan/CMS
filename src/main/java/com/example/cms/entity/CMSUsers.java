package com.example.cms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CMS_USERS")
public class CMSUsers {

	@Column(name = "name")
	private String name;
	@Id
	@Column(name = "ID")
	private long id;
	@Column(name = "password")
	private String password;
	@Column(name = "rePassword")
	private String rePassword;
	
	public CMSUsers() {
		
	}
	
	public CMSUsers(String name, long id, String password, String rePassword) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.rePassword = rePassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	
	
}
