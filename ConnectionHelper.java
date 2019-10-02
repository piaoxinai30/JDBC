package kr.or.bit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
  Class.forName("oracle.jdbc.OracleDriver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
  >>��� CRUD ���� �۾��� �� �ڵ� �ʼ�
  
  1.�ݺ����� �۾��� ������(����̹� �ε�, ���ᰴü, ���... �ڿ�����) >>���� ���̴� �۾�
  2.�ٸ� �����̳� �ٸ� DB�����Ҷ� ���...?
  
 */


public class ConnectionHelper {
	//���(����....) >> �Լ� >> static >> overloading >> interface
	
	public static Connection getConnection(String dsn) { //oracle, mysql
		Connection conn = null;
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
				  
			}else if(dsn.equals("mysql")) {
				Class.forName("oracle.mysql.jdbc.Driver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
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
				Class.forName("oracle.jdbc.OracleDriver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", id, pwd);
				  
			}else if(dsn.equals("mysql")) {
				Class.forName("oracle.mysql.jdbc.Driver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
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
	public static void close(Connection conn) {//��ü�� �ּҰ��� �޾Ƽ�..
		if(conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
	public static void close(ResultSet rs) {//��ü�� �ּҰ��� �޾Ƽ�..
		if(rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
	public static void close(Statement stmt) {//��ü�� �ּҰ��� �޾Ƽ�..
		if(stmt != null) {
		try {
			stmt.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
	public static void close(PreparedStatement pstmt) {//��ü�� �ּҰ��� �޾Ƽ�..
		if(pstmt != null) {
		try {
			pstmt.close();
		} catch (SQLException e) {
		   e.printStackTrace();
		}
	}
	}
}
