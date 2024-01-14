package org.openjfx.homeinventorymanager;
import java.util.Date;

public class inventoryItem {
    
    //Instance variables (based on what Iman listed at whatsapp group)
    private String itemName;
    private String location;
    private double purchasePrice;
    private Date datePurchased;
    private String storeOrWebsite;
    private String note;
    private String photoFilePath;
    
    public inventoryItem(String itemName, String itemLocation, double purchasePrice, Date datePurchased,String purchasePlatform, String note, String photoFilePath){
        this.itemName = itemName;
        this.location = itemLocation;
        this.purchasePrice = purchasePrice;
        this.datePurchased = datePurchased;
        this.storeOrWebsite = purchasePlatform;
        this.note = note;
        this.photoFilePath = photoFilePath;
    }
    
    //Get and set methods for each instance variable
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String itemLocation) {
        this.location = itemLocation;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public String getStoreOrWebsite() {
        return storeOrWebsite;
    }

    public void setStoreOrWebsite(String purchasePlatform) {
        this.storeOrWebsite = purchasePlatform;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhotoFilePath() {
        return photoFilePath;
    }

    public void setPhotoFilePath(String photoFilePath) {
        this.photoFilePath = photoFilePath;
    }    
    
}
