package com.oracle.mvc042;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.mvc042.dto.StudentInformation;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("HomeController index Start...");
		return "index";
	}

//	studentView1, studentView2 로 해도 상관없다 / DML작업 혹은 가공할때는 이렇게
	@RequestMapping("/studentView1")
	public String studentView1(StudentInformation studentInformation, Model model) {
		logger.info("studentView1 Start...");
		System.out.println("studentInformation.getName() --> " + studentInformation.getName());
		System.out.println("studentInformation.getClassNum() --> " + studentInformation.getClassNum());
		System.out.println("studentInformation.getGradeNum() --> " + studentInformation.getGradeNum());
		System.out.println("studentInformation.getAge() --> " + studentInformation.getAge());
		
		model.addAttribute("studentInfo", studentInformation);
		
		return "studentView";
		
	}
	
//	@ModelAttribute 선언과 동시에 넣고 밑에는 출력만한거 / 모델에 한번에 넣은거 / 가공안하고 한번에 디스플레이에 쏴줄때 이렇게 한다
	@RequestMapping("/studentView2")
	public String studentView2(@ModelAttribute("studentInfo") StudentInformation studentInformation) {
		logger.info("studentView2 Start...");
		System.out.println("studentInformation.getName() --> " + studentInformation.getName());
		System.out.println("studentInformation.getClassNum() --> " + studentInformation.getClassNum());
		System.out.println("studentInformation.getGradeNum() --> " + studentInformation.getGradeNum());
		System.out.println("studentInformation.getAge() --> " + studentInformation.getAge());
		
		return "studentView";
		
	}
}
