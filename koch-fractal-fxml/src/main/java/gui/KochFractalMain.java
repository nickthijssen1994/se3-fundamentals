package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KochFractalMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/gui.fxml"));
        primaryStage.setTitle("Factorial");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
