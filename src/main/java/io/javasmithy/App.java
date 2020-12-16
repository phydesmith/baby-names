package io.javasmithy;

import io.javasmithy.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main-layout-borderpane.fxml"));
            stage.setScene(new Scene( (Parent) loader.load(), 800, 800));
            ((MainController)loader.getController()).setStage(stage);
            stage.setTitle("Baby Names");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
