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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * ImcomesReportController�N���X 
 * ��������������ʂ̃R���g���[���[�N���X
 */
public class ImcomesReportController implements Initializable{

	@FXML
	private TableView<TableViewProperty>table;
	@FXML
	private Button HomeButton;

	/**
	 * initialize���\�b�h
	 * ����������
	 * Dao�N���X���炨�����������f�[�^�擾
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//��O����
		try {
            //Dao�N���X�̃C���X�^���X����
			MySQLDao mysq = new MySQLDao();
			
			//TableView�ɕ\��������
			table.setItems(mysq.selectTableViewIncomes());

		} catch(SQLException e) {
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

		//�z�[����ʂ։�ʑJ��
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