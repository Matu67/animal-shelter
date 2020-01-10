package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public static Stage mainWindow;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Initialize all windows
			mainWindow = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainWindow.setScene(scene);
			mainWindow.setTitle("Adopt Me!");
			mainWindow.setResizable(false);
			mainWindow.sizeToScene();
			mainWindow.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
