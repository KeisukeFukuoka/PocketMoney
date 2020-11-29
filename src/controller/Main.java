package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main�N���X 
 *�@�A�v���P�[�V������ʂ̕\��
 * �A�v���P�[�V�����N��
 */
public class Main extends Application {
    
	/**
	 * start���\�b�h
	 * �A�v���P�[�V������ʂ̕\��
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
    	loader.setController(new MainController());
    	Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("���������Ǘ��A�v��");
        stage.setScene(scene);
        stage.show();
    }
    
	/**
	 * main���\�b�h
	 * �A�v���P�[�V�����N��
	 */
    public static void main(String[] args) {
        launch(args);
    }
}