package net.mjhutchinson.nodegrapheditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.mjhutchinson.nodegrapheditor.controller.MainWindowController;

/**
 * Created by Michael Hutchinson on 02/01/2016 at 14:05.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainWindow.fxml"));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();
        primaryStage.setTitle("NodeEditor");
        primaryStage.setScene(new Scene(root, 1280, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
