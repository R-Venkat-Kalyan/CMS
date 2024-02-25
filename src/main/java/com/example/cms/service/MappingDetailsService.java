package com.example.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.entity.MappingDetails;
import com.example.cms.repository.MappinngDetailsRepository;

@Service
public class MappingDetailsService {
	
	@Autowired
	private MappinngDetailsRepository mdr;
	
	public void storeToDB(MappingDetails md) {
		mdr.save(md);
	}

	public List<MappingDetails> getAllRecords(){
		return mdr.findAll();
	}
	
	public MappingDetails getById(long id) {
		return mdr.findById(id).orElse(null);
	}
	
	public void deleteMapping(long id) {
		mdr.deleteById(id);
	}
	
	public boolean checkUser(long id) {
		return mdr.existsById(id);
	}
}
