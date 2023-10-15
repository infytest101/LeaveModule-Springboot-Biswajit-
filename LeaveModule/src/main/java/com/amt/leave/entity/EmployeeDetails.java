package com.amt.leave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name="tbl_employee_dtls")
	public class EmployeeDetails {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private Integer empId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="band_id")
	private String bandId;
	
	@Override
	public String toString() {
		return "EmployeeDetails [empId=" + empId + ", name=" + name + ", address=" + address + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", designation=" + designation + ", bandId=" + bandId + "]";
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}


	
}
