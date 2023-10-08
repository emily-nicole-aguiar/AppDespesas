package com.jorzelio.despesa.model;

public class MinhaDataModel {
    private String date;

    public MinhaDataModel() {
        // Construtor vazio necessário para o Firebase
    }

    public MinhaDataModel(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
