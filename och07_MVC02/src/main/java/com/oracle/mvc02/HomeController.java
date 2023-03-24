package com.oracle.mvc02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = "/board/view")
	public String view() {
		logger.info("Welcome home! {} start...", "/board/view"); //{}안에 "/board/view"가 들어가
		// ViewResolver
		// /WEB-INF/views/ + board/view + .jsp 
		
		return "board/view";
	}
	
	@RequestMapping("/board/content") // 여기는 value = 가 없어
//	@RequestMapping(value = "/board/content")
//	뷰 명을 적어줘야하기 떄문에 스트링
//	밑에 ModelAndView보다 Model인 이 형식을 더 많이 사용
	public String content(Model model) {	// String으로 해야함 
		System.out.println("HomeController content start...");
		model.addAttribute("id", 365);
		// ViewResolver
		// /WEB-INF/views/ + board/content + .jsp 
		
		return "board/content";
	}
	
	@RequestMapping("/board/reply") // ModelAndView 모델과 뷰를 한거번에 설정하는 애
	public ModelAndView reply() {
		System.out.println("HomeController reply start...");
		// 목적 : parameter와 View를 한방에 처리
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", 50);
		mav.setViewName("board/reply");
		
		return mav;
		
	}
}
