package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIStart extends Application{
	Stage primaryStage;
	@Override
	public void start(Stage arg0) throws Exception {
		this.primaryStage = arg0;
		this.primaryStage.setTitle("SQLServer");
		this.primaryStage.setResizable(false);
		initiate();
		
	}
	private void initiate() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(UIStart.class.getResource("CommandUI.fxml"));
		
		try {
			BorderPane borderPane =(BorderPane) loader.load();
			
			Scene scene = new Scene(borderPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

			e.printStackTrace();
		} 
	}
	 public static void main(String[] args) {
	        launch(args);
	    }
}