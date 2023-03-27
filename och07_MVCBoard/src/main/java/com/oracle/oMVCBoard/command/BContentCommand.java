package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// model이를 Map으로 전환
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto board = dao.contentView(bId);
		
		model.addAttribute("mvc_board", board);	// model.addAttribute()는 Spring MVC에서 View로 데이터를 전달하기 위해 사용되는 메소드입니다
		
	}

}
