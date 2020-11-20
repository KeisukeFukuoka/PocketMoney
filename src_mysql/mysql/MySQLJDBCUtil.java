package mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJDBCUtil {

	public static Connection getConnection() throws SQLException {
		Connection con = null;

		try (FileInputStream f = new FileInputStream("/Users/USER/eclipse-workspace/PocketMoney/src_mysql/mysql/db.properties")) {
			
			
			//properties�t�@�C���̃��[�h
			Properties pros = new Properties();
			pros.load(f);
			
			//�f�[�^�x�[�X�p�����[�^�̎w��
			String url    	= pros.getProperty("url");
			String user     = pros.getProperty("user");
			String password = pros.getProperty("password");

			//�@STEP 1:�@�f�[�^�x�[�X�̐ڑ�
			con = DriverManager.getConnection(url,user,password);

		//try-with-resources�X�e�[�g�����g�ɂ���O�����̊ȗ��������L�q
		} catch(IOException e) {
			System.out.println("DB�ɃA�N�Z�X�o���܂���");
			e.printStackTrace();
		}
		return con;
	}
}
