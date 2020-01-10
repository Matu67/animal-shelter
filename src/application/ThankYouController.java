package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ThankYouController {
	@FXML Label lblBigName;
	@FXML Button btnMenu;
	
	public void initialize()
	{
		//Change the big name text to display the dog name
		lblBigName.setText(MainController.selectedDog.getName().toUpperCase() + "!");
	}
	
	public void returnToMenu()
	{
		//Change scenes and window
		Main.mainWindow.show();
		Main.mainWindow.sizeToScene();
		
		Stage currentStage = (Stage) btnMenu.getScene().getWindow();
		currentStage.close();
	}
}
