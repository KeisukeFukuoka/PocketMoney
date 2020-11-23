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
	

	//現在のお小遣い残高照会
	public int selectMoney() throws SQLException {
		final String SUM_QUERY = "select"
							   + "(select sum(imcome) as imcomes_sum from imcomes)" //収入
							   + " - "												//-(マイナス)
							   + "(select sum(money) as pays_sum from pays);";		//支出
		int money = 0;
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {money =  rs.getInt(1);} //残高をint型で受け取る

		//try-with-resourcesステートメントによる例外処理の簡略化した記述
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println(money);
		return money;
	}


	//データベースへ挿入
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager
				.getConnection(url, user, password);

			PreparedStatement ｐｓ = conn.prepareStatement(INSERT_QUERY)) {

				ｐｓ.setObject(1, paid_at);
				ｐｓ.setString(2, memo);
				ｐｓ.setInt(3, money);
				ｐｓ.setInt(4, category_id);
	
				System.out.println(ｐｓ);
				ｐｓ.executeUpdate();

			//try-with-resourcesステートメントによる例外処理の簡略化した記述
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
