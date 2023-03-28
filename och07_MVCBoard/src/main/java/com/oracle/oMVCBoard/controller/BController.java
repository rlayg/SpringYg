package com.oracle.oMVCBoard.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oMVCBoard.command.BCommand;
import com.oracle.oMVCBoard.command.BContentCommand;
import com.oracle.oMVCBoard.command.BDeleteCommand;
import com.oracle.oMVCBoard.command.BListCommand;
import com.oracle.oMVCBoard.command.BModifyCommand;
import com.oracle.oMVCBoard.command.BReplyCommand;
import com.oracle.oMVCBoard.command.BReplyViewCommand;
import com.oracle.oMVCBoard.command.BWriteCommand;

@Controller
public class BController {
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	
	BCommand command = null;
	
	@RequestMapping("list")
	public String list(Model model) {
		logger.info("list Start...");
		command = new BListCommand();
		command.execute(model);
		return "list";
		
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
//							KEY		  Value(객체)
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
//	@RequestMapping + POST
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		logger.info("modify Start...");
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
		public String write_view(Model model) {
			logger.info("write_view Start...");
			
			return "write_view";
	}
	
//	@RequestMapping(value = "/modify", method = RequestMethod.POST) 
//	이것과 같아 @RequestMapping + method = RequestMethod.POST 
	@PostMapping	// 옛날버전은 안돼 / 요청 메소드가 POST일 때만 해당 메소드를 실행하도록 지정 / 반대로 GET매핑도 있어
	public String write(HttpServletRequest request, Model model) {
		logger.info("write Start...");
		
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view Start...");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		return "reply_view";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(HttpServletRequest request, Model model) {
		logger.info("reply Start...");
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("Controller delete Start...");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
}
