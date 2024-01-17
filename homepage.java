
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class homepage {

    private final Stage primaryStage;

    public homepage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label sceneTitle = new Label("Welcome!");
        sceneTitle.setTextAlignment(TextAlignment.CENTER);
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Button loginBtn = new Button("Login");
        Button regBtn = new Button("Register");

        HBox homepageBtns = new HBox(10);
        homepageBtns.getChildren().addAll(loginBtn, regBtn);
        homepageBtns.setAlignment(Pos.CENTER);
        
        loginBtn.setOnAction((ActionEvent e) -> {
            LogIn login = new LogIn(this, primaryStage);
            primaryStage.setScene(login.getScene());
        });

        regBtn.setOnAction((ActionEvent e) -> {
            Register registration = new Register(this, primaryStage);
            primaryStage.setScene(registration.getScene());
        });

        VBox root = new VBox(5);
        root.getChildren().addAll(sceneTitle, homepageBtns);
        root.setAlignment(Pos.CENTER);
        root.setBorder(new Border(
                new BorderStroke(Color.PINK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(3))));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
    }
    public void switchToInventoryPage() {
        InventoryController inventoryController = new InventoryController();
        primaryStage.setScene(inventoryController.getScene());
    }
    /*public void switchToHomepage() {
        primaryStage.setScene(getScene());
    }*/

    public Scene getScene() {
        return primaryStage.getScene();
    }

    public void show() {
        primaryStage.setScene(getScene());
        primaryStage.show();
    }
}



