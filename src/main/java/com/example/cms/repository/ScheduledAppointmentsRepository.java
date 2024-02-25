package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.ScheduleAppointment;

public interface ScheduledAppointmentsRepository extends JpaRepository<ScheduleAppointment, Long> {

}
