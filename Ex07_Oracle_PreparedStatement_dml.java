import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.or.bit.utils.ConnectionHelper;
import kr.or.bit.utils.SingletonHelper;

public class Ex07_Oracle_PreparedStatement_dml {
	public static void main(String[] args) {
		//insert into dmlemp(empno, ename, sal, deptno)
		//values(?,?,?,?)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql="delete from dmlemp where empno =?"; 
				pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 100);
			//pstmt.setString(2,"kim");
			//pstmt.setInt(2, 1000);
			//pstmt.setInt(4, 10);
			
			int row = pstmt.executeUpdate(); //insert, update, delete  >> excuteUpdate
			
			if(row > 0) {
				System.out.println("delete row count: " + row);
				
			}else {
				System.out.println("Fail");
			}
			
					
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(conn);
		}

	}

}
