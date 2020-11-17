package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


public class MainController implements Initializable {    
	@FXML
	private ToggleGroup ToggleGroup;
	@FXML
	private ToggleButton FoodExpenses = new ToggleButton();
	@FXML
	private ToggleButton DailyNecessities = new ToggleButton();
	@FXML
	private ToggleButton SkillUp = new ToggleButton();
	@FXML
	private ToggleButton Other = new ToggleButton();
	@FXML
	private void toggleButtonSelected(int i) {
		noSelectedToggleButton();
		switch(i) {
		case 0:
			//���ꂼ��I�����ꂽ�J�e�S���ɉ����āAMySQL�֑}��https://docs.oracle.com/javase/jp/9/docs/api/java/lang/module/InvalidModuleDescriptorException.html
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		}
	}
	@FXML
	private void noSelectedToggleButton() {
		//�J�e�S���{�^��������������Ȃ������ꍇ�̏����@���@���̓{�^���������ŗǂ��H�H
	}
	@Override
	//�ǂ̃g�O���{�^���������ꂽ���H�܂��́A�ǂ̃g�O���{�^����������Ă��Ȃ���ԂɂȂ�������m�鏈��
	public void initialize(URL location, ResourceBundle resources) {
		FoodExpenses.setUserData(0);
		DailyNecessities.setUserData(1);
		SkillUp.setUserData(2);
		Other.setUserData(3);

		ToggleGroup.selectedToggleProperty().addListener(
			(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
				if (new_toggle != null) {
					toggleButtonSelected((int)new_toggle.getUserData());
				} else {
					noSelectedToggleButton();
				}
			}
		);
		noSelectedToggleButton();
	}	
}