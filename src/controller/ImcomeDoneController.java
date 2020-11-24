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

public class ImcomeDoneController implements Initializable {
	
	@FXML
	private Label ImcomePriceLabel;
	@FXML
	private Button ImcomesReportButton;
	@FXML
	private Button HomeButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//AddImcome画面で入力された金額を、ラベルに反映
		MySQLDao mysq = new MySQLDao();
		try {
			ImcomePriceLabel.setText(mysq.selectIncomePrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onImcomesReportButtonCliked(ActionEvent event) {
		/*
		 * 現在表示されている画面を閉じる
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
		
		//画面遷移
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/ImcomesReport.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("お小遣い履歴");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
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