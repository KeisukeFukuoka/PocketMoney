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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * ImcomeDoneController�N���X 
 * �����������͊�����ʂ̃R���g���[���[�N���X
 */
public class ImcomeDoneController implements Initializable {
	
	@FXML
	private Label ImcomePriceLabel;
	@FXML
	private Button ImcomesReportButton;
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
			//Label�ɕ\��������
			ImcomePriceLabel.setText(mysq.selectIncomePrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * onImcomesReportButtonCliked���\�b�h
	 * ��������������ʂւ̃����N�{�^�������ꂽ�ꍇ�̏���
	 * ��ʑJ��
	 */
	@FXML
	private void onImcomesReportButtonCliked(ActionEvent event) {

		//���ݕ\������Ă����ʂ����
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//��������������ʑJ��
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
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