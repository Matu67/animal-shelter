package application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {
	//Initialize all necessary variables and lists
	public ArrayList<Dog> dogAL = new ArrayList<>();
	public List<String> dogMaleNames;
	public List<String> dogFemaleNames;
	public static LinkedHashMap <String, List<String>> dogChoices;
	String[] breeds = {"Golden Retriever", "Poodle", "Laborador", "Beagle", 
			"German Shepherd", "English Mastiff", "Pomeranian", "Great Dane",
			"Husky", "Pug"};
	public static Dog selectedDog;
	public static boolean dogReplaced = false;
	
	//Initialize all fxml components
	@FXML private ImageView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
	@FXML private TableView table;
	
	public void initialize() throws IOException
	{
		// When the window opens (for the first time or any other time)...
		Main.mainWindow.setOnShown(e->
		{
			//If a dog has been replaced...
			if (dogReplaced)
			{
				//Add the selected dog to the table
				table.getItems().add(selectedDog);
				int indexOfDog = dogAL.indexOf(selectedDog);
				ArrayList<ImageView> buttons = new ArrayList<>(
						Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8));
				
				try {
					//Get a new dog from the list and put it into a button
					dogAL.set(indexOfDog, new Dog(breeds, dogMaleNames, dogFemaleNames));
					
					updateButton(buttons.get(indexOfDog), dogAL.get(indexOfDog));
				} catch (IOException e1) {
					//If the dog is not correct, reset everything
					e1.printStackTrace();
					dogReplaced = false;
					try {
						resetAll();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				//Apply the shadow border and change boolean value to indicate that no dog has been replaced
				applyShadowBorder(buttons.get(indexOfDog));
				dogReplaced = false;
			}
		});
		
		//Get all dog names from files
		dogMaleNames = Files.readAllLines(Paths.get("c:/Users/ninte/OneDrive/JavaWork/AnimalShelter/src/application/assets/dogMaleNames.txt"));
		dogFemaleNames = Files.readAllLines(Paths.get("c:/Users/ninte/OneDrive/JavaWork/AnimalShelter/src/application/assets/dogFemaleNames.txt"));
		dogChoices = new LinkedHashMap<>();
		
		//Put in breed and colors into list
		List<String> temp1 = Arrays.asList("white", "red", "cream", "light", "dark");
		dogChoices.put("Golden Retriever", temp1);
		
		List<String> temp2 = Arrays.asList("apricot", "black", "brown", "cream", "red", "silver", "white");
		dogChoices.put("Poodle", temp2);
		
		List<String> temp3 = Arrays.asList("black", "yellow", "chocolate");
		dogChoices.put("Laborador", temp3);
		
		List<String> temp4 = Arrays.asList("brown & white", "lemon & white", "red & white", "brown, black & white");
		dogChoices.put("Beagle", temp4);
		
		List<String> temp5 = Arrays.asList("black & tan", "black & red", "blue", "liver & tan", "gray", "white", "black");
		dogChoices.put("German Shepherd", temp5);
		
		List<String> temp6 = Arrays.asList("brindle", "apricot", "fawn");
		dogChoices.put("English Mastiff", temp6);
		
		List<String> temp7 = Arrays.asList("white", "black", "gray", "tan", "orange", "brown");
		dogChoices.put("Pomeranian", temp7);
		
		List<String> temp8 = Arrays.asList("black", "brindle", "fawn", "blue", "mantle", "harlequin");
		dogChoices.put("Great Dane", temp8);
		
		List<String> temp9 = Arrays.asList("black & white", "gray siberian", "silver", "white", "agouti", "copper", "sable");
		dogChoices.put("Husky", temp9);
		
		List<String> temp10 = Arrays.asList("black", "fawn", "apricot");
		dogChoices.put("Pug", temp10);
		
		//Make new dogs and apply picture effects
		createNewDogs();
		resetStage();
	}	
	
	//Event handler for buttons to display dog info and launch other window
	public void btn1Pressed() throws IOException{
		selectedDog = dogAL.get(0);
		displayDog();
	}public void btn2Pressed() throws IOException{
		selectedDog = dogAL.get(1);
		displayDog();
	}public void btn3Pressed() throws IOException{
		selectedDog = dogAL.get(2);
		displayDog();
	}public void btn4Pressed() throws IOException{
		selectedDog = dogAL.get(3);
		displayDog();
	}public void btn5Pressed() throws IOException{
		selectedDog = dogAL.get(4);
		displayDog();
	}public void btn6Pressed() throws IOException{
		selectedDog = dogAL.get(5);
		displayDog();
	}public void btn7Pressed() throws IOException{
		selectedDog = dogAL.get(6);
		displayDog();
	}public void btn8Pressed() throws IOException{
		selectedDog = dogAL.get(7);
		displayDog();
	}
	
	//Insertion sort for name and reset the stage to update changes
	public void sortName() throws IOException
	{
		for (int i = 1; i < 8; i++){
		    for (int j = i; j > 0 && dogAL.get(j-1).getName().compareToIgnoreCase(dogAL.get(j).getName()) > 0; j--){
		        Dog temp = dogAL.get(j);
		        dogAL.set(j, dogAL.get(j-1));
		        dogAL.set(j-1, temp);;
		    }
		}
		resetStage();
	}
	
	//Insertion sort for age and reset the stage to update changes
	public void sortAge() throws IOException
	{
		for (int i = 1; i < 8; i++){
		    for (int j = i; j > 0 && dogAL.get(j-1).getAge() > dogAL.get(j).getAge(); j--){
		        Dog temp = dogAL.get(j);
		        dogAL.set(j, dogAL.get(j-1));
		        dogAL.set(j-1, temp);;
		    }
		}
		
		resetStage();
	}

	//Insertion sort for breed and reset the stage to update changes
	public void sortBreed() throws IOException
	{
		for (int i = 1; i < 8; i++){
		    for (int j = i; j > 0 && dogAL.get(j-1).getBreed().compareToIgnoreCase(dogAL.get(j).getBreed()) > 0; j--){
		        Dog temp = dogAL.get(j);
		        dogAL.set(j, dogAL.get(j-1));
		        dogAL.set(j-1, temp);;
		    }
		}
		resetStage();
	}

	//Insertion sort for color and reset the stage to update changes
	public void sortColor() throws IOException
	{
		for (int i = 1; i < 8; i++){
		    for (int j = i; j > 0 && dogAL.get(j-1).getColor().compareToIgnoreCase(dogAL.get(j).getColor()) > 0; j--){
		        Dog temp = dogAL.get(j);
		        dogAL.set(j, dogAL.get(j-1));
		        dogAL.set(j-1, temp);;
		    }
		}
		resetStage();
	}

	//Insertion sort for gender and reset the stage to update changes
	public void sortGender() throws IOException
	{
		for (int i = 1; i < 8; i++){
		    for (int j = i; j > 0 && dogAL.get(j-1).getGender().compareToIgnoreCase(dogAL.get(j).getGender()) > 0; j--){
		        Dog temp = dogAL.get(j);
		        dogAL.set(j, dogAL.get(j-1));
		        dogAL.set(j-1, temp);;
		    }
		}
		resetStage();
	}
	
	//Hover effect for all buttons
	public void btn1OnHover(){
		onHover(btn1);
	} public void btn2OnHover(){
		onHover(btn2);
	} public void btn3OnHover(){
		onHover(btn3);
	} public void btn4OnHover(){
		onHover(btn4);
	} public void btn5OnHover(){
		onHover(btn5);
	} public void btn6OnHover(){
		onHover(btn6);
	} public void btn7OnHover(){
		onHover(btn7);
	} public void btn8OnHover(){
		onHover(btn8);
	}
	public void btn1OffHover(){
		offHover(btn1);
	} public void btn2OffHover(){
		offHover(btn2);
	} public void btn3OffHover(){
		offHover(btn3);
	} public void btn4OffHover(){
		offHover(btn4);
	} public void btn5OffHover(){
		offHover(btn5);
	} public void btn6OffHover(){
		offHover(btn6);
	} public void btn7OffHover(){
		offHover(btn7);
	} public void btn8OffHover(){
		offHover(btn8);
	}
	
	public void onHover(ImageView button)
	{
		// Set up a scale transition for the button
		ScaleTransition trans = new ScaleTransition(Duration.millis(500), button);
		trans.setFromX(1.0);
		trans.setToX(1.02);
		trans.setFromY(1.0);
		trans.setToY(1.02);
		// Play the Animation
		trans.play();       
	}
	public void offHover(ImageView button)
	{
		// Set up a scale transition for the button
		ScaleTransition trans = new ScaleTransition(Duration.millis(500), button);
		trans.setFromX(1.02);
		trans.setToX(1.0);
		trans.setFromY(1.02);
		trans.setToY(1.0);
		// Play the Animation
		trans.play();
	}
	
	public void applyShadowBorder(ImageView button)
	{
		Rectangle clip = new Rectangle(192, 145);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        button.setClip(clip);
        
        // The following four chunks of code have been taken and modified from:
        // https://stackoverflow.com/questions/20489908/border-radius-and-shadow-on-imageview

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = button.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        button.setClip(null);

        // apply a shadow effect
        button.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        button.setImage(image);
	}

	public void updateButton(ImageView button, Dog dog) throws IOException
	{
		//Get the dog picture and display it on the button
		InputStream stream = Files.newInputStream(Paths.get("src/application/assets/" + dog.getBreed().toLowerCase().replace(' ', '_') + "_" + dog.getColor().replace(' ', '_') + ".jpg"));
		button.setImage(new Image(stream));
	}
	
	public void createNewDogs()
	{
		//Generate and replace 8 dogs on the list
		for (int i = 0; i < 8; i++)
		{
			dogAL.add(new Dog(breeds, dogMaleNames, dogFemaleNames));
		}
	}
	
	@FXML
	public void displayDog() throws IOException
	{
		//Method to change dog window
		Parent informationFXML = FXMLLoader.load(getClass().getResource("Information.fxml"));
		Stage informationWindow = new Stage();
		Scene informationScene = new Scene(informationFXML);
		informationWindow.setScene(informationScene);
		informationWindow.setTitle("Adopt Me!");
		informationWindow.setResizable(false);
		informationWindow.sizeToScene();
		informationWindow.show();
		Main.mainWindow.hide();
	}
	
	public void resetStage() throws IOException
	{
		//Make arraylist of all buttons and update them and apply effects
		ArrayList<ImageView> buttons = new ArrayList<>(
		        Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8));
		
		for (int i = 0; i < 8; i++)
		{
			updateButton(buttons.get(i), dogAL.get(i));
			applyShadowBorder(buttons.get(i));
		}
	}
	
	public void resetAll() throws IOException
	{
		//Clear the dog list and create new dogs, and reset the stage
		dogAL.clear();
		createNewDogs();
		
		resetStage();
	}
	
	public void exit()
	{
		//Exit the application
		System.exit(0);
	}
}

