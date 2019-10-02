import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
DML( insert, update, delete)
jdbc�۾�
1.��������� ����. >> resultSet�� �ʿ� ����
2.�׷��� insert, update, delete ����� ��� �˾�?
3.�ݿ������ (�ݿ��� ���� ������) return �Ѵ� 
ex) JAVA�ڵ�
update emp set sal = 0  >> �ǹݿ� �ȴٸ�.. >> return 14
update emp set sal = 100 where empno = 9999 >> ������ ������ >>return 0

 key point
 DB�۾�(sqlplus, DB developer)
 insert, update, delete >> transaction >> �ݵ�� commit/rollback�� ������ ��ü�� �ݿ��ϰų� ��ü�� ���

jdbc api ���ؼ� DML �۾� >> DML �۾��� default �� autocommit ��
if java code�� ���ؼ� delete from emp �����ϸ� �ڵ����� commit�Ǿ� ���� �ݿ���
what if you make a mistake then? 
>>if you need commit, rollback, you can actually control it in JAVA code

start
	A���� ����(update...

	B���� �Ա�(update...

end commit/ rollback �ڵ� �۾� ����  
>>if you don't like this autocommit function, you can change the option 
                               >> false  >> �ݵ�� ���α׷����� commit, rollback

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
			Class.forName("oracle.jdbc.OracleDriver"); //�� ����̺긦 new �ؼ� memory�� �ε��Ų�ٴ� �� >> new driver
			System.out.println("����Ŭ ����̹� �ε�");
			
			//1.db����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bituser","1004");
			System.out.println(conn.isClosed() + " : False >> ����");
			
			//2.��ɻ���(CRUD���)
			stmt = conn.createStatement(); 
			
			//3.���౸��
			//�Է¹޴� ���� insert
			
			int deptno =0;
			
			Scanner sc = new Scanner(System.in);
						System.out.println("�μ���ȣ �Է�");
			deptno = Integer.parseInt(sc.nextLine());
			
			//update dmlemp set sal = 0 where deptno = ����
			//we used to do in the past
			String sql = "update dmlemp set sal = 0 where deptno=" + deptno;
				
			
			//4.����(��� ������ �ʿ� ����..)
			int resultrow = stmt.executeUpdate(sql);
			//���⼭ ������ �߻��ϸ� �ٷ� exception catch�� ������.. >> if�� ���� �ʾ�..
			
			
			//5.ó��
			if(resultrow > 0) 
			{System.out.println("�ݿ��� ���� ����: " + resultrow);
			
			}else {
				//update�߻��ÿ��� ���ǿ� �´� ����� ������ 0���� ��ȯ�Ѵ� unlike insert >> ���ǹ��ϴ�
				//���ܰ� �ƴ϶�, �������� ��������
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
