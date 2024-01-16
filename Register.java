
package org.openjfx.homeinventorymanager;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Register {
    private String expectedUsername = "expectedUsername";
    private String expectedPassword = "expectedPassword";
    private String expectedEmail = "expectedEmail";
    private String expectedFirstname = "expectedFirstname";
    private String expectedLastname = "expectedLastname";
    private Scene sceneRegister;
    private homepage homepage;
    private Stage primaryStage;

    public Register(homepage homepage, Stage primaryStage) {
        this.homepage = homepage;
        Label firstName = new Label("First name");
        Label lastName = new Label("Last Name");
        Label email = new Label("Email");
        Label usnamee = new Label("Username");
        Label pass = new Label("Password");

        VBox first = new VBox(10);
        first.getChildren().addAll(firstName, lastName, email, usnamee, pass);
        first.setAlignment(Pos.CENTER);

        TextField fnText = new TextField();
        TextField lnText = new TextField();
        TextField emelText = new TextField();
        TextField usnameeText = new TextField();
        TextField passText = new TextField();

        VBox second = new VBox(10);
        second.getChildren().addAll(fnText, lnText, emelText, usnameeText, passText);
        second.setAlignment(Pos.CENTER);

        HBox container = new HBox(10);
        container.getChildren().addAll(first, second);
        container.setAlignment(Pos.CENTER);

        Button submit = new Button("Submit");
        Button backk = new Button("Back"); 
       
        HBox button = new HBox(10);
        button.getChildren().addAll(submit, backk);
        button.setAlignment(Pos.CENTER);

        submit.setOnAction((ActionEvent e) -> {
            String firstname = fnText.getText().toString();
            String lastname = lnText.getText().toString();
            String emails = emelText.getText().toString();
            String username = usnameeText.getText().toString();
            String password = passText.getText().toString();

            if (fnText.getText().isEmpty() || lnText.getText().isEmpty() || emelText.getText().isEmpty()|| usnameeText.getText().isEmpty() || passText.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all the fields");
                return;
            }

            if (expectedFirstname.equals(firstname) && expectedLastname.equals(lastname)&& expectedUsername.equals(username) && expectedPassword.equals(password)&& expectedEmail.equals(emails)) {
                infoBox("Registration Successful!", null, "Success");
            } else {
                infoBox("Registration failed! Please fill in your details again", null, "Failed");
            }
        });
        
        backk.setOnAction((ActionEvent e) -> {
            homepage.switchToHomepage();
        });

        VBox root = new VBox(5);
        root.getChildren().addAll(container, button);
        root.setAlignment(Pos.CENTER);
        root.setBorder(new Border(
                new BorderStroke(Color.PINK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(3))));

        sceneRegister = new Scene(root, 600, 400);
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public Scene getScene() {
        return sceneRegister;
    }
}



