package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


public class MainController implements Initializable {
    @FXML
    private Button AddImcomeButton;
    @FXML
    private Button ImcomesReportButton;
    @FXML
    private Label MoneyLeftLabel;
    @FXML
    private DatePicker DatePicker;
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
    private TextField MemoTextField;
    @FXML
    private TextField PayMoneyTextField;
    @FXML
    private Button AddPayButton;
    @FXML
    private Button PaysReportButton;
	@FXML
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
	@FXML
	private void noSelectedToggleButton() {
		//カテゴリボタンが何も押されなっかた場合の処理　→　入力ボタン押下時で良い？？
	}
	@Override
	//どのトグルボタンが押されたか？または、どのトグルボタンも押されていない状態になったかを知る処理
	public void initialize(URL location, ResourceBundle resources) {
		FoodExpenses.setUserData(0);
		DailyNecessities.setUserData(1);
		SkillUp.setUserData(2);
		Other.setUserData(3);

		ToggleGroup.selectedToggleProperty().addListener(
			(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
				if (new_toggle != null) {
					toggleButtonSelected((int) new_toggle.getUserData());
				} else {
					noSelectedToggleButton();
				}
			}
		);
		noSelectedToggleButton();
	}

    @FXML
    void onAddImcomeButtonCliked(ActionEvent event) {

    }

    @FXML
    void onImcomesReportButtonCliked(ActionEvent event) {

    }
}