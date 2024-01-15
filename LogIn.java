
package org.openjfx.homeinventorymanager;

import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Alert;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
//import javafx.scene.text.Text; 
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

public class LogIn {
    private String adminUsername = "oop";
    private String adminPassword = "oop123";
    private Scene scene;
    
    public LogIn(){
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25)); 
        
      Label sceneTitle = new Label("Login");
      sceneTitle.setTextAlignment(TextAlignment.CENTER);
      sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
      grid.add(sceneTitle, 0, 0, 2, 1);
        
      //creating label username 
      Label usn = new Label("Username");       
      
      //creating label password 
      Label pass = new Label("Password"); 
      
       // create an VBox to hold all the labels
       VBox first = new VBox(10); //compound container
       first.getChildren().addAll(usn, pass);
       first.setAlignment(Pos.CENTER);
	  
      //Creating Text Field for username        
      TextField usnText = new TextField();       
      
      //Creating Text Field for password        
      TextField passText = new TextField();  
      
      // create an VBox to hold all the text fields
       VBox second = new VBox(10); //compound container
       second.getChildren().addAll(usnText, passText);
       second.setAlignment(Pos.CENTER);
       
       // create a HBox to hold all two rows
       HBox container1 = new HBox(10); //compound container
       container1.getChildren().addAll(first, second);
       container1.setAlignment(Pos.CENTER);  
        
      //Creating Buttons 
      Button submit = new Button("Submit"); 
      //Button clear = new Button("Clear");  
      
       // create a HBox to hold the buttons
      /* HBox buttons = new HBox(10); //compound container
       buttons.getChildren().addAll(submit, clear);
       buttons.setAlignment(Pos.CENTER);  */
       
       // create a HBox to hold all the items
      /* HBox container2 = new HBox(10); //compound container
       container2.getChildren().addAll(container1, buttons);
       container2.setAlignment(Pos.CENTER);*/ 
       
      //Creating a Grid Pane 
     /* GridPane gridPane = new GridPane();    
      
      //Setting size for the pane  
      gridPane.setMinSize(400, 200); 
       
      //Setting the padding  
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.CENTER); 
       
      //Arranging all the nodes in the grid 
      gridPane.add(sceneTitle, 1, 0);
      gridPane.add(usn, 0, 0); 
      gridPane.add(usnText, 1, 0); 
      gridPane.add(pass, 0, 1);       
      gridPane.add(passText, 1, 1); 
      gridPane.add(submit, 0, 2); 
      gridPane.add(clear, 1, 2); */
      
      
      submit.setOnAction((ActionEvent e) -> {
          String username = usnText.getText().toString();
          String password = passText.getText().toString();
          
          if(usnText.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, "Form Error!",
                      "Please enter your username");
              return;
          }
          if(passText.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, "Form Error!",
                      "Please enter a password");
              return;
          }
          
          if(adminUsername.equals(username) && adminPassword.equals(password)){
              infoBox("Login Successful!", null, "Success");
          } else{
              infoBox("Please enter correct Username and Password", null, "Failed");
          }
      });
      
    VBox root=new VBox(5);
    root.getChildren().addAll(sceneTitle,container1,submit);
    root.setAlignment(Pos.CENTER);
    root.setBorder(new Border(new BorderStroke(Color.PINK, BorderStrokeStyle.DASHED, new CornerRadii(10), new BorderWidths(3)))); 
    
    Stage loginpage = new Stage();
    scene = new Scene(root,600,400);
    
    loginpage.setScene(scene);
    //inventorypage.show();
    //inventorypage.show();
      
      /*Stage loginPage = new Stage();
      //Creating a scene object 
      Scene scenee = new Scene(gridPane);  
      
      //Setting title to the Stage 
      loginPage.setTitle("Grid Pane Example"); 
         
      //Adding scene to the stage 
      loginPage.setScene(scene);
      
      //Adding scene to the stage 
      loginPage.setScene(scenee);*/
    }
        public Scene getScene(){
        
        return scene;
    }
        
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    } 
    
}
