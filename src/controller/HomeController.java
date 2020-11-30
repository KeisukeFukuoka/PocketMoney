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
 * HomeController�N���X 
 * �z�[����ʂ̃R���g���[���[�N���X
 */
public class HomeController implements Initializable {

	//���������\��
	@FXML
	private Button AddImcomeButton;
	@FXML
	private Button ImcomesReportButton;
	@FXML
	private Label MoneyLeftLabel;

	//���̓t�H�[��
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

	//�g�O���{�^����������Ă��Ȃ����f��ێ��B�A���[�g�\�������ɓn���B
	private int catchNumber;

	/**
	 * initialize���\�b�h
	 * ����������
	 * ��ʕ\���Ɠ����ɂ��������c���̕\��
	 * �g�O���{�^���̊Ď�
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//�z�[����ʂɂ��������c���̕\��
		try {
			MySQLDao mysq = new MySQLDao();
			MoneyLeftLabel.setText(mysq.selectMoney());
		} catch (SQLException e) {
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
				
				//�I������Ă����
				if (new_toggle != null)
					//�Y���̃g�O���{�^���Ɋ��蓖�Ă�ꂽ�l����
					catchNumber =((int) new_toggle.getUserData()); 		
				
				//�ǂ���I������ĂȂ����
				else 
					//�g�O���{�^���Ɋ��蓖�Ă��Ă��Ȃ��l����
					catchNumber = 0;
		});
	}

	/**
	 * onAddImcomeButtonCliked���\�b�h
	 * �����������͉�ʂւ̃����N�{�^�������ꂽ�ꍇ�̏���
	 * ��ʑJ��
	 */
	@FXML
	void onAddImcomeButtonCliked(ActionEvent event) {

		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//�����������͉�ʂ֑J��
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

	/**
	 * onImcomesReportButtonCliked���\�b�h
	 * ��������������ʂւ̃����N�{�^�������ꂽ�ꍇ�̏���
	 * ��ʑJ��
	 */
	@FXML
	void onImcomesReportButtonCliked(ActionEvent event) {

		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//��������������ʂ֑J��
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

	/**
	 * onAddImcomeButtonCliked���\�b�h
	 * ���̓{�^���������ꂽ�ꍇ�̏���
	 * ���͂�������΃A���[�g�\��
	 * ���̓f�[�^��Dao�N���X�֓n��
	 * ���͊�����ʂ֑J��
	 */
	@FXML
	public void onAddPayButtonCliked(ActionEvent event)  {

		//��O����
		try {
		//���͂�������΃A���[�g��\��
		Window owner = AddPayButton.getScene().getWindow();

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null, "���t����͂��ĉ������B");
			return;
		}
		if (PayMoneyTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,"���z����͂��ĉ������B");
			return;
		}
		if (catchNumber == 0) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,"�J�e�S���[��I�����ĉ������B");
			return;
		}

		//���͓��e���擾���ADao�N���X�֓n��
		LocalDate paid_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stmoney = PayMoneyTextField.getText();
		int money = Integer.parseInt(stmoney);
		int category_id = catchNumber;
		
		//�ڑ��N���X��INSERT���\�b�h���Ăяo���n��
		MySQLDao.insertRecord(paid_at, memo, money, category_id);

		//getScene()���\�b�h�ɂ���ăV�[�����擾
		Scene s = ((Node)event.getSource()).getScene();
        //Window�N���X��hide()���\�b�h�Ō��݂̉�ʂ����B
		Window window = s.getWindow();
		window.hide();


		//���͊�����ʂ֑J��
		Parent parent = FXMLLoader.load(getClass().getResource("/PayDone.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("�o�^����");
		stage.show();
		
		}catch(IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * showAlert���\�b�h
	 * �A���[�g�\�����e��`
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
	 * onPaysReportButtonCliked���\�b�h
	 * �x�o������ʂւ̃����N�{�^�������ꂽ�ꍇ�̏���
	 * �x�o������ʂ֑J��
	 */
	@FXML
	void onPaysReportButtonCliked(ActionEvent event) {
		
		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//��O����
		try {
			//�x�o������ʂ֑J��
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