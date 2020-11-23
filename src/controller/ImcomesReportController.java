package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ImcomesReportController implements Initializable{
	
	@FXML
	private TableView<?>/*なぜ警告*/ table;	//テーブルビュー
	@FXML
	private TableColumn<?, ?> Imcomed_atColumn;			//日付カラム
	@FXML
	private TableColumn<?, String> MemoColumn;				//メモカラム
	@FXML
	private TableColumn<?, Integer> ImcomeColumn;				//金額カラム
	@FXML
	private Button HomeButton;					//ホームボタン
	
	//テーブルに表示するID、メモ、金額の3つを必ず含む型として後ほどImcomeDatabaseクラスを定義

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//イニシャライズ
	}
	
	
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
		/*
		 * 現在表示されている画面を閉じる
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
	
		//画面遷移
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