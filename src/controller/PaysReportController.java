package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PaysReportController implements Initializable{	//OK
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> Payd_atColumn;

    @FXML
    private TableColumn<?, ?> CategoryColumn;
    
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//�C�j�V�����C�Y
    	
    }

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
    	
    	PayMoneyLabel.setText(value); // �擾�����l�����x���ɕ\��
    }
    
    @FXML
    void onHomeButtonCliked(ActionEvent event) {
		/*
		 * ���ݕ\������Ă����ʂ����
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
	
		//��ʑJ��
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
	    	loader.setController(new MainController());
	    	Parent root = loader.load();
	        Scene scene = new Scene(root);
	        
			Stage stage = new Stage();
	        stage.setTitle("���������Ǘ��A�v��");
	        stage.setScene(scene);
	        stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }
	
	
}
