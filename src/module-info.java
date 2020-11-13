module main {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;
	
	exports controller to javafx.graphics, javafx.fxml;
	opens application to javafx.fxml;
}