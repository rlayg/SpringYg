package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
//		1. model Map 선언
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
//		2. parameter -> String bId, bName, bTitle, bContent
		
		BDao dao = new BDao();
		dao.modify(bId, bName, bTitle, bContent);
		
	}

}
