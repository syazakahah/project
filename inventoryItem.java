/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.oop_project;

import java.time.LocalDate;
import java.util.Date;

public class inventoryItem{
    
    //Instance variables (based on what Iman listed at whatsapp group)
    private String itemName;
    private String location;
    private double purchasePrice;
    private Date datePurchased;
    private String storeOrWebsite;
    private String note;
    private String photoFilePath;
    
    public inventoryItem(String itemName, String location, double purchasePrice, Date datePurchased, String storeWebsite, String note) {
        this.itemName = itemName;
        this.location = location;
        this.purchasePrice = purchasePrice;
        this.datePurchased = datePurchased;
        this.storeOrWebsite = storeWebsite;
        this.note = note;
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