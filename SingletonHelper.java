package kr.or.bit.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



	/*
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
	 */
public class SingletonHelper {
	//싱글톤 변환하기
	private static Connection conn = null;
	private SingletonHelper() {} //new SingletonHelper ...
	
	public static Connection getConnection(String dsn) {
		if( conn != null) {
			//POINT conn 객체가 null 값이 아니면 같은  conn  주소를  return .....
			System.out.println("conn is not null.. return : " + conn);
			return conn;
		}
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver");
				//DriverManager.getConnection 새로운 연결 객체를 생성하는 코드 
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
			}else if(dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitsqldb?useSSL=true","bituser","1004");
			}
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("conn is null  conn  객체 리턴 : " + conn);
		return conn;
	}
	
	public static void dbClose() {
		if(conn != null) {
			try {
				conn.close();
				conn = null; // Connection getConnection if를 타지 않고 새로운 객체..
			} catch (SQLException e) {
			   e.printStackTrace();
			}
		}
	}
}