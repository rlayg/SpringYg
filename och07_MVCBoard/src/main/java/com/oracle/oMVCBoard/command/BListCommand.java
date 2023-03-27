package com.oracle.oMVCBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;


//Service 서비스를 커맨드라고 하는 사람도 있어
public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
//		Dao 연결
		BDao dao = new BDao();
		ArrayList<BDto> boardDtoList = dao.boardList();
		System.out.println("BListCommand boardDtoList.size() --> " + boardDtoList.size());
		model.addAttribute("boardList", boardDtoList);	//	model.addAttribute()는 Spring MVC에서 View로 데이터를 전달하기 위해 사용되는 메소드입니다
		
	}

}
