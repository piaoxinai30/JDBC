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
		System.out.println("���Ῡ��: " + conn.isClosed());
		
		ConnectionHelper.close(conn);
		System.out.println("���Ῡ��: " + conn.isClosed());
		
		Connection conn2 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println("���Ῡ��: " + conn2.isClosed());
		
		ConnectionHelper.close(conn2);
		System.out.println("���Ῡ��: " + conn2.isClosed());
		
		conn2 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println("���Ῡ��: " + conn2.isClosed());
		
		ConnectionHelper.close(conn2);
		System.out.println("���Ῡ��: " + conn2.isClosed());
	
		//1.���ᰴü�ּ�: oracle.jdbc.driver.T4CConnection@7fac631b
		//2.���ᰴü�ּ�: oracle.jdbc.driver.T4CConnection@5b87ed94
		//�ϳ��� application �ε� ���� �����Ҷ����� ��ü�� �����ϰ� �װ��� ����ϴ� �� ���� �ٸ� �����?
		//�ϳ��� ��ü�� ����� �����ϴ� ���!!! >>
		//singleton :�ϳ��� ����� �� �ּҸ� ��� ������ ���� �� : �Ѱ��� ��ü�� ���� >> JDBC���� �����ϴ� ����� �ƴ�
		
		Connection conn3 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		//System.out.println("���Ῡ��: " + conn2.isClosed());
		//��ü����: oracle.jdbc.driver.T4CConnection@6e0e048a
		//ConnectionHelper.close(conn3); //������ �����ϸ� ���� Connection conn3 ��ü�� null�� ������?
		SingletonHelper.dbClose();
		//��ü(�ּ�)�� �ִµ� ������ ������ �ּ�
		
		//POINT
		//�Ʒ� ����Ǵ� �̱��� getConnection�� ���ο� ���� ��ü�� �޾ƿ��� �ϰ� �;�???
		//�׷� if�� Ÿ�� �ʰ� �ؾ��� >> conn�� null �� �Ǿ���!!
		
		Connection conn4 = null;
		conn2 = ConnectionHelper.getConnection("oracle","hr", "1004");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		//System.out.println("���Ῡ��: " + conn2.isClosed());
		//��ü����: conn is not null.. return :oracle.jdbc.driver.T4CConnection@5bc79255
		
		//�̷��� singleton�� ���� ������??????
		//DB���� ������ ��� �������� �ؾ��ϳ�?????
		
		
	}
	

}
