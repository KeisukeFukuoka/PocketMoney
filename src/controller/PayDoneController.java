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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * PayDoneController�N���X 
 * �x�o���͊�����ʂ̃R���g���[���[�N���X
 */
public class PayDoneController implements Initializable{

	@FXML
	private Label PayMoneyLabel;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button PaysReportButton;
	@FXML
	private Button HomeButton;

	/**
	 * initialize���\�b�h
	 * ����������
	 *�@���͊����������z��\��
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//Dao�N���XMySQLDao���\�b�h���璼�O�̓��̓f�[�^���擾
		try {
			MySQLDao mysq = new MySQLDao();
			PayMoneyLabel.setText(mysq.selectPayPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onPaysReportButtonCliked���\�b�h
	 * �폜�{�^�������ꂽ�ꍇ�̏���
	 * �z�[����ʂ֑J��
	 */
	@FXML
	public void onDeleteButtonCliked(ActionEvent event) {
		
		//�x�o������ʂ֑J��
		try {
			
		MySQLDao.deletePaysRecord();
		
		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
		loader.setController(new HomeController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("���������Ǘ��A�v��");
		stage.setScene(scene);
		stage.show();
		}catch(IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onPaysReportButtonCliked���\�b�h
	 * �x�o������ʂւ̃����N�{�^�������ꂽ�ꍇ�̏���
	 * �x�o������ʂ֑J��
	 */
	@FXML
	public void onPaysReportButtonCliked(ActionEvent event) {
		
		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//�x�o������ʂ֑J��
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
	
	/**
	 * onHomeButtonCliked���\�b�h
	 * �z�[���{�^���������ꂽ�ꍇ�̏���
	 * �z�[����ʂ֑J��
	 */
	@FXML
	public void onHomeButtonCliked(ActionEvent event) {
		
		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//�z�[����ʂ֑J��
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
			loader.setController(new HomeController());
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