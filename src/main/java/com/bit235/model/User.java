package com.bit235.model;//could or maybe should rename springtime to controller
// bare bones, basic as can be user class. This is where we set the username and password for the user.
// we could also add more fields like email, profile picture, addresses and then pipe it to the database from the controller.
public class User {
    private String username;
    private String password;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}