package org.example;

public class User {
    private String username;
    private String password;
    private String address;
    private String city;
    private String email;

    public User(String username, String password, String address, String city, String email) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.city = city;
        this.email = email;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + address + "," + city + "," + email;
    }

    public static User fromString(String userData) {
        String[] parts = userData.split(",");
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}
