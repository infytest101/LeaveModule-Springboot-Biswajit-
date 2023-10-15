package com.amt.leave.viewdto;

public class EmployeeDeatilsViewDto {
	private String name;
	private String mobileNo;
	private String email;
	private String designation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getBandName() {
		return bandName;
	}
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	private String bandName;
	@Override
	public String toString() {
		return "EmployeeDeatilsViewDto [name=" + name + ", mobileNo=" + mobileNo + ", email=" + email + ", designation="
				+ designation + ", bandName=" + bandName + "]";
	}
}
