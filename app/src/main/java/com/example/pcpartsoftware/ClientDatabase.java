package com.example.pcpartsoftware;

import java.util.ArrayList;

public class ClientDatabase {
    private ArrayList<Client> clientArrayList;

    public ClientDatabase(){
        clientArrayList = new ArrayList<Client>();
    }

    public boolean checkCredentials(String username, String password){
        boolean doesCredentialExist = false;

        for (Client client : this.clientArrayList) {
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                doesCredentialExist = true;
                break;
            }
        }

        return doesCredentialExist;
    }

    public Client getClientByUsername(String username){
        Client clientCurrent = null;

        for (Client client : this.clientArrayList) {
            if (client.getUsername().equals(username)) {
                clientCurrent = client;
                break;
            }
        }

        return clientCurrent;
    }

    public boolean addNewUser(String username, String password, String email, int phoneNumber, String name, String address) {
        if (getClientByUsername(username) != null) {
            return false;
        } else {
            this.clientArrayList.add(new Client(name, email, username, password, phoneNumber, address));
            return true;
        }
    }
}
