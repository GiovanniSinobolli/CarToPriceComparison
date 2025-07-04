package com.example.pricetohpGraph;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/pricetohpGraph/cargraph-view.fxml")
        );
        stage.setTitle("Car Data Viewer");
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
