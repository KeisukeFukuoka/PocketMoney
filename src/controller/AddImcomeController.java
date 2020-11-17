package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddImcomeController implements Initializable{
	
	@FXML
	private javafx.scene.control.DatePicker DatePicker; //DatePicker→javafx.scene.controlへ変更
	@FXML
	private TextField MemoTextField;
	@FXML
	private TextField ImcomePriceTextField;
	@FXML
	private Button AddImcomeButton;
	@FXML
	private Button HomeButton;

	@FXML
	private void onAddImcomeButtonCliked(ActionEvent event) {
		LocalDate date = DatePicker.getValue();	//DatePickerの警告は一旦保留
		
//		String memo =  MemoTextField.getValue();
		
//		String price =  MemoTextField.getValue();
		
	}
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//イニシャライズ
	}
	
}