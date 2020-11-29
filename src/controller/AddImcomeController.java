package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * AddImcomeControllerクラス 
 * お小遣い入力画面のコントローラークラス
 */
public class AddImcomeController implements Initializable{
	//入力フォーム
	@FXML
	private javafx.scene.control.DatePicker DatePicker; //Alart処理内のNULLチェックの為。DatePicker→javafx.scene.controlへ変更
	@FXML
	private TextField MemoTextField;
	@FXML
	private TextField ImcomePriceTextField;
	@FXML
	private Button AddImcomeButton;
	//ホームボタン
	@FXML
	private Button HomeButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * onAddImcomeButtonClikedメソッド
	 * 入力ボタンが押された場合の処理
	 * NULLチェック、アラート表示
	 * 入力データをDaoクラスへ渡す
	 * 入力完了画面へ遷移
	 */
	@FXML
	private void onAddImcomeButtonCliked(ActionEvent event) throws SQLException {

		//NULLの際にアラートを表示させる処理
		Window owner = AddImcomeButton.getScene().getWindow();

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"日付を入力して下さい。");
			return;
		}
		if (ImcomePriceTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"金額を入力して下さい。");
			return;
		}

		//入力内容を取得し、Daoクラスへ渡す
		LocalDate imcomed_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stimcome = ImcomePriceTextField.getText();
		int imcome = Integer.parseInt(stimcome);
		MySQLDao.insertRecord(imcomed_at, memo, imcome);

		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//入力完了画面へ遷移
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/ImcomeDone.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("登録完了");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * showAlertメソッド
	 * アラート表示内容定義
	 */
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String header, String message) {

		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
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