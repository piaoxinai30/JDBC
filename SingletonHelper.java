package kr.or.bit.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



	/*
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
	 */
public class SingletonHelper {
	//�̱��� ��ȯ�ϱ�
	private static Connection conn = null;
	private SingletonHelper() {} //new SingletonHelper ...
	
	public static Connection getConnection(String dsn) {
		if( conn != null) {
			//POINT conn ��ü�� null ���� �ƴϸ� ����  conn  �ּҸ�  return .....
			System.out.println("conn is not null.. return : " + conn);
			return conn;
		}
		try {
			if(dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver");
				//DriverManager.getConnection ���ο� ���� ��ü�� �����ϴ� �ڵ� 
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
			}else if(dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bitsqldb?useSSL=true","bituser","1004");
			}
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("conn is null  conn  ��ü ���� : " + conn);
		return conn;
	}
	
	public static void dbClose() {
		if(conn != null) {
			try {
				conn.close();
				conn = null; // Connection getConnection if�� Ÿ�� �ʰ� ���ο� ��ü..
			} catch (SQLException e) {
			   e.printStackTrace();
			}
		}
	}
}