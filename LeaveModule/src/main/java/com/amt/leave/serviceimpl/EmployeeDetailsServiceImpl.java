package com.amt.leave.serviceimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amt.leave.entity.EmployeeDetails;
import com.amt.leave.repository.EmployeeDetailsRepository;
import com.amt.leave.service.EmployeeDetailsService;
import com.amt.leave.viewdto.EmployeeDeatilsViewDto;
import com.amt.leave.viewdto.EmployeeLeaveRecordViewDto;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {
	static final Logger LOGGER= LoggerFactory.getLogger(EmployeeDetailsServiceImpl.class);
	
	@Autowired
	EmployeeDetailsRepository repo;
	
	@Override
	public List<EmployeeDetails> findAllEmployeeDeatils() {
		try {
		LOGGER.info("inside EmployeeDetailsServiceImpl :: findAllEmployeeDeatils() :: Started::" );
		
		List<EmployeeDetails> employeeDetails=repo.findAll();
		
		if(!employeeDetails.isEmpty()){
			return employeeDetails;
		}
		}
		catch(Exception ex){
			LOGGER.error(ex.getMessage());
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public List<EmployeeLeaveRecordViewDto> findEmployeeLeaveRecord(String employeeName) {
		
		List <Object[]> resultData = repo.getEmployeeLeaveDetailsByName(employeeName);
		List <EmployeeLeaveRecordViewDto> empListAllData = new ArrayList<>();
		LOGGER.info(" employee all Data::: "+repo.getEmployeeLeaveDetailsByName(employeeName));
		
		for (Object[] result : resultData) {
			
			EmployeeLeaveRecordViewDto empViewDto =new EmployeeLeaveRecordViewDto();
			
			empViewDto.setsNo(result[0].toString());
			empViewDto.setLeaveType(result[1].toString());
			empViewDto.setEntitled(result[2].toString());
			empViewDto.setApplied(result[3].toString());
			empViewDto.setGranted(result[4].toString());
			empViewDto.setReject(result[5].toString());
			empViewDto.setBalance(result[6].toString());
			empListAllData.add(empViewDto);
		}
		LOGGER.info("empListAllData :: "+empListAllData);
		return empListAllData;
	}

	@Override
	public List<EmployeeDeatilsViewDto> findEmployeeData(String employeeName) {
		
		List <Object[]> resultData = repo.findbyEmployeeName(employeeName);
		//LOGGER.info("wwwwwwwwwwwwww:: "+resultData);
		List<EmployeeDeatilsViewDto> empListAllData = new ArrayList<>();
		
		for (Object[] result : resultData) {
			
			EmployeeDeatilsViewDto empViewDto =new EmployeeDeatilsViewDto();
			
			empViewDto.setName(result[0].toString());
			empViewDto.setMobileNo(result[1].toString());
			empViewDto.setEmail(result[2].toString());
			empViewDto.setDesignation(result[3].toString());
			empViewDto.setBandName(result[4].toString());
			empListAllData.add(empViewDto);
		}
		LOGGER.info("findbyEmployeeName employee Data :: "+empListAllData);
		return empListAllData;
	}

	@Override
	public String mailSendToHR() {
		File htmlFile = new File ("");
		StringBuilder htmlContent = new StringBuilder();
		try( FileReader reader = new FileReader(htmlFile)){
			int charecter;
			while((charecter = reader.read())!=-1) {
				htmlContent.append((char)charecter);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
