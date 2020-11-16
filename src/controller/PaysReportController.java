package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PaysReportController implements Initializable{	//OK
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> Payd_atColumn;

    @FXML
    private TableColumn<?, ?> MemoColumn;

    @FXML
    private TableColumn<?, ?> MoneyColumn;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private Button DecisionButton;
    
    @FXML
    private Label PayMoneyLabel;

    @FXML
    private Button Homebutton;
    

    @FXML
    void onDecisionButton(ActionEvent event) {
    	String value = ComboBox.getValue(); // ComboBoxから値を取得しMySQLクエリと結び付ける
    	
    	 switch (value) {
         case "日ごとにいくら使ったか":

             break;

         case "何回使ったか":

             break;

         case "一回当たり平均額":

             break;
             
         case "3000円以上の支出":

        	 break;
        	 
         case "これまでで一番高い支出":

        	 break;
     }
    	
    	PayMoneyLabel.setText(int?); // 取得した値をラベルに表示
    }
    
    @FXML
    void onHomeButtonCliked(ActionEvent event) {

    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//イニシャライズ

	}
}
