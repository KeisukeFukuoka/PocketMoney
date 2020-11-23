package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MySQLDao {

	private static final String url = "jdbc:mariadb://localhost:3306/pocketmoney?";
	private static final String user = "root";
	private static final String password = "root";
	

	//���݂̂��������c���Ɖ�
	public int selectMoney() throws SQLException {
		final String SUM_QUERY = "select"
							   + "(select sum(imcome) as imcomes_sum from imcomes)" //����
							   + " - "												//-(�}�C�i�X)
							   + "(select sum(money) as pays_sum from pays);";		//�x�o
		int money = 0;
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {money =  rs.getInt(1);} //�c����int�^�Ŏ󂯎��

		//try-with-resources�X�e�[�g�����g�ɂ���O�����̊ȗ��������L�q
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(money);
		return money;
	}


	//�f�[�^�x�[�X�֑}��
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager
				.getConnection(url, user, password);

			PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {

				����.setObject(1, paid_at);
				����.setString(2, memo);
				����.setInt(3, money);
				����.setInt(4, category_id);
	
				System.out.println(����);
				����.executeUpdate();

			//try-with-resources�X�e�[�g�����g�ɂ���O�����̊ȗ��������L�q
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
