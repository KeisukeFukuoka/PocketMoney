package controller;

import java.io.IOException;
import java.net.URL;
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

public class ImcomeDoneController implements Initializable {
	
	@FXML
	private Label IncomePriceLabel;
	@FXML
	private Button ImcomeReportButton;
	@FXML
	private Button HomeButton;
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//イニシャライズ。コントローラのルート要素が完全に処理された後に、コントローラを初期化するためにコールされる。
	}
	
}