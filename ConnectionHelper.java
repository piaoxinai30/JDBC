package kr.or.bit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
  Class.forName("oracle.jdbc.OracleDriver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
  >>모든 CRUD 선행 작업시 위 코드 필수
  
  1.반복적인 작업을 줄이자(드라이버 로딩, 연결객체, 명령... 자원해제) >>많이 쓰이는 작업
  2.다른 계정이나 다른 DB연결할때 어떻게...?
  
 */


public class ConnectionHelper {
	//기능(연결....) >> 함수 >> static >> overloading >> interface
	
	public static Connection getConnection(String dsn) { //oracle, mysql
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
				  
			}else if(dsn.equals("mysql")) {
				Class.forName("oracle.mysql.jdbc.Driver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306:xe/bitsqldb?useSSL =true","bituser", "1004");
				
			}
		}catch (Exception e) {
			
		}
		return conn;
		
	}
	
	public static Connection getConnection(String dsn, String id, String pwd) { //oracle, mysql
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", id, pwd);
				  
			}else if(dsn.equals("mysql")) {
				Class.forName("oracle.mysql.jdbc.Driver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306:xe/bitsqldb?useSSL =true", id, pwd);
				
			}
		}catch (Exception e) {
			
		}
		return conn;
		
	}
	/*
	 try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			   e.printStackTrace();
			}
	 */
	public static void close(Connection conn) {//객체의 주소값을 받아서..
		if(conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
	public static void close(ResultSet rs) {//객체의 주소값을 받아서..
		if(rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
	public static void close(Statement stmt) {//객체의 주소값을 받아서..
		if(stmt != null) {
		try {
			stmt.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
	public static void close(PreparedStatement pstmt) {//객체의 주소값을 받아서..
		if(pstmt != null) {
		try {
			pstmt.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
}
