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
	private ToggleButton FoodExpenses;
	@FXML
	private ToggleButton DailyNecessities;
	@FXML
	private ToggleButton SkillUp;
	@FXML
	private ToggleButton Other;
	
	private void toggleButtonSelected(int i) {
		noSelectedToggleButton();
		switch(i) {
		case 0:
			//それぞれ選択されたカテゴリに応じて、MySQLへ挿入
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		}
	}

	private void noSelectedToggleButton() {
		//カテゴリボタンが何も押されなっかた場合の処理　→　入力ボタン押下時で良い？？
	}
	
	//どのトグルボタンが押されたか？または、どのトグルボタンも押されていない状態になったかを知る処理
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