import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
DML( insert, update, delete)
jdbc작업
1.결과집합이 없다. >> resultSet이 필요 없어
2.그러면 insert, update, delete 결과를 어떻게 알아?
3.반영결과를 (반영된 행의 개수로) return 한다 
ex) JAVA코드
update emp set sal = 0  >> 실반영 된다면.. >> return 14
update emp set sal = 100 where empno = 9999 >> 실행은 되지만 >>return 0

 key point
 DB작업(sqlplus, DB developer)
 insert, update, delete >> transaction >> 반드시 commit/rollback을 강제해 전체를 반영하거나 전체를 취소

jdbc api 통해서 DML 작업 >> DML 작업은 default 가 autocommit 임
if java code를 통해서 delete from emp 실행하면 자동으로 commit되어 실제 반영됨
what if you make a mistake then? 
>>if you need commit, rollback, you can actually control it in JAVA code

start
	A계좌 인출(update...

	B계좌 입금(update...

end commit/ rollback 코드 작업 가능  
>>if you don't like this autocommit function, you can change the option 
                               >> false  >> 반드시 프로그램에서 commit, rollback

-----------------
create table dmlemp
as 
select * from emp where 1=2;

alter table dmlemp
add constraint pk_dmlemp_empno primary key(empno);

select*from user_constraints where table_name ='DMLEMP';
----------------------

 */
public class Ex03_Oracle_DML_update {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //이 드라이브를 new 해서 memory에 로드시킨다는 것 >> new driver
			System.out.println("오라클 드라이버 로딩");
			
			//1.db연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
			System.out.println(conn.isClosed() + " : False >> 연결");
			
			//2.명령생성(CRUD명령)
			stmt = conn.createStatement(); 
			
			//3.실행구문
			//입력받는 값을 insert
			
			int deptno =0;
			
			Scanner sc = new Scanner(System.in);
						System.out.println("부서번호 입력");
			deptno = Integer.parseInt(sc.nextLine());
			
			//update dmlemp set sal = 0 where deptno = 변수
			//we used to do in the past
			String sql = "update dmlemp set sal = 0 where deptno=" + deptno;
				
			
			//4.실행(결과 집합이 필요 없어..)
			int resultrow = stmt.executeUpdate(sql);
			//여기서 문제가 발생하면 바로 exception catch로 떨어짐.. >> if로 가지 않아..
			
			
			//5.처리
			if(resultrow > 0) 
			{System.out.println("반영된 행의 개수: " + resultrow);
			
			}else {
				//update발생시에는 조건에 맞는 결과가 없으면 0값을 반환한다 unlike insert >> 유의미하다
				//예외가 아니라, 정상적인 실행결과임
				System.out.println("update fail: " +resultrow);
			}
			
		}catch (Exception e) {
			System.out.println("insert fail: " + e.getMessage());
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			   e.printStackTrace();
			}
		}
	}

}
