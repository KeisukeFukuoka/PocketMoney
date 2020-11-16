package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
	@FXML
	private void onReportButtonCliked(ActionEvent event) {
		
	}
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//イニシャライズ
	}
	
}