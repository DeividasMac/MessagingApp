package com.junglist963.messagingapp;

public class ModelClass {

    String message;
    String from;

    public ModelClass() {
    }

    public ModelClass(String message, String from) {
        this.message = message;
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
