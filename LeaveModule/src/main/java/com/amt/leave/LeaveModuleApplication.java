package com.amt.leave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeaveModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveModuleApplication.class, args);
		Logger logger = LoggerFactory.getLogger(LeaveModuleApplication.class);
		logger.info("<<<<<<<<<<<<<<<<<<<appication Started>>>>>>>>>>>>>>>");
	}

}
