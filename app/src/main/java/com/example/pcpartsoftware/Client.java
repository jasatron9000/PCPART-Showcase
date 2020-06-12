package com.example.pcpartsoftware;

public class Client {
    private final int clientID;
    private final String name;
    private final String username;
    private static int clientNum = 0;
    private final String email;
    private final String password;
    private final int phoneNumber;
    private final String address;

    public Client(String name, String email,
                  String username, String password,
                  int phoneNumber, String address){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;

        this.clientID = clientNum;
        clientNum++;
    }

    public int getClientID() {
        return clientID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}