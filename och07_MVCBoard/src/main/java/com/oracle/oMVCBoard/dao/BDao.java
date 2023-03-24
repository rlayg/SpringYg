package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oracle.oMVCBoard.dto.BDto;

public class BDao {
	DataSource dataSource;
	
	public BDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (NamingException e) {
			System.out.println("생성자 dataSource --> " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	public ArrayList<BDto> boardList() {
		ArrayList<BDto> bList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println("BDao boardList start...");
//		1. SQL 조회후 --> 
//		2. BDto dto Setting {1. setter 이용방법, 2. DTO이용방법 여기는 2번 DTO 이용방법}
		
		String query = "SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent\r\n" + 
				"FROM    mvc_board\r\n" + 
				"order by bGroup desc, bStep asc";
		
//		아래 query가 실행 안되는 이유 : 세렉문 끝나고 프롬 끝나고 오더바이 사이사이에 띄어쓰기가 안되어있어서 안먹힘
//		String query = "SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent" + 
//				"FROM    mvc_board" + 
//				"order by bGroup desc, bStep asc;";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			System.out.println("BDao query --> " + query);
			resultSet = preparedStatement.executeQuery(); 
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bid");
				String bName = resultSet.getNString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
//				2. BDto dto setting DTO방식
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent);
				
				bList.add(dto);
				

//				이건 세터방식
//				while(resultSet.next()) {
//					BDto bDto = new BDto();
//					bDto.setbId(resultSet.getInt("bId"));
//					bDto.setbName(resultSet.getString("bName"));
//					bDto.setbTitle(resultSet.getString("bTitle"));
//					bDto.setbContent(resultSet.getString("bContent"));
//					bDto.setbDate(resultSet.getTimestamp("bDate"));
//				}
			}
					
		} catch (Exception e) {
			System.out.println("list dataSource --> " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bList;
	}
}
