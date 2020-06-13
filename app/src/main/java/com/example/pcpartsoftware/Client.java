package com.example.pcpartsoftware;

import android.os.Parcel;
import android.os.Parcelable;

public class Client implements Parcelable {
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

    protected Client(Parcel in) {
        clientID = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        password = in.readString();
        phoneNumber = in.readInt();
        address = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(clientID);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeInt(phoneNumber);
        dest.writeString(address);
    }
}
