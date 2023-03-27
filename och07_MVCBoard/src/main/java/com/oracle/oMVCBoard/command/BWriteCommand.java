package com.oracle.oMVCBoard.command;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
//		  1)  model이용 , map 선언
//		  2) request 이용 ->  bName  ,bTitle  , bContent  추출
//		  3) dao  instance 선언
//		  4) write method 이용하여 저장(bName, bTitle, bContent)
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
//		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = new BDao();
		dao.write(bName, bTitle, bContent);
		
		/*
			시퀸스 방식
			새 시퀸스 - 이름: MVC_BOARD_SEQ
			다음으로 시작 : 44
		------------------------------------
			bId --> mvc_board_seq.nextval
			bGroup --> bId와 동일번호
			bStep, bIndent , bHit --> 0
			bDate -> sysdate
		*/
	}

}
