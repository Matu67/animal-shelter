package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InformationController {
	@FXML ImageView selectedImage, backgroundImage;
	@FXML Button btnAdopt, btnCancel; 
	@FXML Label lblName, lblAge, lblBreed, lblSex, lblColor;
	
	Dog currentDog;
	
	//Initialize window
	public void initialize() throws IOException
	{
		currentDog = MainController.selectedDog;
		
		InputStream stream = Files.newInputStream(Paths.get("src/application/assets/" + currentDog.getBreed().toLowerCase().replace(' ', '_') + "_" + currentDog.getColor().replace(' ', '_') + ".jpg"));
		selectedImage.setImage(new Image(stream));
		
		Rectangle clip = new Rectangle(192, 145);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        selectedImage.setClip(clip);
        
        // The following four chunks of code have been taken and modified from:
        // https://stackoverflow.com/questions/20489908/border-radius-and-shadow-on-imageview

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = selectedImage.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        selectedImage.setClip(null);

        // apply a shadow effect
        selectedImage.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        selectedImage.setImage(image);
        backgroundImage.setImage(new Image(Files.newInputStream(Paths.get("src/application/assets/background.jpg"))));
        
        //Display dog info
        lblName.setText("|" + currentDog.getName().toUpperCase());
        lblSex.setText(currentDog.getGender());
        lblAge.setText(Integer.toString(currentDog.getAge()));
        lblBreed.setText(currentDog.getBreed());
        lblColor.setText(currentDog.getColor());
	}
	
	public void adopt() throws IOException
	{
		//All window initializations
		Parent thankYouFXML = FXMLLoader.load(getClass().getResource("ThankYou.fxml"));
		Stage thankYouWindow = new Stage();
		Scene thankYouScene = new Scene(thankYouFXML);
		thankYouWindow.setScene(thankYouScene);
		thankYouWindow.setTitle("Thanks!");
		thankYouWindow.setResizable(false);
		thankYouWindow.sizeToScene();
		thankYouWindow.show();
		
		//Modify boolean value to indicate that a dog should be replaced in the gui
		MainController.dogReplaced = true;
		
		//Close the stage and open the "thanks" stage
		Stage currentStage = (Stage) btnAdopt.getScene().getWindow();
		currentStage.close();
	}
	
	public void cancel()
	{
		//Close the stage and go back to the main menu
		Main.mainWindow.show();
		Main.mainWindow.sizeToScene();
		
		Stage currentStage = (Stage) btnAdopt.getScene().getWindow();
		currentStage.close();
	}
}
