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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * ImcomeDoneControllerクラス 
 * お小遣い入力完了画面のコントローラークラス
 */
public class ImcomeDoneController implements Initializable {
	
	@FXML
	private Label ImcomePriceLabel;
	@FXML
	private Button ImcomesReportButton;
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
			//Labelに表示させる
			ImcomePriceLabel.setText(mysq.selectIncomePrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onImcomesReportButtonClikedメソッド
	 * お小遣い履歴画面へのリンクボタン押された場合の処理
	 * 画面遷移
	 */
	@FXML
	private void onImcomesReportButtonCliked(ActionEvent event) {

		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//お小遣い履歴画面遷移
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/ImcomesReport.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("お小遣い履歴");
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
	private void onHomeButtonCliked(ActionEvent event) {
		
		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
	
		//ホーム画面へ遷移
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
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