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

public class ImcomesReportController {
	
	@FXML
	private TableView<ImcomeDatabase>/*なぜ警告*/ table;	//テーブルビュー
	@FXML
	private TableColumn Imcomed_atColumn;			//日付カラム
	@FXML
	private TableColumn MemoColumn;				//メモカラム
	@FXML
	private TableColumn ImcomeColumn;				//金額カラム
	@FXML
	private Button HomeButton;					//ホームボタン
	
	
	//テーブルに表示するID、メモ、金額の3つを必ず含む型として後ほどImcomeDatabaseクラスを定義
	@FXML
	public void onReportButtonCliked(ActionEvent event_r) {
		
	}
	@FXML
	public void onHomeButtonCliked(ActionEvent event_h) {
		
	}
	
	
}