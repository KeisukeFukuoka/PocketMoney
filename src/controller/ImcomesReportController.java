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

public class ImcomesReportController {
	
	@FXML
	private TableView<ImcomeDatabase>/*�Ȃ��x��*/ table;	//�e�[�u���r���[
	@FXML
	private TableColumn Imcomed_atColumn;			//���t�J����
	@FXML
	private TableColumn MemoColumn;				//�����J����
	@FXML
	private TableColumn ImcomeColumn;				//���z�J����
	@FXML
	private Button HomeButton;					//�z�[���{�^��
	
	
	//�e�[�u���ɕ\������ID�A�����A���z��3��K���܂ތ^�Ƃ��Č�ق�ImcomeDatabase�N���X���`
	@FXML
	public void onReportButtonCliked(ActionEvent event_r) {
		
	}
	@FXML
	public void onHomeButtonCliked(ActionEvent event_h) {
		
	}
	
	
}