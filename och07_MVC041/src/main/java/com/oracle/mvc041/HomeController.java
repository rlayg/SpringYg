package com.oracle.mvc041;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	// 밑에 public String postStudent와 이어짐. /index 실행하고 값 넣으면 밑에서 또 저장되는듯
	@RequestMapping("index")
	public String goindex() {
		logger.info("index Start...");
		return "index";
	}
	
//	GET방식
//	@RequestMapping(value = "student", method = RequestMethod.GET )
	@RequestMapping("student")	//다른것들 생략하면 기본이 GET방식 / index에서 post방식으로 바꾸고 get방식이 없다면 post방식으로 변경해줌 근데 이렇게 안하고 바로 위처럼 get으로 메소드를 정의하고 index에서 post로 보내면 405오류 허용하지 않는 메소드라고 나옴
	public String getStudent(HttpServletRequest request, Model model) {
		logger.info("getStudent Start...");
		String id = request.getParameter("id");
		System.out.println("GET id : " + id);
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
	
//	POST방식
	@RequestMapping(value = "student", method = RequestMethod.POST )
	public String postStudent(HttpServletRequest request, Model model) {
		logger.info("postStudent Start...");
		String id = request.getParameter("id");
		System.out.println("POST id : " + id);
		model.addAttribute("studentId", id);
		
		return "student/studentId";
		
	}
	
}
