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
		//”お小遣い”履歴画面のTableViewに表示させるデータの取得
		try {
			MySQLDao mysq = new MySQLDao();

			table.setItems(mysq.selectTableViewPays());

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void onSearchButton(ActionEvent event) {

		String search = cbBox.getSelectionModel().getSelectedItem();
		System.out.println(search);
		try {
			MySQLDao mysq = new MySQLDao();

			table.setItems(mysq.searchTableView(search));

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}

	}

	@FXML
	void onHomeButtonCliked(ActionEvent event) {
		/*
		 * 現在表示されている画面を閉じる
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();

		//画面遷移
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
			loader.setController(new MainController());
			Parent root = loader.load();
			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setTitle("お小遣い管理アプリ");
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
