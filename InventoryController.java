package org.openjfx.homeinventoryproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InventoryController {

    private TextField iNameField;
    private ComboBox<String> locationField;
    private TextField priceField;
    private DatePicker dateField;
    private TextField storewebsiteField;
    private TextField noteField;
    private TextField FilePathField;
    private ImageView photoView;
    private TextField SearchItemField;
    
    private Button search=new Button("Search");
    private Button add=new Button("Add");
    private Button delete=new Button("Delete");
    private Button edit=new Button("Edit");
    private Button exit=new Button("Exit");
    private Button clear=new Button ("Clear");
    private Scene scene;
    
 
    
    private List<inventoryItem> inventoryItems= new ArrayList<>();
    
    public InventoryController(){
      
        HBox priceanddate=new HBox(15);
       
        
    iNameField=new TextField();
    locationField=new ComboBox<>();
    locationField.getItems().addAll("Kuala Lumpur", "Selangor",
            "Negeri Sebmilan", "Sabah");
    locationField.setPrefWidth(425);
    priceField=new TextField();
    dateField=new DatePicker();
    storewebsiteField=new TextField();
    noteField=new TextField();
    FilePathField=new TextField();
    photoView=new ImageView();
    photoView.setFitWidth(75);
    photoView.setPreserveRatio(true);
    SearchItemField=new TextField();
    
    Label name=new Label("Inventory Name ");
    Label loc=new Label("Location ");
    Label price=new Label("Purchase Price ");
    Label date=new Label("Date Purchased ");
    Label store=new Label("Store/Website ");
    Label notes=new Label("Note ");
    Label photo=new Label("Photo ");
    
  
    
    
    search.setOnAction(e -> search());
    add.setOnAction(e -> add());
    clear.setOnAction(e->clearfields());
    
     exit.setOnAction((ActionEvent t)->{
            confirmExit();
        });
          
            
            HBox button1=new HBox(10);
            button1.getChildren().addAll(edit,delete,exit,clear);
            HBox button2=new HBox(10);
            button2.getChildren().addAll(add,search);
         
    
    priceanddate.getChildren().addAll(priceField,date,dateField);
    
    VBox field = new VBox(8);
    field.getChildren().addAll(iNameField,locationField,priceanddate,storewebsiteField,
            noteField,FilePathField );
    VBox button=new VBox(15);
    button.getChildren().addAll(field,photoView,button1);
    VBox label=new VBox(15);
    label.getChildren().addAll(name,loc,price, store,notes,photo);
    label.setAlignment(Pos.BASELINE_RIGHT);
    HBox root=new HBox(5);
    root.getChildren().addAll(label,button,button2);
    
    root.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, 
    			new CornerRadii(10), new BorderWidths(3))));
    root.setAlignment(Pos.CENTER);
    Stage inventorypage=new Stage();
    scene=new Scene(root,800,500);
    
    
    inventorypage.setScene(scene);
   
    
        
    }
   
    
    public void search() {
        readfile(inventoryItems);
        String ItemtoSearch= iNameField.getText();
        boolean found=false;
        if(!ItemtoSearch.isEmpty()){
            for(inventoryItem item : inventoryItems){
                if(item.getItemName().equalsIgnoreCase(ItemtoSearch)){
                
                displayItemInformation(item);
                found=true;
                } 
            }
            if(!found){
            Alert noItem=new Alert(Alert.AlertType.ERROR);
            noItem.setContentText("Item is not in the record ");
            noItem.show();
            clearfields();
            
            
        }
               
                
             
        } else{
            Alert noItem=new Alert(Alert.AlertType.ERROR);
            noItem.setContentText("Please enter item name ");
            noItem.show();
            
        }
        
     } 

    public void displayItemInformation(inventoryItem item){
            iNameField.setText(item.getItemName());
            locationField.getSelectionModel().select(item.getLocation());
            priceField.setText(String.valueOf(item.getPurchasePrice()));
            dateField.setValue(item.getDatePurchased());
            storewebsiteField.setText(item.getStoreOrWebsite());
            FilePathField.setText(item.getPhotoFilePath());
            noteField.setText(item.getNote());
            try{
            InputStream stream = new FileInputStream("file:" + item.getPhotoFilePath());
            photoView.setImage(new Image(stream));
            } catch (FileNotFoundException e){
                System.out.println("Message: " + e);
		}
             delete.setOnAction(e -> delete(item));
             edit.setOnAction(e-> edit(item));
           
            }
    public void edit(inventoryItem EditItem){ 
        
        
        Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Edit Item");
        confirmation.setContentText("Are you sure you want to continue?");
        Optional<ButtonType> result=confirmation.showAndWait();
        
        if(result.isPresent()&& result.get()==ButtonType.OK){
            EditItem.setItemName(iNameField.getText());
            EditItem.setLocation(locationField.getSelectionModel().getSelectedItem());
            EditItem.setPurchasePrice(Double.parseDouble(priceField.getText()));
            EditItem.setDatePurchased(dateField.getValue());
            EditItem.setNote(noteField.getText());
            EditItem.setPhotoFilePath(FilePathField.getText());
            EditItem.setStoreOrWebsite(storewebsiteField.getText());
            
            System.out.print("Item is successfully edited");
            saveandexit();
            
        }
        else{
            System.out.print("Edition is cancelled ");
        }
    }   
  
    public void delete(inventoryItem DeleteItem){
        Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Edit Item");
        confirmation.setContentText("Are you sure you want to delete?");
        Optional<ButtonType> result=confirmation.showAndWait();
        
        if(result.isPresent()&& result.get()==ButtonType.OK){
            inventoryItems.remove(DeleteItem);
            clearfields();
            saveandexit();
            
            System.out.print("Item is sucessfully deleted");
            
        }
        else{
            System.out.print("Deletion is cancelled ");

        }
    }
    public void add(){
        String itemname=iNameField.getText();
        String location=locationField.getSelectionModel().getSelectedItem();
        double price=Double.parseDouble(priceField.getText());
        LocalDate Date=dateField.getValue();
        String note=noteField.getText();
        String filepath=FilePathField.getText();
        String website=storewebsiteField.getText();
        
        
        if (itemname.isEmpty()||location.isEmpty()||website.isEmpty()||
                filepath.isEmpty()){
           Alert invalidInput=new Alert(Alert.AlertType.INFORMATION);
           invalidInput.setTitle("Invalid Input");
           invalidInput.setContentText("Please fill in all required field");
           invalidInput.showAndWait();
           
            
        } else{
            Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Add Item");
            confirmation.setContentText("Are you sure you want to continue?");
            Optional<ButtonType> result=confirmation.showAndWait();
            
                if(result.isPresent()&&result.get()==ButtonType.OK){
                saveandexit();
                clearfields();
                readfile(inventoryItems);
                } 
                   
                  else{
                    Alert information=new Alert(Alert.AlertType.INFORMATION);
                    information.setContentText("Addition of item is cancelled");
                    information.showAndWait();
                    
                }
        }
    }
        
             
        
    public void clearfields(){
        iNameField.clear();
        locationField.getSelectionModel().clearSelection();
        priceField.clear();
        dateField.setValue(null);
        noteField.clear();
        FilePathField.clear();
        storewebsiteField.clear();
    }
    public void saveandexit(){
      
        String itemname=iNameField.getText();
        String location=locationField.getSelectionModel().getSelectedItem();
        String price=priceField.getText();
        LocalDate Date=dateField.getValue();
        String note=noteField.getText();
        String filepath=FilePathField.getText();
        String website=storewebsiteField.getText();
        
        
    if(!itemname.isEmpty())  { 
            
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(
                "HomeInventoryFile.txt",true))){
            
                writer.write( itemname+"#"+location+"#"+price+"#"+Date+"#"+
                        website+"#"+note+"#"+filepath);
                writer.newLine();
            
        } catch (IOException fe){
            System.out.println(fe);
            
        }
    }
        
    }
     public void confirmExit(){
         saveandexit();
        Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Confirmation Dialog");
        msg.setContentText("Are you sure to exit?");
        Optional<ButtonType> result=msg.showAndWait();
        
        if(result.get()==ButtonType.OK){
            System.exit(0);
        }
    }
    
    
   /* public List<inventoryItem> readfile() {
        List<inventoryItem> inventoryItems = new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader("HomeInventoryFile.txt"))){
            String line;
            while(( line=reader.readLine())!=null){
               String item[]=line.split("#");
             inventoryItem items=new inventoryItem(
                     item[0],
                     item[1],
                     Double.parseDouble(item[2]),
                     LocalDate.parse(item[3]),
                     item[4],
                     item[5],
                     item[6]);
             inventoryItems.add(items);
                
                
            
        }
        }catch(IOException ex){
                ex.printStackTrace();
                }
        return inventoryItems;
    }*/
      public void readfile(List<inventoryItem> inventoryItems) {
        
        try(BufferedReader reader=new BufferedReader(new FileReader("HomeInventoryFile.txt"))){
            String line;
            while(( line=reader.readLine())!=null){
               String item[]=line.split("#");
             inventoryItem items=new inventoryItem(
                     item[0],
                     item[1],
                     Double.parseDouble(item[2]),
                     LocalDate.parse(item[3]),
                     item[4],
                     item[5],
                     item[6]);
             inventoryItems.add(items);
                
                
            
        }System.out.println();
        }catch(IOException ex){
                ex.printStackTrace();
                }
    }

    
    public Scene getScene(){
        
        return scene;
    }

}