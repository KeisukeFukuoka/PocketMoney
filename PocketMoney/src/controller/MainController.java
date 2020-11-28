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

		//�A�v���N�����ɂ��������c���̕\��
		try {
			MySQLDao mysq = new MySQLDao();
			MoneyLeftLabel.setText(mysq.selectMoney());
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		
		//�g�O���{�^���ւ̒l�̊��蓖��
		FoodExpenses.setUserData(1);
		DailyNecessities.setUserData(2);
		SkillUp.setUserData(3);
		Other.setUserData(4);

		//�ǂ̃g�O���{�^���������ꂽ���H�܂��́A�ǂ̃g�O���{�^����������Ă��Ȃ����H�Ď�
		ToggleGroup.selectedToggleProperty().addListener(
				(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
					if (new_toggle != null)  //�I������Ă���΁A
						catchNumber =((int) new_toggle.getUserData());  //�Y���̃g�O���{�^���ɉ������l�����o��
					else 
						catchNumber = 0;  //�ǂ̃g�O���{�^����������Ă��Ȃ��ꍇ
				});
	}

	@FXML
	void onAddImcomeButtonCliked(ActionEvent event) {
		/*
		 * ���ݕ\������Ă����ʂ����
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//��ʑJ��
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/AddImcome.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("���������o�^");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void onImcomesReportButtonCliked(ActionEvent event) {
		/*
		 * ���ݕ\������Ă����ʂ����
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//��ʑJ��
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/ImcomesReport.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("������������");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void onAddPayButtonCliked(ActionEvent event) throws SQLException {

		//���̓G���[�̍ۂɃA���[�g��\�������鏈��
		Window owner = AddPayButton.getScene().getWindow();

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,
					"���t����͂��ĉ������B");
			return;
		}
		if (PayMoneyTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,
					"�x�o�i�~�j����͂��ĉ������B");
			return;
		}
		if (catchNumber == 0) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,
					"�J�e�S���[��I�����ĉ������B");
			return;
		}

		LocalDate paid_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stmoney = PayMoneyTextField.getText();
		int money = Integer.parseInt(stmoney);
		int category_id = catchNumber;

		MySQLDao.insertRecord(paid_at, memo, money, category_id);

		
		/*
		 * ���ݕ\������Ă����ʂ����
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//��ʑJ��
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/PayDone.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("�o�^����");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String header, String message) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	@FXML
	void onPaysReportButtonCliked(ActionEvent event) {
		/*
		 * ���ݕ\������Ă����ʂ����
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//��ʑJ��
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/PaysReport.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("�o�^�m�F");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}