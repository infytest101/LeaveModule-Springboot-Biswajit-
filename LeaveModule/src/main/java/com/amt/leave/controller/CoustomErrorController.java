package com.amt.leave.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoustomErrorController implements ErrorController{
@RequestMapping("/error")
public String handelError() {
	return "error in code";
	
}


}
