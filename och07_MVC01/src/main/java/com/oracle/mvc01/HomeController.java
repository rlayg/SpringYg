package com.oracle.mvc01;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
		// setAttribute
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("kkk", "we Parameter" );
		// ViewResolver
		// /WEB-INF/views/ + home + .jsp
		// prefix : 선행자 , suffix : 후행자 -> 선행자 + home + 후행자
		return "home";
	}
	@RequestMapping(value = "/hr", method = RequestMethod.GET)
	public String hr(Model model) {
		System.out.println("/hr Start..");
		model.addAttribute("hr", "hr Value"); // 파라미터, 값 
		return "hr";
	}
}
