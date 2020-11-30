package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import mysql.MySQLDao;

/**
 * ImcomesReportController�N���X 
 * �x�o������ʂ̃R���g���[���[�N���X
 */
public class PaysReportController implements Initializable{	//OK

	@FXML
	private ComboBox<String> cbBox;
	@FXML
	private Button SearchButton;
	@FXML
	private TableView<TableViewProperty> table;
	@FXML
	private Button Homebutton;

	/**
	 * initialize���\�b�h
	 * ����������
	 * Dao�N���X����x�o�����f�[�^�擾
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//��O����
		try {
            //Dao�N���X�̃C���X�^���X����
			MySQLDao mysq = new MySQLDao();
			
			//TableView�ɕ\��������
			table.setItems(mysq.selectTableViewPays());
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onSearchButton���\�b�h
	 * �����{�^���������ꂽ�ꍇ�̏���
	 * �����������Ƃ̕������Dao�N���X�֓n��
	 * �������ʂ̕\��
	 */
	@FXML
	void onSearchButton(ActionEvent event) {

		//�����������Ƃ̕�������R���{�{�b�N�X����擾
		String search = cbBox.getSelectionModel().getSelectedItem();
		
		//��O����
		try {
			//�ڑ��N���X�̃C���X�^���X����
			MySQLDao mysq = new MySQLDao();
			//�Ăяo���悩��̖߂�l�f�[�^��TableView�ɕ\��������
			table.setItems(mysq.searchTableView(search));
			
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
	void onHomeButtonCliked(ActionEvent event) {

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
