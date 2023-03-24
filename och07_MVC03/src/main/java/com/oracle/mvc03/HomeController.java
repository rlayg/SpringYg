package com.oracle.mvc03;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;

import com.oracle.mvc03.dto.Member;

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
	
	// Parameter 선택
	@RequestMapping("board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		logger.info("confirmId Start...");
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		System.out.println("board/confirmId id -> " + id);
		System.out.println("board/confirmId pw -> " + pw);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);

		return "board/confirmId";	
	}
	
	// Parameter Force / 파라미터 강제
	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String idd,
						  @RequestParam("pw") int	 passwd,
						  Model				  model // 데이터를 넘겨줄 일이 있다면 parameter로 model을 잡아줘야한다
						 ) { // @RequestParam("id") 는 id를 강제한다는뜻 필수라는뜻
		logger.info("checkId Start...");
		System.out.println("board/checkId idd -> "+ idd);
		System.out.println("board/checkId passwd -> "+ passwd);
		model.addAttribute("identify", idd);
		model.addAttribute("password", passwd);
		
		return "board/checkId";
	}

	// Parameter Force / 파라미터 강제
	@RequestMapping("member/join")
	public String member( @RequestParam("name")		String name,
						  @RequestParam("id")		String id,
						  @RequestParam("pw")		String pw,
						  @RequestParam("email")		String email,
						  Model model
						)
	{
//		이렇게 일일이 로그지정 하면 좋다 그런데 이렇게 말고 DTO에서 toString으로 지정할 수도 있음
//		System.out.println("member/join name  -> " + name);
//		System.out.println("member/join id 	  -> " + id);
//		System.out.println("member/join pw 	  -> " + pw);
//		System.out.println("member/join email -> " + email);
		
		Member member = new Member();
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		
		System.out.println("member/join member -> " + member);
		
		model.addAttribute("member", member);
		
		return "member/join";
	}
	
//	DTO Parameter *****
	@RequestMapping("member/join/dto")
	public String memberDto(Member member,
							Model model
			)
	{
		System.out.println("member/join member.getName() -> " + member.getName());
		System.out.println("member/join getId() -> " + member.getId());
		System.out.println("member/join getPw() -> " + member.getPw());
		System.out.println("member/join Email() -> " + member.getEmail());
		System.out.println("member/join member  -> " + member);
		
		model.addAttribute("member", member);
		
		return "member/join";
	}
}
