package com.example.cms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CounselorMappings")
public class MappingDetails {

	@Column(name = "Counselor_Name")
	private String counselorName;       
	
	@Column(name = "Counselor_ID")
	private int counselorId;           
	
	@Column(name = "Counselor_Cabin")
	private String cabinId;
	
	@Column(name = "Counselor_Number")
	private long counselorPhone;
	
	@Column(name = "Telegram_Link")
	private String telegramLink;
	
	@Column(name = "Student_Name")
	private String studentName;
	
	@Column(name = "Student_ID")
	@Id
	private long studentId;
	
	@Column(name = "Student_Branch")
	private String studentDepartment;
	
	@Column(name = "Student_Number")
	private long studentPhone;
	
	public MappingDetails() {
		
	}

	public MappingDetails(String counselorName, int counselorId, String cabinId, long counselorPhone,
			String telegramLink, String studentName, long studentId, String studentDepartment, long studentPhone) {
		super();
		this.counselorName = counselorName;
		this.counselorId = counselorId;
		this.cabinId = cabinId;
		this.counselorPhone = counselorPhone;
		this.telegramLink = telegramLink;
		this.studentName = studentName;
		this.studentId = studentId;
		this.studentDepartment = studentDepartment;
		this.studentPhone = studentPhone;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}

	public int getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(int counselorId) {
		this.counselorId = counselorId;
	}

	public String getCabinId() {
		return cabinId;
	}

	public void setCabinId(String cabinId) {
		this.cabinId = cabinId;
	}

	public long getCounselorPhone() {
		return counselorPhone;
	}

	public void setCounselorPhone(long counselorPhone) {
		this.counselorPhone = counselorPhone;
	}

	public String getTelegramLink() {
		return telegramLink;
	}

	public void setTelegramLink(String telegramLink) {
		this.telegramLink = telegramLink;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentDepartment() {
		return studentDepartment;
	}

	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}

	public long getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(long studentPhone) {
		this.studentPhone = studentPhone;
	}
	
	
	
}
