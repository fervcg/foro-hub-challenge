package com.alura.ForoHubAPI.domain.model;

public enum TopicStatus {
    SOLVED("SOLUCIONADO"),
    DISCUSSED("DISCUTIDO"),
    ACTIVE("ACTIVO");

    private String spanishText;

    TopicStatus(String spanishText){
        this.spanishText = spanishText;
    }

    public String getspanishText(){
        return spanishText;
    }
}

