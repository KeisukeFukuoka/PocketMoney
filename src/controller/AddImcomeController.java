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
	private DatePicker DatePicker; //DatePicker�̌x���͈�U�ۗ�
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
		LocalDate date = DatePicker.getValue();	//DatePicker�̌x���͈�U�ۗ�
		
		String memo =  MemoTextField.getValue();
		
		String price =  MemoTextField.getValue();
		
	}
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�C�j�V�����C�Y
	}
	
}