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
import javafx.scene.layout.GridPane;
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
    private GridPane keyboard;
    
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
    locationField.getItems().addAll("Johor", "Kedah","Kelantan",
            "Melaka","Negeri Sebmilan", "Pahang","Pulau Pinang",
            "Perak","Perlis","Selangor","Terengganu","Sabah");
    locationField.setPrefWidth(425);
    priceField=new TextField();
    dateField=new DatePicker();
    storewebsiteField=new TextField();
    noteField=new TextField();
    FilePathField=new TextField();
    photoView=new ImageView();
    photoView.setFitWidth(250);
    photoView.setPreserveRatio(true);
    keyboard=createkeyboard();
    SearchItemField=new TextField();
    
    
    Label name=new Label("Inventory Name ");
    Label loc=new Label("Location ");
    Label price=new Label("Purchase Price ");
    Label date=new Label("Date Purchased ");
    Label store=new Label("Store/Website ");
    Label notes=new Label("Note ");
    Label photo=new Label("Photo ");
    
  
    
    
    search.setOnAction(e -> search());
   // search.setOnAction(e->readfile(inventoryItems));
    add.setOnAction(e -> add());
    clear.setOnAction(e->clearfields());
    
     exit.setOnAction((ActionEvent t)->{
            confirmExit();
            saveandexit();
        });
          
            
            HBox button1=new HBox(10);
            button1.getChildren().addAll(edit,delete,exit,clear);
            HBox button2=new HBox(10);
            button2.getChildren().addAll(add);
            
           
            
    
    priceanddate.getChildren().addAll(priceField,date,dateField);
    HBox searchpanel=new HBox(5);
    searchpanel.getChildren().addAll(SearchItemField,search);
    VBox searchitem=new VBox(5);
    searchitem.getChildren().addAll(searchpanel,keyboard); 
    HBox pic=new HBox(20);
    pic.getChildren().addAll(searchitem,photoView);
    VBox field = new VBox(8);
    field.getChildren().addAll(iNameField,locationField,priceanddate,storewebsiteField,
            noteField,FilePathField );
    VBox button=new VBox(15);
    button.getChildren().addAll(field,pic,button1);
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
   //readfile(inventoryItems);
    
        
    }
    public GridPane createkeyboard(){
        GridPane tiles=new GridPane();
        //tiles.setAlignment(Pos.C);
        //tiles.setHgap(10);
       // tiles.setVgap(10);
        
        
        String[][] layout={
            {"A","B","C","D","E","F"},
            {"G","H","I","J","K","L"},
            {"M","N","O","P","Q","R"},
            {"S","T","U","V","W","X"},
            {"Y","Z","    "}
            
        };
        for(int i=0;i<layout.length;i++){
            for (int j=0;j<layout[i].length;j++){
                Button button=createbutton(layout[i][j]);
                tiles.add(button, j, i);
            }
        }
        return tiles;
    }
    public Button createbutton(String text){
        Button button= new Button(text);
        if("    ".equals(text)){
            button.setOnAction(e->pastetosearchitem(" "));
        }else{
             button.setOnAction(e->pastetosearchitem(text));
        }
       
        return button;
    }
    public void pastetosearchitem(String text){
        SearchItemField.appendText(text);
    }
    
   
    
    public void search() {
        readfile(inventoryItems);
        String ItemtoSearch=SearchItemField.getText();
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
            InputStream stream = new FileInputStream( item.getPhotoFilePath());
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
  
   public void delete(inventoryItem DeleteItem) {
    String itemname = iNameField.getText();
    String location = locationField.getSelectionModel().getSelectedItem();
    double price = Double.parseDouble(priceField.getText());
    LocalDate Date = dateField.getValue();
    String note = noteField.getText();
    String filepath = FilePathField.getText();
    String website = storewebsiteField.getText();

    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Delete Item");
    confirmation.setContentText("Are you sure you want to delete?");
    Optional<ButtonType> result = confirmation.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
        //inventoryItem itemToDelete = new inventoryItem(itemname, location, price, Date, website, note, filepath);
        inventoryItems.remove(DeleteItem);
        saveandexit();
        clearfields();
        System.out.println("Item is successfully deleted");
        System.out.print("number of items"+ inventoryItems.size());
    } else {
        System.out.println("Deletion is cancelled");
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
                    inventoryItem items=new inventoryItem(itemname,location,price,Date,website,note,filepath);
                    inventoryItems.add(items);
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
        SearchItemField.clear();
        photoView.setImage(null);
    }
    public void saveandexit(){
      
       /* String itemname=iNameField.getText();
        String location=locationField.getSelectionModel().getSelectedItem();
        String price=priceField.getText();
        LocalDate Date=dateField.getValue();
        String note=noteField.getText();
        String filepath=FilePathField.getText();
        String website=storewebsiteField.getText();*/
        
    
            
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(
                "HomeInventoryFile.txt"))){
            for(inventoryItem items : inventoryItems){
        
                writer.write( items.getItemName()+"#"+items.getLocation()+"#"+items.getPurchasePrice()+"#"+items.getDatePurchased()+"#"+
                        items.getStoreOrWebsite()+"#"+items.getNote()+"#"+items.getPhotoFilePath());
                writer.newLine();
            }
            
        } catch (IOException fe){
            System.out.println(fe);
            
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
   
      public void readfile(List<inventoryItem> inventoryItems) {
        inventoryItems.clear();
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