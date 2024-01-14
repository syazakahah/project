/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2project;

/**
 *
 * @author iman bakour
 */
public class UserClass {

  // Instance variables
  private String username;
  private String password;
  
  // Constructor
  public UserClass(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // Getter and setter methods
  public String getUsername() {
    return username; 
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }

  // Login method
  public boolean login(String username, String password) {
    // Check entered username & password against 
    // stored credentials
    return true; 
  }
  
  // Register new user method
  public void registerUser(String username, String password) {
    // Store new username & password 
  }
  
}
