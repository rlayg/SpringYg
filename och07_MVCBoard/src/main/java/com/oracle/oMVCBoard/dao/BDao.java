package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
//				2. BDto dto setting DTO방식 / 생성자 이용
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

	public BDto contentView(String strId) {
//		Hit(조회수) 증가
		upHit(strId);
		
		BDto dto = null;
//		1. bId --> mvc_board 내용을 조회
//		2. 생성자 이용 --> 내용을 dto 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("BDao contentView start...");
		
		String sql = "Select * from mvc_board where bId = ?";
		System.out.println("sql --> " + sql);
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strId));
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				int bId = rs.getInt("bid");
				String bName = rs.getNString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
//				2. BDto dto setting DTO방식 / 생성자 이용 내용을 dto
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			System.out.println("list dataSource --> " + e.getMessage());
			e.printStackTrace();
		} finally {
//			이곳에 try-catch 하는 이유 : 여기서 던지면 BContentCommand이 부분이 처리해야할게 생김
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
			
		}
		
		return dto;
	}

	private void upHit(String strId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, strId);
			
			int rn = preparedStatement.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}	
		
		
		// 바로 위 public BDto contentView 안의 upHit사용. 이 안에서만 쓸거기에 private
		
		
		
		/*
		 
	private void upHit(String strId) {
	    // strId를 이용하여 DB에서 해당 게시물의 조회수 정보를 가져온다.
	    // 만약 DB에 해당 ID가 없을 경우, 새로운 레코드를 추가한다.
	    
	    // 조회수 정보를 1 증가시킨다.
	    int hitCount = // DB에서 가져온 조회수 정보;
	    hitCount++;
	    
	    // 증가된 조회수 정보를 다시 DB에 저장한다.
	}
		
		*/
	}

	public void modify(String bId, String bName, String bTitle, String bContent) {
//		int result = null;
//		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		System.out.println("BDao modify start...");
		
		String sql = "UPDATE mvc_board SET bName = ?, bTitle = ?, bContent = ? Where bId = ?";
		System.out.println("sql --> " + sql);
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			int rn= pstmt.executeUpdate();	// 수정할때를 대비해 만들어놧대
			if (rn > 0) {
		        System.out.println("modify UPDATE 성공");
		    } else {
		        System.out.println("modify UPDATE 실패");
		    }
			
		} catch (Exception e) {
			System.out.println("e.getMessage() --> " + e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
			
		}
		
//		return result; void로 해서 return 안한 예
	}

//	SEQ 사용
	public void write(String bName, String bTitle, String bContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("BDao write start...");	
		
//		String sql = "INSERT INTO mvc_board "
//		           + "(bId, bName, bTitle, bContent, bGroup, bStep, bIndent, bHit, bDate) "
//		           + "VALUES (mvc_board_seq.nextval, ?, ?, ?, mvc_board_seq.currval, 0, 0, 0, sysdate)";
		
		
		String sql = "INSERT INTO mvc_board "
		           + "(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent,  bDate) "
		           + "VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0, sysdate)";
		
		System.out.println("sql --> " + sql);
		
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			int rn = pstmt.executeUpdate();
			if (rn > 0) {
		        System.out.println("write INSERT 성공");
		    } else {
		        System.out.println("write INSERT 실패");
		    }
			
		} catch (Exception e) {
			System.out.println("write dataSource --> " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
			
		}
		
	}

	public BDto reply_view(String rebId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto dto = null;
		System.out.println("BDao reply_view start...");
		
		String sql = "Select * from mvc_board where bId = ?";
		System.out.println("sql --> " + sql);
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(rebId));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bId = rs.getInt("bid");
				String bName = rs.getNString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
//				2. BDto dto setting DTO방식 / 생성자 이용 내용을 dto
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
			
		}
		
		return dto;
	}

//	SEQ 사용
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
//		홍해 기적 --> bGroup 같고, parameter에 있는 bStep보다 큰 bStep을 가진 Row에 대해 bStep 하나 증가 ++ 
		replyShape(bGroup, bStep);
		
//		1. insert into mvc_board
//		2. bId --> SEQ
//		3. bGroup --> bGroup
//		4. bStep --> 현재 번호보다 +1
//		5. bIndent --> 현재 번호보다 +1
//		6. 나머지는 입력받은데로 --> bName, bTitle, bContent
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("BDao reply start...");	
		
		String sql = "insert into mvc_board "
		           + "(bId, bName, bTitle, bContent, bGroup, bStep, bIndent) "
		           + "VALUES (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		System.out.println("sql --> " + sql);

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep) + 1);
			pstmt.setInt(6, Integer.parseInt(bIndent) + 1);			// och16과 같은 로직
			
			int rn = pstmt.executeUpdate();
			if (rn > 0) {
//		        System.out.println("reply INSERT 성공");
		        System.out.printf("reply INSERT %d개 성공\n", rn);
		    } else {
		        System.out.println("reply INSERT 실패");
		    }
		} catch (Exception e) {
			System.out.println("e.getMessage() --> " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
		}
	}

	private void replyShape(String bGroup, String bStep) {
//		홍해 기적 --> bGroup 같고, parameter에 있는 bStep보다 큰 bStep을 가진 Row에 대해 bStep 하나 증가 ++
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("BDao replyShape start...");	
		
		String sql = "Update mvc_board "
		           + "set bStep = bStep + 1 "
		           + "where bGroup = ? and bStep > ?";
		System.out.println("sql --> " + sql);

		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bGroup));
			pstmt.setInt(2, Integer.parseInt(bStep));
			
			int rn = pstmt.executeUpdate();
			if (rn > 0) {
//		        System.out.println("reply INSERT 성공");
		        System.out.printf("replyShape Update %d개 성공\n", rn);
		    } else {
		        System.out.println("replyShape Update 실패");
		    }
		} catch (Exception e) {
			System.out.println("e.getMessage() --> " + e.getMessage());
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
		}
		
	}

	public void delete(String bId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("BDao delete start...");	
		
		String sql = "delete from mvc_board where bId = ?"; 

		System.out.println("sql --> " + sql);
		
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bId);
			int rn = pstmt.executeUpdate();
			if (rn > 0) {
		        System.out.println("delete 성공");
		    } else {
		        System.out.println("delete 실패");
		    }
			
		} catch (Exception e) {
			System.out.println("delete dataSource --> " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("e2.getMessage() --> " + e2.getMessage());
			}
			
		}
		
	}	
		
} //End Class
	
