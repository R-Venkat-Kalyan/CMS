package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.CMSUsers;

public interface CMSUsersRepository extends JpaRepository<CMSUsers, Long> {

}
