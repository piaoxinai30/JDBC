import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
JDBC
1. Java �� ���ؼ� Oracle ���� �ϰ� CRUD//insert-select-update-delete �۾�
2. ��� DB ����Ʈ���� ��� ���� (Oracle , mysql , ms-sql) 
2.1 ��ǰ�� �´� ����̹��ʿ� (�� ���� ����Ʈ���� �ٿ�ε� �޾Ƽ� ���)
2.2 ����Ŭ (���� PC ����Ŭ DB ���� ��ġ) >> ojdbc6.jar (����̹� ����)
C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib
//C:\SmartWeb\DataBase\JDBC\Connect_Utils\oracleDriver

3. Cmd ����� Java Project ������ ����̹� ����ϱ� ���ؼ� ���� 
3.1 java Build Path (jar �߰�) �ϴ� �۾�
3.2 ����̹� ����غ� �Ϸ�  >> ����̹� ����� �� �ֵ��� �޸� (new ..)
3.3 class.forName("class �̸�") >> new ������ ȿ�� 


�ڹ� 1.6 �̻���ʹ� 
Console ����� ������Ʈ���� ������Ʈ ���� ������ ��� ��� �����մϴ�
���񽺷δ�(http://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html)) ������� JDBC Driver�� �ڵ����� ��ϵ˴ϴ�.
�׷��� Class.forName("com.mysql.jdbc.Driver") ���� �ڵ带 ȣ������ �ʾƵ� �˴ϴ�. :)
*********************

4.JAVA ( JDBC API)
4.1 import java.sql.*; �����ϴ� �ڿ� (��κ��� �ڿ��� : interface , class)
4.2 �����ڴ� interface �� ���ؼ� �۾� ( �ñ��� : why interface �ϱ�?  hint)oracle �Ӹ� �ƴ϶� �پ��� DB ��� )

5. DB����  -> ��� -> ���� -> ó��  -> �ڿ�����
5.1 ��� (CRUD) : select , insert , update , delete
5.2 ó�� : select ȭ�� ����Ҳ���  �ƴϾ�  �� Ȯ�θ� ...........
5.3 �ڿ����� (����), otherwise db�� �Ƚ���..

*���� ���ڿ� (ConnectionString) ����
ä�� (client  ->  server �����ϱ� ���ؼ�)
��Ʈ��ũ DB (���� IP , PORT , SID(���� �����ͺ��̽� �̸�) , ���Ӱ��� , ���� ���)
   
*/

public class Ex01_Oracle_Connection {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
		System.out.println("����Ŭ ����̹� �ε�");
		
		//1.db����
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
		//getConnection : ���ο��� new Connection >> Connection �������̽��� �θ�� �ؼ� ����, ������ ��ü�� ����
		System.out.println(conn.isClosed() + " : False >> ����");
		//���� ��������? ��� ���� ��... �̶� false �� ���;� ����Ǵ� ��
		
		//2.��ɻ���(CRUD���)
		Statement stmt = conn.createStatement(); //��ɿ� �ʿ��� ��ü ���
		
		String sql = "select empno, ename, sal from emp";
		
		//3.��ɽ���(ó��) >>�����͸� ��ȸ�����ν�
		//select   vs.   insert, update, delete(�� 3���� ��� ������ ����)
		ResultSet rs = stmt.executeQuery(sql); //DB������ ������ �������(memory) �����ؼ� data read
		
		//4. ó��(��ȸ)
		while(rs.next()) { //next�� select�Ҷ� ��� ������ �ִ�? ����� ��
			//System.out.println(rs.getInt("empno") + "/" + rs.getString("ename") + "/" + rs.getInt("sal"));
			System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
		}
		
		//5. ��������
		rs.close();
		conn.close();
		System.out.println("DB���� : True" + conn.isClosed());
		
		
	}

}
