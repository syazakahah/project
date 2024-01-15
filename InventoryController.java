package org.openjfx.homeinventoryproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryController {

    private TextField iNameField;
    private TextField locationField;
    private TextField priceField;
    private DatePicker dateField;
    private TextField storewebsiteField;
    private TextArea noteArea;
    private TextField FilePathField;
    private ImageView photoView;
    private TextField SearchItemField;
    
    private Scene scene;
    
    private static final String INVENTORY_FILE= "HomeInventoryManager.txt";
    
    private List<inventoryItem> inventoryItems= new ArrayList<>();
    
    public InventoryController(){
       // HBox itemname=new HBox(10);
       // HBox location=new HBox(10);
        HBox priceanddate=new HBox(10);
       // HBox storewebsite=new HBox(10);
       // HBox note=new HBox(10);
       // HBox filepath=new HBox(10);
       // HBox search=new HBox(10);
        
    iNameField=new TextField();
    locationField=new TextField();
    priceField=new TextField();
    dateField=new DatePicker();
    storewebsiteField=new TextField();
    noteArea=new TextArea();
    FilePathField=new TextField();
    photoView=new ImageView();
    SearchItemField=new TextField();
    
    Label name=new Label("Inventory Name ");
    Label loc=new Label("Location ");
    Label price=new Label("Purchase Price ");
    Label date=new Label("Date Purchased ");
    Label store=new Label("Store/Website ");
    Label notes=new Label("Note ");
    Label photo=new Label("Photo ");
    
    priceanddate.getChildren().addAll(priceField,date,dateField);
    
    VBox field = new VBox(10);
    field.getChildren().addAll(iNameField,locationField,priceanddate,storewebsiteField,
            noteArea,FilePathField );
    VBox label=new VBox(20);
    label.getChildren().addAll(name,loc,price, store,notes,photo);
    label.setAlignment(Pos.TOP_LEFT);
    HBox root=new HBox(5);
    root.getChildren().addAll(label,field);
    root.setAlignment(Pos.CENTER);
    
    Stage inventorypage=new Stage();
    scene=new Scene(root,600,400);
    
    inventorypage.setScene(scene);
    //inventorypage.show();
    
        
    }
    public void search() throws FileNotFoundException{
        String ItemtoSearch= iNameField.getText();
        
        if(!ItemtoSearch.isEmpty()){
            for(inventoryItem item : inventoryItems){
                if(item.getItemName().equalsIgnoreCase(ItemtoSearch));
                
                displayItemInformation(item);
                return;
            }
        }
     }   
    public void displayItemInformation(inventoryItem item){
            iNameField.setText(item.getItemName());
            locationField.setText(item.getLocation());
            priceField.setText(String.valueOf(item.getPurchasePrice()));
            dateField.setValue(item.getDatePurchased());
            storewebsiteField.setText(item.getStoreOrWebsite());
            FilePathField.setText(item.getPhotoFilePath());
            noteArea.setText(item.getNote());
            try{
            InputStream stream = new FileInputStream("file:" + item.getPhotoFilePath());
            photoView.setImage(new Image(stream));
            } catch (FileNotFoundException e){
                System.out.println("Message: " + e);
		}
            }
    public void edit(inventoryItem EditItem){ 
         
        Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Edit Item");
        confirmation.setContentText("Are you sure you want to continue?");
        Optional<ButtonType> result=confirmation.showAndWait();
        
        if(result.isPresent()&& result.get()==ButtonType.OK){
            EditItem.setItemName(iNameField.getText());
            EditItem.setLocation(locationField.getText());
            EditItem.setPurchasePrice(Double.parseDouble(priceField.getText()));
            EditItem.setDatePurchased(dateField.getValue());
            EditItem.setNote(noteArea.getText());
            EditItem.setPhotoFilePath(FilePathField.getText());
            EditItem.setStoreOrWebsite(storewebsiteField.getText());
            
            System.out.print("Item is successfully edited");
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
        
        if(result.isPresent()&& result.get()==ButtonType.YES){
            inventoryItems.remove(DeleteItem);
            System.out.print("Item is sucessfully deleted");
        }
        else{
            System.out.print("Deletion is cancelled ");

        }
    }
    public void add(){
        String itemName=iNameField.getText();
        String location=locationField.getText();
        double price=Double.parseDouble(priceField.getText());
        LocalDate date=dateField.getValue();
        String note=noteArea.getText();
      //  String filepath=
    }
     
        
    
    public void readfile(String INVENTORY_FILE) {
        Scanner file;
        try {
            file = new Scanner(new File(INVENTORY_FILE));
        
         String input;
         while(file.hasNext()){
             input=file.nextLine();
             String item[]=input.split("#");
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
         System.out.println();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public Scene getScene(){
        
        return scene;
    }

}