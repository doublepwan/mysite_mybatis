package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList() {
		List<BoardVo> list = sqlSession.selectList("board.getList");
		return list;
	}

	public BoardVo getOne(String no) {
		
		return sqlSession.selectOne("board.getOne", no);
	}

	public void hitCount(String no) {
		sqlSession.update("board.hitUp", no);
	}

	public void insert(BoardVo boardVo) {
		sqlSession.insert("board.insert", boardVo);
	}

	public void update(BoardVo boardVo) {
		sqlSession.update("board.update", boardVo);
	}

	public void delete(String no) {
		sqlSession.delete("board.delete", no);
	}
	
//	
//	//전체 레코드수 얻기용
//		public int getTotalRowCount(Map map){
//			int totalRecordCount = 0;
//			Connection conn = null;
//			PreparedStatement psmt = null;
//			ResultSet rs = null;
//			
//			try {
//				// JDBC 드라이버(Oracle)로딩
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//
//				// Connection 얻어오기
//				String url = "jdbc:oracle:thin:@localhost:1521:xe";
//				conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//				// sql문 준비 / 바인딩 / 실행
//				String query =  "select count(*) from board ";
//
//				psmt = conn.prepareStatement(query);
//				rs= psmt.executeQuery();
//				rs.next();
//				totalRecordCount = rs.getInt(1);
//
//			} catch (ClassNotFoundException e) {
//				System.out.println("드라이버 로딩 실패 : " + e);
//			} catch (SQLException e) {
//				System.out.println("error : " + e);
//			} finally {
//				try {
//					if (psmt != null) {
//						psmt.close();
//					}
//					if (conn != null) {
//						conn.close();
//					}
//				} catch (SQLException e) {
//					System.out.println("error : " + e);
//				}
//			}
//			return totalRecordCount;
//		}////////////////////////////////////////
//
//	public void insert(BoardVo dto) {
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		try {
//			// JDBC 드라이버(Oracle)로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// Connection 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//			// sql문 준비 / 바인딩 / 실행
//			String query = "";
//			query = " insert into board(no,title,content,reg_date,hit,user_no) " +
//					" values (seq_board_no.nextval, ?, ?, default, default, ?) ";
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, dto.getTitle());
//			psmt.setString(2, dto.getContent());
//			psmt.setString(3, dto.getUserNo());
//			int count = psmt.executeUpdate();
//
//			// 결과처리
//			System.out.println(count + "건 저장완료.");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패 : " + e);
//		} catch (SQLException e) {
//			System.out.println("error : " + e);
//		} finally {
//			try {
//				if (psmt != null) {
//					psmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error : " + e);
//			}
//		}
//	}
//
//	public void delete(String no) {
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		try {
//			// JDBC 드라이버(Oracle)로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// Connection 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//			// sql문 준비 / 바인딩 / 실행
//			String query = "delete from board where no = ? ";
//
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, no);
//
//			int count = psmt.executeUpdate();
//
//			// 결과처리
//			System.out.println(count + "건 삭제완료.");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패 : " + e);
//		} catch (SQLException e) {
//			System.out.println("error : " + e);
//		} finally {
//			try {
//				if (psmt != null) {
//					psmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error : " + e);
//			}
//		}
//
//	}
//	
//	public void update(BoardVo vo) {
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		
//		try {
//			// JDBC 드라이버(Oracle)로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// Connection 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//			// sql문 준비 / 바인딩 / 실행
//			String query = "update board set title = ?, content = ? " + 
//						   "where no = ?";
//			
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, vo.getTitle());
//			psmt.setString(2, vo.getContent());
//			psmt.setString(3, vo.getNo());
//
//			int count = psmt.executeUpdate();
//
//			// 결과처리
//			System.out.println(count + "건 수정완료.");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패 : " + e);
//		} catch (SQLException e) {
//			System.out.println("error : " + e);
//		} finally {
//			try {
//				if (psmt != null) {
//					psmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error : " + e);
//			}
//		}
//
//	}
//	
}
