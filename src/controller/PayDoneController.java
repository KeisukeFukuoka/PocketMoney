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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * PayDoneControllerクラス 
 * 支出入力完了画面のコントローラークラス
 */
public class PayDoneController implements Initializable{

	@FXML
	private Label PayMoneyLabel;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button PaysReportButton;
	@FXML
	private Button HomeButton;

	/**
	 * initializeメソッド
	 * 初期化処理
	 *　入力完了した金額を表示
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//DaoクラスMySQLDaoメソッドから直前の入力データを取得
		try {
			MySQLDao mysq = new MySQLDao();
			PayMoneyLabel.setText(mysq.selectPayPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onPaysReportButtonClikedメソッド
	 * 削除ボタン押された場合の処理
	 * ホーム画面へ遷移
	 */
	@FXML
	public void onDeleteButtonCliked(ActionEvent event) {
		
		//支出履歴画面へ遷移
		try {
			
		MySQLDao.deletePaysRecord();
		
		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
		loader.setController(new HomeController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("お小遣い管理アプリ");
		stage.setScene(scene);
		stage.show();
		}catch(IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onPaysReportButtonClikedメソッド
	 * 支出履歴画面へのリンクボタン押された場合の処理
	 * 支出履歴画面へ遷移
	 */
	@FXML
	public void onPaysReportButtonCliked(ActionEvent event) {
		
		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//支出履歴画面へ遷移
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/PaysReport.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("登録確認");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onHomeButtonClikedメソッド
	 * ホームボタンが押された場合の処理
	 * ホーム画面へ遷移
	 */
	@FXML
	public void onHomeButtonCliked(ActionEvent event) {
		
		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//ホーム画面へ遷移
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
			loader.setController(new HomeController());
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