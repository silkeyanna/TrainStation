package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Welcome to your Travel Buddy!
 *
 * Program that creates a user interface in JavaFx.
 * The user is can select a departure and arrival station and a time
 * When the user presses the "Find Route" button the program connects to
 * a SQL database and executes a query which will retrieve the best journey information.
 * The information will then be presented to the user in the interface.
 *
 * 2019-10-24   Juan, Mert, Silke   Full implementation
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); //reads FXML file which contains information about view which is loaded into Stage
        primaryStage.setTitle("Travel Buddy");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show(); //makes Stage visible
    }


    public static void main(String[] args) {
        launch(args); //function in Application class which is part of javafx framework: creates object for controller class and stage object, trigger call to function start
    }
}