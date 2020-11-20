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
			
			
			//propertiesファイルのロード
			Properties pros = new Properties();
			pros.load(f);
			
			//データベースパラメータの指定
			String url    	= pros.getProperty("url");
			String user     = pros.getProperty("user");
			String password = pros.getProperty("password");

			//　STEP 1:　データベースの接続
			con = DriverManager.getConnection(url,user,password);

		//try-with-resourcesステートメントによる例外処理の簡略化した記述
		} catch(IOException e) {
			System.out.println("DBにアクセス出来ません");
			e.printStackTrace();
		}
		return con;
	}
}
