package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ImcomesReportController implements Initializable{
	
	@FXML
	private TableView<?>/*�Ȃ��x��*/ table;	//�e�[�u���r���[
	@FXML
	private TableColumn<?, ?> Imcomed_atColumn;			//���t�J����
	@FXML
	private TableColumn<?, String> MemoColumn;				//�����J����
	@FXML
	private TableColumn<?, Integer> ImcomeColumn;				//���z�J����
	@FXML
	private Button HomeButton;					//�z�[���{�^��
	
	//�e�[�u���ɕ\������ID�A�����A���z��3��K���܂ތ^�Ƃ��Č�ق�ImcomeDatabase�N���X���`

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�C�j�V�����C�Y
	}
	
	
	@FXML
	private void onHomeButtonCliked(ActionEvent event) {
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