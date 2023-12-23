package com.example.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.entity.ScheduleAppointment;
import com.example.cms.repository.ScheduledAppointmentsRepository;

@Service
public class ScheduledAppointmentsService {
	
	@Autowired
	private ScheduledAppointmentsRepository sar;
	
	public void storeAppointments(ScheduleAppointment sa) {
		sar.save(sa);
	}

	public boolean appointmentExists(long id) {
		return sar.existsById(id);
	}
	
	public List<ScheduleAppointment> getAllAppointments(){
		return sar.findAll();
	}
	
	public void deleteAppointment(long id) {
		sar.deleteById(id);
	}
}
