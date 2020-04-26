package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //application is a prebuilt java class that provides a framework to manage a JavaFX application
    //responsible for starting the application by calling the start class

    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader is another class prebuilt in java to load the fxml file we build with scenebuilder
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //set the title in our scene
        primaryStage.setTitle("Fish in the Sea");
        //setScene is used to prepare the scene with the attributes from the fxml file and the desired size
        primaryStage.setScene(new Scene(root, 600, 400));
        //build the scene
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch launches the javaFX runtime and the application
        launch(args);
    }
}
