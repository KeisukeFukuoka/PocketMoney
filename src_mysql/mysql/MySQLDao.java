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
	public String selectMoney() throws SQLException {
		final String SUM_QUERY = "select"
							   + "(select sum(imcome) as imcomes_sum from imcomes)" //����
							   + " - "												//-(�}�C�i�X)
							   + "(select sum(money) as pays_sum from pays);";		//�x�o
		String money = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {money =  rs.getString(1);} //�c���̒l���󂯎��

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return money;
	}


	//imcomes�e�[�u���փf�[�^�}��
	public static void insertRecord(LocalDate imcomed_at, String memo, int imcome) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO imcomes (imcomed_at, memo, imcome) VALUES (?, ?, ?)";
		
		try (Connection conn = DriverManager
				.getConnection(url, user, password);
				
				PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {
			
			����.setObject(1, imcomed_at);
			����.setString(2, memo);
			����.setInt(3, imcome);
			����.executeUpdate();
			System.out.println(����);
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//pays�e�[�u���փf�[�^�}��
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager
				.getConnection(url, user, password);

			PreparedStatement ���� = conn.prepareStatement(INSERT_QUERY)) {

				����.setObject(1, paid_at);
				����.setString(2, memo);
				����.setInt(3, money);
				����.setInt(4, category_id);
				����.executeUpdate();
				System.out.println(����);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}

	
	//AddImcome��ʂœ��͂��ꂽ���z���擾
	public String selectIncomePrice() throws SQLException {
		final String SUM_QUERY = "SELECT imcome FROM imcomes ORDER BY id DESC LIMIT 1;";
		String newPrice = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {newPrice = rs.getString("imcome");} //�l��int�^�Ŏ󂯎��

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return newPrice;
	}
	
	//Main��ʂœ��͂��ꂽ���z���擾
	public String selectPayPrice() throws SQLException {
		final String SUM_QUERY = "SELECT money FROM pays ORDER BY id DESC LIMIT 1;";
		String newPrice = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {newPrice = rs.getString("money");} //�l��int�^�Ŏ󂯎��
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return newPrice;
	}
}
