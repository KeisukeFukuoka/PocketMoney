package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
    	loader.setController(new MainController());
    	Parent root = loader.load();
    	//アプリケーションクラスのstartメソッドでは、FXMLLoad#loadの結果はSceneを作るために使う
    	//FXMLのルートノードが何であれ、常にParentとして扱うのがよい
    	
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        
        stage.setTitle("お小遣い管理アプリ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}