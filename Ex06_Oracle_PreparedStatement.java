import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.or.bit.utils.ConnectionHelper;
import kr.or.bit.utils.SingletonHelper;

/*

Statement stmt = conn.createStatement();
String sql = "select empno, ename, sal from emp";
     >>sql ="select deptno, dname,from dept";
ResultSet rs = stmt.executeQuery(sql); 

advantages: query문을 바꿔 실행 가능(고정문 컬럼명, 테이블명, 조건)
dis : query문이 동일하면 성능 ok...but query문에 변화가 생기면 성능이 떨어짐

query문의 조건만 바뀌어도
sql = "select empno, ename, sal from emp where deptno = 10";
sql = "select empno, ename, sal from emp where deptno = 20";
		>> parameter만 다른 query.. 근데도 다르게 인지???
		
그래서.. 대안으로
select empno, ename, sal from emp where deptno = ?  라고 먼저 DB에서 가지고 있게하고
? parameter 부분만 바꿔서
select empno, ename, sal from emp where deptno = ? 라고 oracle 같은 query로 인정해줌
	PreparedStatement pstmt = conn.preparedStatement("select * from emp");
	쿼리문을 DB에 미리 보내서 컴파일 시켜놓고, 쿼리의 정보를 가지고 있는 객체를 리턴함 >> 실행
	pstmt.executeQuery();
		>>advantage???? >> 보안, 미리 쿼리문을 컴파일 시켜서 parameter만 설정하면 됨. 성능 좋아
		>>disadvantage??? >> 재사용성이 떨어짐..  >> However, 크게 문제 안됨
		
 */
public class Ex06_Oracle_PreparedStatement {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, deptno from emp where deptno =?";
			//where empno =? and ename = ? and sal > ?      >>> ?(물음표)가 parameter
			//where empno = 1000 and ename = 'king' and sal > 1000
			//? (믈음표)는 순서가 중요!!! 순서에 따라 값을 설정함
			pstmt = conn.prepareStatement(sql);
			//실행객체를 얻을떄 미리 쿼리문 보내서 그 객체정보 얻기
			//Statement stmt = conn.createStatement();
			
			//parameter 설정
			pstmt.setInt(1, 30);  //1번째 물음표에 30을 넣는다
								  //parameter만 바꿔서.. 항상 같은 쿼리고.. 구문분석은 실행 안함! 재사용함!!
			
			rs = pstmt.executeQuery();
			//rs = pstmt.executeQuery(sql);
			
			//************공식같은 로직//**********
			//데이터가 없는 경우
			//데이터가 1건인 경우
			//데이터가 2건 이상인 경우 ....   >> 모든 케이스를 해결해야함
			
			if(rs.next()) {
				do{
					System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
				}while(rs.next());
			}else {
				System.out.println("조회된 데이터가 없습니다"); //데이터 없는 경우
			}
			
			while(rs.next()) { 
				System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
			}
			
			
		}catch(Exception e) {
			
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
		}

	}

}
