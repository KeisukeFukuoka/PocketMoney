package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * ImcomesReportControllerクラス 
 * お小遣い履歴画面のコントローラークラス
 */
public class ImcomesReportController implements Initializable{

	@FXML
	private TableView<TableViewItem>table;
	@FXML
	private Button HomeButton;

	/**
	 * initializeメソッド
	 * 初期化処理
	 * お小遣い履歴の表示
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//DaoクラスMySQLDaoメソッドからお小遣い履歴データの取得
		try {
			MySQLDao mysq = new MySQLDao();
			//TableViewに表示させる
			table.setItems(mysq.selectTableViewIncomes());
		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * onHomeButtonClikedメソッド
	 * ホームボタンが押された場合の処理
	 * ホーム画面へ遷移
	 */
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {

		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//ホーム画面へ画面遷移
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
			loader.setController(new MainController());
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("お小遣い管理アプリ");
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}