import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogIn {
    private Scene sceneLogin;
    private homepage homepage;
    private Stage primaryStage;

    public LogIn(homepage homepage, Stage primaryStage) {
        this.homepage = homepage;
        this.primaryStage = primaryStage;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        /*Label sceneTitle = new Label("Login");
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(sceneTitle, 0, 0, 2, 1);*/

        Label usn = new Label("Username");
        Label pass = new Label("Password");

        VBox first = new VBox(10);
        first.getChildren().addAll(usn, pass);
        first.setAlignment(Pos.CENTER);

        TextField usnText = new TextField();
        TextField passText = new TextField();

        VBox second = new VBox(10);
        second.getChildren().addAll(usnText, passText);
        second.setAlignment(Pos.CENTER);

        HBox container1 = new HBox(10);
        container1.getChildren().addAll(first, second);
        container1.setAlignment(Pos.CENTER);

        Button submit = new Button("Submit");
        Button clear = new Button("Clear");

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(submit, clear);
        buttons.setAlignment(Pos.CENTER);

        submit.setOnAction((ActionEvent e) -> {
            String username = usnText.getText().toString();
            String password = passText.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter both username and password");
                return;
            }

            if (authenticateUser(username, password)) {
                infoBox("Login Successful!", null, "Success");
                homepage.switchToInventoryPage();
                
            } else {
                infoBox("Incorrect Username or Password", null, "Failed");
            }
        });

        clear.setOnAction((ActionEvent e) -> {
            usnText.clear();
            passText.clear();
        });

        VBox root = new VBox(5);
        root.getChildren().addAll( container1, buttons);
        root.setAlignment(Pos.CENTER);
        root.setBorder(new Border(new BorderStroke(Color.PINK, BorderStrokeStyle.DASHED,
                new CornerRadii(10), new BorderWidths(3))));

        sceneLogin = new Scene(root, 400, 300);
    }

    private boolean authenticateUser(String username, String password) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("LoginInfo.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("#");

                String storedUsername = parts[0].trim();
                String storedPassword = parts[1].trim();

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return false; // No matching record found
    }

    public Scene getScene() {
        return sceneLogin;
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
}
