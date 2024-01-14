package org.openjfx.oop_project;
import javafx.scene.control.DatePicker;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InventoryController {
    private TextField itemNameField;
    private TextField locationField;
    private TextField priceField;
    private DatePicker dateField;
    private TextField storeField;
    private TextArea noteArea;
    private ImageView photoView;

    private static final String INVENTORY_FILE = "HomeInventoryFile.txt";

    private List<inventoryItem> inventoryItems = new ArrayList<>();

    public InventoryController() {
        // Initialize UI components and set up the inventory scene
        VBox root = new VBox(10);
        itemNameField = new TextField();
        locationField = new TextField();
        priceField = new TextField();
        dateField = new DatePicker();
        storeField = new TextField();
        noteArea = new TextArea();
        photoView = new ImageView();

        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button saveAndExitButton = new Button("Save and Exit");

        searchButton.setOnAction(e -> search());
        addButton.setOnAction(e -> add());
        editButton.setOnAction(e -> edit());
        deleteButton.setOnAction(e -> delete());
        saveAndExitButton.setOnAction(e -> saveAndExit());

        root.getChildren().addAll(
                new Label("Item Name:"), itemNameField,
                new Label("Location:"), locationField,
                new Label("Price:"), priceField,
                new Label("Date:"), dateField,
                new Label("Store:"), storeField,
                new Label("Note:"), noteArea,
                new Label("Photo:"), photoView,
                searchButton, addButton, editButton, deleteButton, saveAndExitButton
        );

        Stage inventoryStage = new Stage();
        inventoryStage.setScene(new Scene(root, 600, 400));
        inventoryStage.setTitle("Inventory Manager");
        inventoryStage.show();
    }

    public void search() {
        // Implement search functionality here
        // Display information based on the search result
         String searchItemName = itemNameField.getText(); // Assuming itemNameField is a TextField for the search input

        // Check if the searchItemName is not empty
        if (!searchItemName.isEmpty()) {
            // Iterate through the inventoryItems list to find a match
            for (inventoryItem item : inventoryItems) {
                if (item.getItemName().equalsIgnoreCase(searchItemName)) {
                    // Display information about the found item (you can customize this part based on your UI)
                    displayItemInformation(item);
                    return; // Stop searching once a match is found
                }
            }

            // If no match is found, show an alert
            showAlert("Item Not Found", "The item with name '" + searchItemName + "' was not found.");
        } else {
            // If the search input is empty, show an alert
            showAlert("Invalid Input", "Please enter an item name for the search.");
        }
    }
    private void displayItemInformation(inventoryItem item) {
        // Implement how to display item information in your UI (e.g., update text fields, labels, etc.)
        itemNameField.setText(item.getItemName());
        locationField.setText(item.getLocation());
        priceField.setText(String.valueOf(item.getPurchasePrice()));
        dateField.setValue(item.getDatePurchased());
        storeField.setText(item.getStoreOrWebsite());
        noteArea.setText(item.getNote());
        if (item.getPhotoFilePath() != null && !item.getPhotoFilePath().isEmpty()) {
            displayPhoto(item.getPhotoFilePath());
        } else {
            // Clear the photo view if no photo is available
            photoView.setImage(null);
        }
        // Set other fields accordingly...
    }
    private void displayPhoto(String photoPath) {
        // Load the image and display it in the photoView
        Image image = new Image("file:" + photoPath); // Assuming the photo path is a file path
        photoView.setImage(image);
    }

    private void showAlert(String title, String content) {
        // Show an alert with the specified title and content
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void add() {
         String itemName = itemNameField.getText();
        String location = locationField.getText();
        double purchasePrice = Double.parseDouble(priceField.getText());
        Date datePurchased = dateField.getValue();
        String storeWebsite = storeField.getText();
        String note = noteArea.getText();

        // Validate input (you can add more validation as needed)
        if (itemName.isEmpty() || location.isEmpty() || storeWebsite.isEmpty()) {
            showAlert("Invalid Input", "Please fill in all required fields.");
            return;
        }

        // Create a new InventoryItem
        inventoryItem newItem = new inventoryItem(itemName, location, purchasePrice, datePurchased, storeWebsite, note);

        // Add the new item to the inventoryItems list
        inventoryItems.add(newItem);

        // Show success message or perform additional actions (e.g., clear input fields)
        showAlert("Item Added", "The item '" + itemName + "' has been successfully added to the inventory.");
        clearInputFields(); // Implement this method to clear input fields in your UI
    }
    private void clearInputFields() {
        // Clear input fields in your UI
        itemNameField.clear();
        locationField.clear();
        priceField.clear();
        dateField.setValue(null);
        storeField.clear();
        noteArea.clear();
        
        // Clear other fields accordingly...
    }

    public void edit() {
        // Implement edit functionality here
        // Modify the selected InventoryItem
        inventoryItem selectedItem = getSelectedItem(inventoryItem item);

        // Check if an item is selected
        if (selectedItem != null) {
            // Populate UI components with existing values
            itemNameField.setText(selectedItem.getItemName());
            locationField.setText(selectedItem.getLocation());
            priceField.setText(String.valueOf(selectedItem.getPurchasePrice()));
            dateField.setValue(selectedItem.getDatePurchased());
            storeField.setText(selectedItem.getStoreWebsite());
            noteArea.setText(selectedItem.getNote());
            
            // Allow the user to make changes in the UI (assuming you have an Edit button for confirmation)
            // ...

            // After the user confirms the changes:
            // Update the InventoryItem with the edited values
            selectedItem.setItemName(itemNameField.getText());
            selectedItem.setLocation(locationField.getText());
            selectedItem.setPurchasePrice(Double.parseDouble(priceField.getText()));
            selectedItem.setDatePurchased(dateField.getValue());
            selectedItem.setStoreOrWebsite(storeField.getText());
            selectedItem.setNote(noteArea.getText());

            // Update the UI to reflect the changes (you may need to update your TableView or other UI elements)
            // ...

            // Show success message or perform additional actions
            showAlert("Item Edited", "The item has been successfully edited.");
            clearInputFields(); // Optional: Clear input fields after editing
        } else {
            // If no item is selected, show an alert
            showAlert("No Item Selected", "Please select an item to edit.");
        }
    
    }
private inventoryItem getSelectedItem(inventoryItem item) {
        // Get the selected item from the TableView
       // inventoryItem selectedItem = tableView.getSelectionModel().getSelectedItem();
         while(!(itemNameField.getText().equalsIgnoreCase(item.getItemName()))){
           for(int n=0;n<inventoryItems.size();n++){
               itemNameField.getText().equalsIgnoreCase(item.getItemName());
           }
           n=item[n];
        }
         inventoryItem selectedItem = ;
        return selectedItem;
    }
    public void delete() {
        // Implement delete functionality here
        // Remove the selected InventoryItem from the list
    }

    public void saveAndExit() {
        // Implement save and exit functionality here
        // Save all inventory items to HomeInventoryFile.txt
        try (FileWriter writer = new FileWriter(INVENTORY_FILE)) {
            for (inventoryItem item : inventoryItems) {
                // Write each item to the file
                writer.write(item.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

   
}

