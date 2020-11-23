package controller;

import mysql.MySQLDao;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

public class MainController implements Initializable {
	@FXML
	private Button AddImcomeButton;
	@FXML
	private Button ImcomesReportButton;
	@FXML
	private Label MoneyLeftLabel;
	@FXML
	private DatePicker DatePicker;
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
	private TextField MemoTextField, PayMoneyTextField;
	@FXML
	private Button AddPayButton, PaysReportButton;

	private int catchNumber;




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//アプリ起動時にお小遣い残高の表示
		MySQLDao mysq = new MySQLDao();
		try {
			MoneyLeftLabel.setText(Integer.toString(mysq.selectMoney()));
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

	@FXML
	public void onAddPayButtonCliked(ActionEvent event) throws SQLException {

		//入力エラーの際にアラートを表示させる処理
		Window owner = AddPayButton.getScene().getWindow();

		System.out.println(DatePicker.getValue());
		System.out.println(PayMoneyTextField.getText());

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"日付を入力して下さい。");
			return;
		}
		if (PayMoneyTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"支出（円）を入力して下さい。");
			return;
		}
		if (catchNumber == 0) {
			showAlert(Alert.AlertType.ERROR, owner, "入力エラー!", null,
					"カテゴリーを選択して下さい。");
			return;
		}

		LocalDate paid_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stmoney = PayMoneyTextField.getText();
		int money = Integer.parseInt(stmoney);
		int category_id = catchNumber;

		MySQLDao.insertRecord(paid_at, memo, money, category_id);

		//すぐさまお小遣い残高に反映
		MySQLDao mysq = new MySQLDao();
		try {
			MoneyLeftLabel.setText(Integer.toString(mysq.selectMoney()));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String header, String message) {
		// TODO 自動生成されたメソッド・スタブ
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	@FXML
	void onImcomesReportButtonCliked(ActionEvent event) {

	}
}