package controller;
	
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        //�A�v���P�[�V�����N���X��start���\�b�h�ł́AFXMLLoad#load�̌��ʂ�Scene����邽�߂ɂ����g��Ȃ��̂�����
    	//�ł�����FXML�̃��[�g�m�[�h�����ł���A���Parent�Ƃ��Ĉ����̂��悢
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        
        stage.setTitle("���������Ǘ��A�v��");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        
        try(Connection con = mysql.MySQLJDBCUtil.getConnection()) {
        	
        	System.out.println(String.format("Connected to database %s "
        			+ "successfully.", con.getCatalog()));
        } catch(SQLException ex) {
        	ex.printStackTrace();
        }
    }
}