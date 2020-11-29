package controller;

import mysql.MySQLDao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * MainControllerクラス 
 * ホーム画面のコントローラークラス
 */
public class MainController implements Initializable {

	//お小遣い表示
	@FXML
	private Button AddImcomeButton;
	@FXML
	private Button ImcomesReportButton;
	@FXML
	private Label MoneyLeftLabel;

	//入力フォーム
	@FXML
	private DatePicker DatePicker;
	@FXML
	private TextField MemoTextField;
	@FXML
	private TextField PayMoneyTextField;
	@FXML
	private ToggleGroup ToggleGroup;
	@FXML
	private ToggleButton FoodExpenses = new ToggleButton();
	@FXML
	private ToggleButton DailyNecessities = new ToggleButton();
	@FXML
	private ToggleButton SkillUp = new ToggleButton();
	@FXML
	private ToggleButton Other = new ToggleButton();
	@FXML
	private Button AddPayButton;
	@FXML
	private Button PaysReportButton;

	//トグルボタンが押されていない判断を保持。アラート表示処理に渡す。
	private int catchNumber;

	/**
	 * initializeメソッド
	 * 初期化処理
	 * 画面表示と同時にお小遣い残高の表示
	 * トグルボタンの監視
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//ホーム画面にお小遣い残高の表示
		try {
			MySQLDao mysq = new MySQLDao();
			MoneyLeftLabel.setText(mysq.selectMoney());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//トグルボタンへの値の割り当て
		FoodExpenses.setUserData(1);
		DailyNecessities.setUserData(2);
		SkillUp.setUserData(3);
		Other.setUserData(4);

		//どのトグルボタンが押されたか？または、どのトグルボタンも押されていないか？監視
		ToggleGroup.selectedToggleProperty().addListener(
			(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
				if (new_toggle != null)  //選択されていれば、
					catchNumber =((int) new_toggle.getUserData());  //該当のトグルボタンに応じた値を取り出す
				else 
					catchNumber = 0;  //どのトグルボタンも押されていない場合
		});
	}

	/**
	 * onAddImcomeButtonClikedメソッド
	 * お小遣い入力画面へのリンクボタン押された場合の処理
	 * 画面遷移
	 */
	@FXML
	void onAddImcomeButtonCliked(ActionEvent event) {

		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//お小遣い入力画面へ遷移
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/AddImcome.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("お小遣い登録");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onImcomesReportButtonClikedメソッド
	 * お小遣い履歴画面へのリンクボタン押された場合の処理
	 * 画面遷移
	 */
	@FXML
	void onImcomesReportButtonCliked(ActionEvent event) {

		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//お小遣い履歴画面へ遷移
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
	 * onAddImcomeButtonClikedメソッド
	 * 入力ボタンが押された場合の処理
	 * NULLチェック、アラート表示
	 * 入力データをDaoクラスへ渡す
	 * 入力完了画面へ遷移
	 */
	@FXML
	public void onAddPayButtonCliked(ActionEvent event) throws SQLException {

		//NULLの際にアラートを表示
		Window owner = AddPayButton.getScene().getWindow();

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"日付を入力して下さい。");
			return;
		}
		if (PayMoneyTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"金額を入力して下さい。");
			return;
		}
		if (catchNumber == 0) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"カテゴリーを選択して下さい。");
			return;
		}

		//入力内容を取得し、Daoクラスへ渡す
		LocalDate paid_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stmoney = PayMoneyTextField.getText();
		int money = Integer.parseInt(stmoney);
		int category_id = catchNumber;
		MySQLDao.insertRecord(paid_at, memo, money, category_id);

		//現在表示されている画面を閉じる
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//入力完了画面へ遷移
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/PayDone.fxml"));
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
		// TODO 自動生成されたメソッド・スタブ
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
	
	/**
	 * onPaysReportButtonClikedメソッド
	 * 支出履歴画面へのリンクボタン押された場合の処理
	 * 支出履歴画面へ遷移
	 */
	@FXML
	void onPaysReportButtonCliked(ActionEvent event) {
		
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
}