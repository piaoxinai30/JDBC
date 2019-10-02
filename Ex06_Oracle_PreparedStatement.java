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

advantages: query���� �ٲ� ���� ����(������ �÷���, ���̺��, ����)
dis : query���� �����ϸ� ���� ok...but query���� ��ȭ�� ����� ������ ������

query���� ���Ǹ� �ٲ�
sql = "select empno, ename, sal from emp where deptno = 10";
sql = "select empno, ename, sal from emp where deptno = 20";
		>> parameter�� �ٸ� query.. �ٵ��� �ٸ��� ����???
		
�׷���.. �������
select empno, ename, sal from emp where deptno = ?  ��� ���� DB���� ������ �ְ��ϰ�
? parameter �κи� �ٲ㼭
select empno, ename, sal from emp where deptno = ? ��� oracle ���� query�� ��������
	PreparedStatement pstmt = conn.preparedStatement("select * from emp");
	�������� DB�� �̸� ������ ������ ���ѳ���, ������ ������ ������ �ִ� ��ü�� ������ >> ����
	pstmt.executeQuery();
		>>advantage???? >> ����, �̸� �������� ������ ���Ѽ� parameter�� �����ϸ� ��. ���� ����
		>>disadvantage??? >> ���뼺�� ������..  >> However, ũ�� ���� �ȵ�
		
 */
public class Ex06_Oracle_PreparedStatement {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, deptno from emp where deptno =?";
			//where empno =? and ename = ? and sal > ?      >>> ?(����ǥ)�� parameter
			//where empno = 1000 and ename = 'king' and sal > 1000
			//? (����ǥ)�� ������ �߿�!!! ������ ���� ���� ������
			pstmt = conn.prepareStatement(sql);
			//���ఴü�� ������ �̸� ������ ������ �� ��ü���� ���
			//Statement stmt = conn.createStatement();
			
			//parameter ����
			pstmt.setInt(1, 30);  //1��° ����ǥ�� 30�� �ִ´�
								  //parameter�� �ٲ㼭.. �׻� ���� ������.. �����м��� ���� ����! ������!!
			
			rs = pstmt.executeQuery();
			//rs = pstmt.executeQuery(sql);
			
			//************���İ��� ����//**********
			//�����Ͱ� ���� ���
			//�����Ͱ� 1���� ���
			//�����Ͱ� 2�� �̻��� ��� ....   >> ��� ���̽��� �ذ��ؾ���
			
			if(rs.next()) {
				do{
					System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
				}while(rs.next());
			}else {
				System.out.println("��ȸ�� �����Ͱ� �����ϴ�"); //������ ���� ���
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
