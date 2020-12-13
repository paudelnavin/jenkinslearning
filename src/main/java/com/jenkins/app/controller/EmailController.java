package com.jenkins.app.controller;

import com.jenkins.app.model.EmailRequest;
import com.jenkins.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){
        return "This is my email api";
    }

    //api to send email
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){

        System.out.println(emailRequest);
        boolean result = this.emailService.sendEmail(emailRequest.getMessage(), emailRequest.getSubject(), emailRequest.getTo());
        if(result)
            return ResponseEntity.ok("Email is sent succesfully...");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent...");
    }
}
