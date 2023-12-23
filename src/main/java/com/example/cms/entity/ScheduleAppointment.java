package com.example.cms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appointments")
public class ScheduleAppointment {
	
	@Column(name = "counselor_Id")
	private int counselorId;
	@Id
	@Column(name = "student_Id")
	private long studentId;
	@Column(name = "Full_Name")
	private String fullname;
	@Column(name = "email")
	private String email;
	@Column(name = "date")
	private String appt;
	@Column(name = "time")
	private String time;
	
	public ScheduleAppointment() {
		
	}

	public ScheduleAppointment(int counselorId, long studentId, String fullname, String email, String appt,
			String time) {
		super();
		this.counselorId = counselorId;
		this.studentId = studentId;
		this.fullname = fullname;
		this.email = email;
		this.appt = appt;
		this.time = time;
	}

	public int getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(int counselorId) {
		this.counselorId = counselorId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAppt() {
		return appt;
	}

	public void setAppt(String appt) {
		this.appt = appt;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
