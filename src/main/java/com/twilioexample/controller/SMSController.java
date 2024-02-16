package com.twilioexample.controller;

import com.twilioexample.payload.SMSRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SMSController {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    @PostMapping("/sendSMS")
    public ResponseEntity<String> sendSMS(@RequestBody SMSRequest request) {
        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                            new PhoneNumber(request.getTo()),
                            new PhoneNumber(twilioPhoneNumber),
                            request.getMessage())
                    .create();
            return ResponseEntity.ok("SMS sent successfully. SID " + message.getSid());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send SMS: "+e.getMessage());

    }
    }
}

