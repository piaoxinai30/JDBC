import java.sql.Connection;
import java.sql.SQLException;

import kr.or.bit.utils.ConnectionHelper;
import kr.or.bit.utils.SingletonHelper;

public class Ex05_ConnectionHelper {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		conn = ConnectionHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println("연결여부: " + conn.isClosed());
		
		ConnectionHelper.close(conn);
		System.out.println("연결여부: " + conn.isClosed());
		
		Connection conn2 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println("연결여부: " + conn2.isClosed());
		
		ConnectionHelper.close(conn2);
		System.out.println("연결여부: " + conn2.isClosed());
		
		conn2 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println("연결여부: " + conn2.isClosed());
		
		ConnectionHelper.close(conn2);
		System.out.println("연결여부: " + conn2.isClosed());
	
		//1.연결객체주소: oracle.jdbc.driver.T4CConnection@7fac631b
		//2.연결객체주소: oracle.jdbc.driver.T4CConnection@5b87ed94
		//하나의 application 인데 굳이 연결할때마다 객체를 생성하고 그것을 사용하는 것 말고 다른 방법은?
		//하나의 객체를 만들고 공유하는 방법!!! >>
		//singleton :하나를 만들어 그 주소를 계속 가져다 쓰는 것 : 한개의 객체를 재사용 >> JDBC에서 권장하는 방법은 아님
		
		Connection conn3 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		//System.out.println("연결여부: " + conn2.isClosed());
		//객체리턴: oracle.jdbc.driver.T4CConnection@6e0e048a
		//ConnectionHelper.close(conn3); //연결을 해제하면 과연 Connection conn3 객체가 null을 갖을까?
		SingletonHelper.dbClose();
		//객체(주소)는 있는데 연결이 끊어진 주소
		
		//POINT
		//아래 실행되는 싱글톤 getConnection이 새로운 연결 객체를 받아오게 하고 싶어???
		//그럼 if를 타지 않게 해야함 >> conn이 null 이 되야함!!
		
		Connection conn4 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		//System.out.println("연결여부: " + conn2.isClosed());
		//객체리턴: conn is not null.. return :oracle.jdbc.driver.T4CConnection@5bc79255
		
		//이렇게 singleton을 사용시 문제점??????
		//DB연결 해제는 어느 시점에서 해야하나?????
		
		
	}
	

}
