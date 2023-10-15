package com.amt.leave.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.stream.FileImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.amt.leave.entity.EmployeeDetails;
import com.amt.leave.service.EmployeeDetailsService;
import com.amt.leave.viewdto.EmployeeDeatilsViewDto;
import com.amt.leave.viewdto.EmployeeLeaveRecordViewDto;
import com.itextpdf.html2pdf.HtmlConverter;

@Controller
public class LeaveController {
	static final Logger LOGGER = LoggerFactory.getLogger(LeaveController.class);

	@Autowired
	EmployeeDetailsService empServcies;
	
	@GetMapping("/homepage")
	public String leaveRenderUrl(Model model) {
		
		LOGGER.info("LeaveController:: leaveRenderUrl() :: Rendering page");
		
		return "leavemodule";
	}
	
	@GetMapping("/fetchEmployeeName")
	public ResponseEntity<List<String>> fetchEmployeeName() {
		
		LOGGER.info("LeaveController:: leaveRenderUrl() :: Rendering page");
		
		List<EmployeeDetails>empDetails=empServcies.findAllEmployeeDeatils();
		List<String>empDetailsData=empDetails.stream().map(EmployeeDetails::getName).collect(Collectors.toList());
	
		LOGGER.info("fetching empDetailsData Data from Dynamic DB:::"+empDetailsData);
		return ResponseEntity.ok(empDetailsData);
	}
	
	@GetMapping(value= "/fetchFilterEmployeeDetils", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeLeaveRecordViewDto>> fetchFilterEmployeeDetils(@RequestParam("employeeName") String employeeName) {
			List<EmployeeLeaveRecordViewDto> empLeaveRecord = empServcies.findEmployeeLeaveRecord(employeeName);
		
		return ResponseEntity.ok(empLeaveRecord);
		
	}
	
	@GetMapping(value="/fetchEmployeeDetails",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDeatilsViewDto>> fetchEmployeeDetails(@RequestParam("employeeName") String employeeName,Model model) {
		LOGGER.info("Employee Name from UI ::: "+employeeName);
		List <EmployeeDeatilsViewDto> empViewDto=empServcies.findEmployeeData(employeeName);
		return ResponseEntity.ok(empViewDto);
	}
	
	@GetMapping(value="/MailtoHR",produces = MediaType.APPLICATION_JSON_VALUE)
	public String  MailtoHR() throws IOException {
		LOGGER.info("444444444444444444444444");
		convertHTMLtoPDF();
		LOGGER.info("11111111111111111111111");
		return "mail send sucessfullly";
	}
	public String convertHTMLtoPDF() throws IOException{
		try {
			String htmlFilePath = "src/main/webapp/views/pdfgenerator.html";
			String pdfFilePath = "src/main/webapp/views";
			convertHtmlfileToPdf(htmlFilePath,pdfFilePath);
			LOGGER.info("2222222222222222222222");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "pdf convert sucessfully";
	}

	private void convertHtmlfileToPdf(String htmlFilePath, String pdfFilePath) throws FileNotFoundException, IOException {
		FileInputStream htmlfile = new FileInputStream(new File(htmlFilePath));
		FileOutputStream pdffile = new FileOutputStream(new File(pdfFilePath));
		HtmlConverter.convertToPdf(htmlfile, pdffile);
		htmlfile.close();
		pdffile.close();
		LOGGER.info("######################");
	}
	
}
