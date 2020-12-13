package com.jenkins.app.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String message, String subject, String to) {

        String from = "navinpaudelhelpdesk@gmail.com";

        boolean flag = false;

        //step 1. variable for gmail host
        String host = "smtp.gmail.com";

        //step 2. get the system properties. Data will be in key value pair
        Properties properties = System.getProperties();
        System.out.println("Properties: " + properties);

        //setting important information to properties object

        // step2a. set host in this properties
        properties.put("mail.smtp.host", host);
        //step 2b. set smtp port
        properties.put("mail.smtp.port", "587");
        //step 2c. enable SSL
        properties.put("mail.smtp.starttls.enable", "true");
        //step 2d. set authentication
        properties.put("mail.smtp.auth", "true");

        //step 3. to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("navinhelpdesk@gmail.com", "zafopemycnbjflnm");
            }
        });

        session.setDebug(true);

        //step 4. compose the message. we can keep simple text, multimedia
        MimeMessage mimeMessage = new MimeMessage(session);


        try {
            //from email
            mimeMessage.setFrom(from);

            //adding recipient to message means where this message will go
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            mimeMessage.setSubject(subject);

            //adding text to message
            mimeMessage.setText(message);

            //step 5. Send message using Transport Class
            Transport.send(mimeMessage);

            System.out.println("Sent successfully.........");
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
