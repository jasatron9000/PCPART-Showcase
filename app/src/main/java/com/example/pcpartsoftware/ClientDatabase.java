package com.example.pcpartsoftware;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ClientDatabase {
    private ArrayList<Client> clientArrayList;
    private Client currentClient;

    public ClientDatabase(){
        clientArrayList = new ArrayList<Client>();
    }

    public boolean checkCredentials(String username, String password){
        boolean doesCredentialExist = false;

        for (Client client : this.clientArrayList) {

            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                doesCredentialExist = true;

                //Let this particular user be the currentClient
                currentClient = new Client(client);
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

    public Client getCurrentClient() {
        System.out.println(currentClient.getName());
        return new Client(this.currentClient);
    }
}
