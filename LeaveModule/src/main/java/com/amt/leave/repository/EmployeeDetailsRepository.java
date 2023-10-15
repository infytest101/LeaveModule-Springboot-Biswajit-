package com.amt.leave.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amt.leave.entity.EmployeeDetails;
import com.amt.leave.viewdto.EmployeeDeatilsViewDto;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Integer> {
	
	@Procedure(procedureName = "getEmployeeLeaveDetailsByName")
	List<Object[]> getEmployeeLeaveDetailsByName(@Param("employeeName")String employeeName);
	
@Query(value="SELECT ed.name, ed.mobile_no,ed.email,ed.designation,teb.band_name"
		+ " FROM leavemanagment.tbl_employee_dtls ed inner join tbl_emp_band teb ON ed.band_id=teb.band_id"
		+ " where ed.name=:employeeName",nativeQuery = true)
List<Object[]> findbyEmployeeName(@Param("employeeName")String employeeName);
}
