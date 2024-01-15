package org.openjfx.homeinventoryproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    InventoryController inventorycontrol=new InventoryController();
    primaryStage.setTitle("HomeInventoryManager");
    primaryStage.setScene(inventorycontrol.getScene());
    primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
