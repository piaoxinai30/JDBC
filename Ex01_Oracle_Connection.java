import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
JDBC
1. Java 를 통해서 Oracle 연결 하고 CRUD//insert-select-update-delete 작업
2. 어떤한 DB 소프트웨어 사용 결정 (Oracle , mysql , ms-sql) 
2.1 제품에 맞는 드라이버필요 (각 벤더 사이트에서 다운로드 받아서 사용)
2.2 오라클 (로컬 PC 오라클 DB 서버 설치) >> ojdbc6.jar (드라이버 파일)
C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib
//C:\SmartWeb\DataBase\JDBC\Connect_Utils\oracleDriver

3. Cmd 기반의 Java Project 에서는 드라이버 사용하기 위해서 참조 
3.1 java Build Path (jar 추가) 하는 작업
3.2 드라이버 사용준비 완료  >> 드라이버 사용할 수 있도록 메모리 (new ..)
3.3 class.forName("class 이름") >> new 동일한 효과 


자바 1.6 이상부터는 
Console 기반의 프로젝트에서 프로젝트 참조 만으로 등록 사용 가능합니다
서비스로더(http://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html)) 기반으로 JDBC Driver가 자동으로 등록됩니다.
그래서 Class.forName("com.mysql.jdbc.Driver") 류의 코드를 호출하지 않아도 됩니다. :)
*********************

4.JAVA ( JDBC API)
4.1 import java.sql.*; 제공하는 자원 (대부분의 자원은 : interface , class)
4.2 개발자는 interface 를 통해서 작업 ( 궁긍증 : why interface 일까?  hint)oracle 뿐만 아니라 다양한 DB 사용 )

5. DB연결  -> 명령 -> 실행 -> 처리  -> 자원해제
5.1 명령 (CRUD) : select , insert , update , delete
5.2 처리 : select 화면 출력할꺼야  아니야  난 확인만 ...........
5.3 자원해제 (성능), otherwise db가 안써졍..

*연결 문자열 (ConnectionString) 설정
채팅 (client  ->  server 연결하기 위해서)
네트워크 DB (서버 IP , PORT , SID(전역 데이터베이스 이름) , 접속계정 , 접속 비번)
   
*/

public class Ex01_Oracle_Connection {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
		System.out.println("오라클 드라이버 로딩");
		
		//1.db연결
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
		//getConnection : 내부에서 new Connection >> Connection 인터페이스를 부모로 해서 생성, 생성된 객체를 리턴
		System.out.println(conn.isClosed() + " : False >> 연결");
		//연결 끊어졌니? 라고 묻는 것... 이때 false 가 나와야 실행되는 것
		
		//2.명령생성(CRUD명령)
		Statement stmt = conn.createStatement(); //명령에 필요한 객체 얻기
		
		String sql = "select empno, ename, sal from emp";
		
		//3.명령실행(처리) >>데이터를 조회함으로써
		//select   vs.   insert, update, delete(이 3개는 결과 집합이 없다)
		ResultSet rs = stmt.executeQuery(sql); //DB서버에 생성된 결과집합(memory) 접근해서 data read
		
		//4. 처리(조회)
		while(rs.next()) { //next는 select할때 결과 집합이 있니? 물어보는 것
			//System.out.println(rs.getInt("empno") + "/" + rs.getString("ename") + "/" + rs.getInt("sal"));
			System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
		}
		
		//5. 연결해제
		rs.close();
		conn.close();
		System.out.println("DB연결 : True" + conn.isClosed());
		
		
	}

}
