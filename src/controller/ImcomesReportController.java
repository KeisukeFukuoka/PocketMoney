package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ImcomesReportController implements Initializable{
	
	@FXML
	private TableView<?>/*�Ȃ��x��*/ table;	//�e�[�u���r���[
	@FXML
	private TableColumn<?, ?> Imcomed_atColumn;			//���t�J����
	@FXML
	private TableColumn<?, String> MemoColumn;				//�����J����
	@FXML
	private TableColumn<?, Integer> ImcomeColumn;				//���z�J����
	@FXML
	private Button HomeButton;					//�z�[���{�^��
	
	
	//�e�[�u���ɕ\������ID�A�����A���z��3��K���܂ތ^�Ƃ��Č�ق�ImcomeDatabase�N���X���`
	@FXML
	private void onReportButtonCliked(ActionEvent event) {
		
	}
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�C�j�V�����C�Y
	}
	
}