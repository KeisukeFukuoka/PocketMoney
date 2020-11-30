package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import controller.TableViewProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLDao {

	private static final String url = "jdbc:mariadb://localhost:3306/pocketmoney?";
	private static final String user = "root";
	private static final String password = "root";
	
	//現在のお小遣い残高照会
	public String selectMoney() throws SQLException {
		final String SUM_QUERY = "SELECT"
							   + "(SELECT SUM(imcome) AS imcomes_sum FROM imcomes)" //収入
							   + " - "												//-(マイナス)
							   + "(SELECT SUM(money) AS pays_sum FROM pays);";		//支出
		//取得結果を格納するString変数
		String money = "";
		
		//例外処理
		try (
	         //データベース接続
			 Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
		     //ResultSetからString変数へセット
			 if(rs.next()) {money =  rs.getString(1);}

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//呼び出し元へ返す
		return money;
	}

	//imcomesテーブルへデータ挿入
	public static void insertRecord(LocalDate imcomed_at, String memo, int imcome) throws SQLException {
		
		//SQL文はカラム名にプレースホルダーを代入する形で準備
		final String INSERT_QUERY = "INSERT INTO imcomes (imcomed_at, memo, imcome) "
				                  + "VALUES (?, ?, ?)";
		//例外処理
		try (
            //データベース接続
            Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {
			
			//入力フォームからのパラメータをプレースホルダにバインドし、データベースへ挿入
			ps.setObject(1, imcomed_at);
			ps.setString(2, memo);
			ps.setInt(3, imcome);
			ps.executeUpdate();
			System.out.println(ps);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//paysテーブルへデータ挿入
	public static void insertRecord(LocalDate paid_at, String memo, int money, int category_id) throws SQLException {
		
		//SQL文はカラム名にプレースホルダーを代入する形で準備
		final String INSERT_QUERY = "INSERT INTO pays (paid_at, memo, money, category_id) "
				                  + "VALUES (?, ?, ?, ?)";
		//例外処理
		try (
	            //データベース接続
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ｐｓ = conn.prepareStatement(INSERT_QUERY)) {

			    //入力フォームからのパラメータをプレースホルダにバインドし、データベースへ挿入
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
	
	//imcomesテーブル直前データ削除
	public static void deleteImcomesRecord() throws SQLException {
		
		//SQL文はカラム名にプレースホルダーを代入する形で準備
		final String INSERT_QUERY = "DELETE FROM imcomes "
								  + "ORDER BY id DESC "
								  + "LIMIT 1;";
		//例外処理
		try (
				//データベース接続
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ｐｓ = conn.prepareStatement(INSERT_QUERY)) {
			
			ｐｓ.executeUpdate();
			System.out.println(ｐｓ);
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//paysテーブル直前データ削除
	public static void deletePaysRecord() throws SQLException {
		
		//SQL文はカラム名にプレースホルダーを代入する形で準備
		final String INSERT_QUERY = "DELETE FROM pays "
				                  + "ORDER BY id DESC "
				                  + "LIMIT 1;";
		//例外処理
		try (
	            //データベース接続
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement ｐｓ = conn.prepareStatement(INSERT_QUERY)) {

				ｐｓ.executeUpdate();
				System.out.println(ｐｓ);

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Main画面で入力された金額を取得
	public String selectPayPrice() throws SQLException {
		final String SUM_QUERY = "SELECT money "
						 	   + "FROM pays "
							   + "ORDER BY id DESC "
							   + "LIMIT 1;";
					
		//取得結果を格納するString変数
		String newPrice = "";
		
		//例外処理
		try (
	            //データベース接続
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
			    //ResultSetからString変数へセット
			    if(rs.next()) {newPrice = rs.getString("money");}
			
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//呼び出し元へ返す
		return newPrice;
	}
	
	//AddImcome画面で入力された金額を取得
	public String selectIncomePrice() throws SQLException {
		final String SUM_QUERY = "SELECT imcome "
							   + "FROM imcomes "
							   + "ORDER BY id DESC "
							   + "LIMIT 1;";
		
		//取得結果を格納するString変数
		String newPrice = "";
		
		//例外処理
		try (
	         //データベース接続
			 Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
		    //ResultSetからString変数へセット
			 if(rs.next()) {newPrice = rs.getString("imcome");}

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//呼び出し元へ返す
		return newPrice;
	}
	
	//”お小遣い”履歴画面のTableViewに表示させるデータの取得
	public ObservableList<TableViewProperty> selectTableViewIncomes() throws SQLException {
		final String SUM_QUERY = "SELECT imcomed_at, memo, imcome "
							   + "FROM imcomes "
							   + "ORDER BY imcomed_at;";
		
        //プロパティクラスのインスタンスを格納するlist
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
		//例外処理
		try (
	            //データベース接続
				Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SUM_QUERY)) {
			
            //ResultSetからプロパティクラスのフィールドへセット
			while(rs.next()){
				
				//プロパティクラスのインスタンス生成
				TableViewProperty tbl = new TableViewProperty();
				
                //フィールドにセット
				tbl.setImcomed_at(rs.getString("imcomed_at"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setImcome(rs.getString("imcome"));
				//インスタンスを格納
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//呼び出し元へ返す
		return list;
	}
	
	//”支出”履歴画面のTableViewに表示させるデータの取得
	public ObservableList<TableViewProperty> selectTableViewPays() throws SQLException {
		final String SUM_QUERY = "SELECT paid_at, category, memo, money "
							   + "FROM pays "
							   + "JOIN categorys "
							   + "ON pays.category_id = categorys.id "
							   + "ORDER BY paid_at;";
		
        //プロパティクラスのインスタンスを格納するlist
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		
        //例外処理
        try (
            //データベース接続
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SUM_QUERY)) {

            //ResultSetからプロパティクラスのフィールドへセット
			while(rs.next()){
				
				//プロパティクラスのインスタンス生成
				TableViewProperty tbl = new TableViewProperty();
                //フィールドにセット
				tbl.setPaid_at(rs.getString("paid_at"));
				tbl.setCategory(rs.getString("category"));
				tbl.setMemo(rs.getString("memo"));
				tbl.setMoney(rs.getString("money"));
				//インスタンスを格納
				list.add(tbl);
			}
		}catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		//呼び出し元へ返す
		return list;
	}
	
	/**
	 * searchTableViewメソッド
	 * 文字列の引数を受け取り、どの検索条件か判別
	 * 対応するSOLとバインド
	 * クエリ実行し、結果をObservableList型listで返す
	 */
	public ObservableList<TableViewProperty> searchTableView(String search) throws SQLException {
		ObservableList<TableViewProperty> list = FXCollections.observableArrayList();
		String SERACH_QUERY = null;
		
		//それぞれ選択された検索ワードに応じて、SQL発行
		switch(search) {
		
		case "日ごとにいくら使ったか":
			SERACH_QUERY = "SELECT paid_at, "
						 + "SUM(money) "
						 + "FROM pays "
						 + "GROUP BY paid_at;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setPaid_at(rs.getString("paid_at"));
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "一回当たり平均額":
			SERACH_QUERY = "SELECT AVG(money) "
						 + "FROM pays;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("AVG(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;

		case "3000円以上の支出":
			SERACH_QUERY = "SELECT paid_at, category, memo, money "
						 + "FROM pays "
						 + "JOIN categorys "
						 + "ON pays.category_id = categorys.id "
						 + "WHERE money > 3000;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setPaid_at(rs.getString("paid_at"));
					tbl.setCategory(rs.getString("category"));
					tbl.setMemo(rs.getString("memo"));
					tbl.setMoney(rs.getString("money"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		
		case "カテゴリーごとの合計額":
			SERACH_QUERY = "SELECT category, "
						 + "SUM(money) "
						 + "FROM pays "
						 + "JOIN categorys "
						 + "ON pays.category_id = categorys.id "
						 + "GROUP BY category_id;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setCategory(rs.getString("category"));
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;

		case "今月の合計額":
			SERACH_QUERY = "SELECT SUM(money) "
						 + "FROM pays "
						 + "WHERE paid_at > "
						 + "DATE_SUB(NOW(), INTERVAL 1 MONTH) "
						 + "ORDER BY paid_at ASC;";
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "総合計額":
			SERACH_QUERY = "SELECT SUM(money) "
					     + "FROM pays;";
			
			try (Connection conn = DriverManager.getConnection(url, user, password);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SERACH_QUERY)) {
				
				while(rs.next()){
					TableViewProperty tbl = new TableViewProperty();
					tbl.setMoney(rs.getString("SUM(money)"));
					list.add(tbl);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		return list;
	}
}
			