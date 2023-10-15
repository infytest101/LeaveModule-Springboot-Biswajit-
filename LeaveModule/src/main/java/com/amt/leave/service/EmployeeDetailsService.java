package com.amt.leave.service;

import java.util.List;

import com.amt.leave.entity.EmployeeDetails;
import com.amt.leave.viewdto.EmployeeDeatilsViewDto;
import com.amt.leave.viewdto.EmployeeLeaveRecordViewDto;

public interface EmployeeDetailsService {
 public List<EmployeeDetails> findAllEmployeeDeatils();
 public List<EmployeeDeatilsViewDto> findEmployeeData(String employeeName);
 public List<EmployeeLeaveRecordViewDto> findEmployeeLeaveRecord(String employeeName);
public String mailSendToHR();
}
