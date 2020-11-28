package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import controller.TableViewItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLDao {

	private static final String url = "jdbc:mariadb://localhost:3306/pocketmoney?";
	private static final String user = "root";
	private static final String password = "root";
	

	//現在のお小遣い残高照会
	public String selectMoney() throws SQLException {
		final String SUM_QUERY = "select"
							   + "(select sum(imcome) as imcomes_sum from imcomes)" //収入
							   + " - "												//-(マイナス)
							   + "(select sum(money) as pays_sum from pays);";		//支出
		String money = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {money =  rs.getString(1);} //残高の値を受け取る

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return money;
	}


	//imcomesテーブルへデータ挿入
	public static void insertRecord(LocalDate imcomed_at, String memo, int imcome) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO imcomes (imcomed_at, memo, imcome) VALUES (?, ?, ?)";
		
		try (Connection conn = DriverManager
				.getConnection(url, user, password);
				
				PreparedStatement ｐｓ = conn.prepareStatement(INSERT_QUERY)) {
			
			ｐｓ.setObject(1, imcomed_at);
			ｐｓ.setString(2, memo);
			ｐｓ.setInt(3, imcome);
			ｐｓ.executeUpdate();
			System.out.println(ｐｓ);
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//paysテーブルへデータ挿入
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager
				.getConnection(url, user, password);

			PreparedStatement ｐｓ = conn.prepareStatement(INSERT_QUERY)) {

				ｐｓ.setObject(1, paid_at);
				ｐｓ.setString(2, memo);
				ｐｓ.setInt(3, money);
				ｐｓ.setInt(4, category_id);
				ｐｓ.executeUpdate();
				System.out.println(ｐｓ);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}

	//Main画面で入力された金額を取得
	public String selectPayPrice() throws SQLException {
		final String SUM_QUERY = "SELECT money FROM pays ORDER BY id DESC LIMIT 1;";
		String newPrice = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {newPrice = rs.getString("money");} //値を受け取る
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return newPrice;
	}
	
	//AddImcome画面で入力された金額を取得
	public String selectIncomePrice() throws SQLException {
		final String SUM_QUERY = "SELECT imcome FROM imcomes ORDER BY id DESC LIMIT 1;";
		String newPrice = "";
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			if(rs.next()) {newPrice = rs.getString("imcome");} //値を受け取る

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return newPrice;
	}
	
	//”お小遣い”履歴画面のTableViewに表示させるデータの取得
	public ObservableList<TableViewItem> selectTableViewIncomes() throws SQLException {
		final String SUM_QUERY = "SELECT imcomed_at, memo, imcome FROM imcomes ORDER BY imcomed_at;";
		ObservableList<TableViewItem> list = FXCollections.observableArrayList();
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

			while(rs.next()){
				TableViewItem tbl = new TableViewItem();
				tbl.setImcomed_at(rs.getString("imcomed_at"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setImcome(rs.getString("imcome"));
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	//”支出”履歴画面のTableViewに表示させるデータの取得
	public ObservableList<TableViewItem> selectTableViewPays() throws SQLException {
		final String SUM_QUERY = "SELECT paid_at, category, memo, money FROM pays JOIN categorys ON pays.category_id = categorys.id ORDER BY paid_at;";
		ObservableList<TableViewItem> list = FXCollections.observableArrayList();
		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

			while(rs.next()){
				TableViewItem tbl = new TableViewItem();
				tbl.setPaid_at(rs.getString("paid_at"));
				tbl.setCategory(rs.getString("category"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setMoney(rs.getString("money"));
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
}
			