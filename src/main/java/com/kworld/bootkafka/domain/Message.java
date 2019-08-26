package com.kworld.bootkafka.domain;

public class Message implements KafkaMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

