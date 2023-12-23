package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.CMSContact;

public interface CMSContactRepository extends JpaRepository<CMSContact, String> {

}
