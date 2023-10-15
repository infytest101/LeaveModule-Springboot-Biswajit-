package com.amt.leave.viewdto;

public class EmployeeLeaveRecordViewDto {
private String sNo;
private String leaveType;
private String entitled;
private String applied;
private String granted;
private String reject;
private String balance;
public String getsNo() {
	return sNo;
}
public void setsNo(String sNo) {
	this.sNo = sNo;
}
public String getLeaveType() {
	return leaveType;
}
public void setLeaveType(String leaveType) {
	this.leaveType = leaveType;
}
public String getEntitled() {
	return entitled;
}
public void setEntitled(String entitled) {
	this.entitled = entitled;
}
public String getApplied() {
	return applied;
}
public void setApplied(String applied) {
	this.applied = applied;
}
public String getGranted() {
	return granted;
}
public void setGranted(String granted) {
	this.granted = granted;
}
public String getReject() {
	return reject;
}
public void setReject(String reject) {
	this.reject = reject;
}
public String getBalance() {
	return balance;
}
public void setBalance(String balance) {
	this.balance = balance;
}
@Override
public String toString() {
	return "EmployeeLeaveRecordViewDto [sNo=" + sNo + ", leaveType=" + leaveType + ", entitled=" + entitled
			+ ", applied=" + applied + ", granted=" + granted + ", reject=" + reject + ", balance=" + balance + "]";
}
}
