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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

public class PayDoneController implements Initializable{
	
	@FXML
	private Label PayMoneyLabel;
	@FXML
	private Button PaysReportButton;
	@FXML
	private Button HomeButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Main��ʂœ��͂��ꂽ���z���A���x���ɔ��f
		MySQLDao mysq = new MySQLDao();
		try {
			PayMoneyLabel.setText(mysq.selectPayPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onPaysReportButtonCliked(ActionEvent event) {
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
	@FXML
	public void onHomeButtonCliked(ActionEvent event) {
		/*
		 * ���ݕ\������Ă����ʂ����
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
	
		//��ʑJ��
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