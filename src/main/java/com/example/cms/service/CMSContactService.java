package com.example.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.entity.CMSContact;
import com.example.cms.repository.CMSContactRepository;

@Service
public class CMSContactService {
	
	@Autowired
	private CMSContactRepository ccr;
	
	public void addToDB(CMSContact cc) {
		ccr.save(cc);
	}

}
