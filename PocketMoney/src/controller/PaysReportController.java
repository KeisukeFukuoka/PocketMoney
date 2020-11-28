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

public class PaysReportController implements Initializable{	//OK
	@FXML
	private TableView<TableViewItem> table;

	@FXML
	private ComboBox<String> cbBox;

	@FXML
	private Button SearchButton;

	@FXML
	private Button Homebutton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�h���������h������ʂ�TableView�ɕ\��������f�[�^�̎擾
		try {
			MySQLDao mysq = new MySQLDao();

			table.setItems(mysq.selectTableViewPays());

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}

		//		//�R���{�{�b�N�X�ɍ��ڂ�ǉ� ���ł�fxml�Ŏ����ς݁I�I�I�I
		//		cbBox.setItems(FXCollections.observableArrayList());
		//		cbBox.getItems().add("�����Ƃɂ�����g������");
		//		cbBox.getItems().add("����g������");
		//		cbBox.getItems().add("��񓖂��蕽�ϊz");
		//		cbBox.getItems().add("3000�~�ȏ�̎x�o");
		//		cbBox.getItems().add("����܂łň�Ԉ����x�o");
		//		cbBox.getItems().add("����܂łň�ԍ����x�o");
	}

	@FXML
	void onSearchButton(ActionEvent event) {

		String search = cbBox.getSelectionModel().getSelectedItem();
		System.out.println(search);

		switch(search) {
		case "�����Ƃɂ�����g������":
			//���ꂼ��I�����ꂽ�J�e�S���ɉ����āAMySQL�֑}��
			System.out.println(search);
			break;

		case "����g������":
			System.out.println(search);
			break;

		case "��񓖂��蕽�ϊz":
			System.out.println(search);			
			break;

		case "3000�~�ȏ�̎x�o":
			System.out.println(search);
			break;

		case "����܂łň�ԍ����x�o":
			System.out.println(search);
			break;

		}
	}

	@FXML
	void onHomeButtonCliked(ActionEvent event) {
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
