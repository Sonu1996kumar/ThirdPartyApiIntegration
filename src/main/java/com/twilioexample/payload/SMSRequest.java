package com.twilioexample.payload;

public class SMSRequest {
    private String to;
    private String message;

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
