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
    	String value = ComboBox.getValue(); // ComboBox����l���擾��MySQL�N�G���ƌ��ѕt����
    	
    	 switch (value) {
         case "�����Ƃɂ�����g������":

             break;

         case "����g������":

             break;

         case "��񓖂��蕽�ϊz":

             break;
             
         case "3000�~�ȏ�̎x�o":

        	 break;
        	 
         case "����܂łň�ԍ����x�o":

        	 break;
     }
    	
    	PayMoneyLabel.setText(int?); // �擾�����l�����x���ɕ\��
    }
    
    @FXML
    void onHomeButtonCliked(ActionEvent event) {

    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�C�j�V�����C�Y

	}
}
