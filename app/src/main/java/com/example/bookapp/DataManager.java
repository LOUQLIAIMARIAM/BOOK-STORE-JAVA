package com.example.bookapp;

public class DataManager {

    private static DataManager instance;
    private String sharedData;

    private DataManager() {
        // Empêcher l'instanciation directe de la classe
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public String getSharedData() {
        return sharedData;
    }

    public void setSharedData(String data) {
        sharedData = data;
    }
}

