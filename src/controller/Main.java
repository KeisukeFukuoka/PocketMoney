package controller;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {		//　↓　/は必要か不明
    	//FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml.Main.fxml"));
    	AnchorPane root = FXMLLoader.load(getClass().getResource("fxml.Main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}