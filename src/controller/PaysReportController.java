package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import mysql.MySQLDao;

public class PaysReportController implements Initializable{	//OK
    @FXML
    private TableView<TableViewItem> table;

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
		//”お小遣い”履歴画面のTableViewに表示させるデータの取得
		try {
			MySQLDao mysq = new MySQLDao();

			table.setItems(mysq.selectTableViewPays());

		} catch(SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
			e.printStackTrace();
		}
    	
    }

//    @FXML
//    void onDecisionButton(ActionEvent event) {
//    	String value = ComboBox.getValue(); // ComboBoxから値を取得しMySQLクエリと結び付ける
//    	
//    	 switch (value) {
//    	 
////         case "日ごとにいくら使ったか":
////        	 SELECT SUM(money), paid_at FROM pays GROUP BY paid_at;
////             break;
//
//         case "何回使ったか":
//        	 SELECT COUNT(id) FROM pays;
//             break;
//
//         case "一回当たり平均額":
//        	 SELECT AVG(money) FROM pays;
//             break;
//             
////         case "3000円以上の支出":
////			 SELECT money FROM pays WHERE money > 3000;
////        	 break;
//        	 
//         case "これまでで一番安い支出":
//        	 SELECT MIN(money) FROM pays;
//        	 break;
//        	 
//         case "これまでで一番高い支出":
//        	 SELECT MAX(money) FROM pays;
//        	 break;
//     }
//    	
//    	PayMoneyLabel.setText(value); // 取得した値をラベルに表示
//    }
    
    @FXML
    void onHomeButtonCliked(ActionEvent event) {
		/*
		 * 現在表示されている画面を閉じる
		 */
		Scene s = ((Node)event.getSource()).getScene();
		Window window = s.getWindow();
		window.hide();
	
		//画面遷移
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
	    	loader.setController(new MainController());
	    	Parent root = loader.load();
	        Scene scene = new Scene(root);
	        
			Stage stage = new Stage();
	        stage.setTitle("お小遣い管理アプリ");
	        stage.setScene(scene);
	        stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
    }
	
    @FXML
    void onDecisionButton(ActionEvent event) {
    	
    }
}
