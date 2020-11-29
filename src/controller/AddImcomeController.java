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
 * AddImcomeController�N���X 
 * �����������͉�ʂ̃R���g���[���[�N���X
 */
public class AddImcomeController implements Initializable{
	//���̓t�H�[��
	@FXML
	private javafx.scene.control.DatePicker DatePicker; //Alart��������NULL�`�F�b�N�ׁ̈BDatePicker��javafx.scene.control�֕ύX
	@FXML
	private TextField MemoTextField;
	@FXML
	private TextField ImcomePriceTextField;
	@FXML
	private Button AddImcomeButton;
	//�z�[���{�^��
	@FXML
	private Button HomeButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * onAddImcomeButtonCliked���\�b�h
	 * ���̓{�^���������ꂽ�ꍇ�̏���
	 * NULL�`�F�b�N�A�A���[�g�\��
	 * ���̓f�[�^��Dao�N���X�֓n��
	 * ���͊�����ʂ֑J��
	 */
	@FXML
	private void onAddImcomeButtonCliked(ActionEvent event) throws SQLException {

		//NULL�̍ۂɃA���[�g��\�������鏈��
		Window owner = AddImcomeButton.getScene().getWindow();

		if (DatePicker.getValue() == null) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,
					"���t����͂��ĉ������B");
			return;
		}
		if (ImcomePriceTextField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "���̓G���[!", null,
					"���z����͂��ĉ������B");
			return;
		}

		//���͓��e���擾���ADao�N���X�֓n��
		LocalDate imcomed_at = DatePicker.getValue();
		String memo = MemoTextField.getText();
		String stimcome = ImcomePriceTextField.getText();
		int imcome = Integer.parseInt(stimcome);
		MySQLDao.insertRecord(imcomed_at, memo, imcome);

		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//���͊�����ʂ֑J��
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/ImcomeDone.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("�o�^����");
			stage.show();
		}catch(IOException e) {
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
	 * onHomeButtonCliked���\�b�h
	 * �z�[���{�^���������ꂽ�ꍇ�̏���
	 * �z�[����ʂ֑J��
	 */
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {

		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//�z�[����ʂ֑J��
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
			loader.setController(new MainController());
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("���������Ǘ��A�v��");
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}