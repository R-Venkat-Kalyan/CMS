package com.example.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.entity.CMSUsers;
import com.example.cms.repository.CMSUsersRepository;

@Service
public class CMSUsersService {
	
	@Autowired
	private  CMSUsersRepository cur;
	
	public void storeToDB(CMSUsers cu) {
		cur.save(cu);	
	}
	
	public List<CMSUsers> getAllUsers(){
		return cur.findAll();
	}
	
	public boolean getByID(long id){
		return cur.existsById(id);
	}

}
