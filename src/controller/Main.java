package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Mainクラス 
 *　アプリケーション画面の表示
 * アプリケーション起動
 */
public class Main extends Application {
    
	/**
	 * startメソッド
	 * アプリケーション画面の表示
	 */
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
    	loader.setController(new MainController());
    	Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("お小遣い管理アプリ");
        stage.setScene(scene);
        stage.show();
    }
    
	/**
	 * mainメソッド
	 * アプリケーション起動
	 */
    public static void main(String[] args) {
        launch(args);
    }
}