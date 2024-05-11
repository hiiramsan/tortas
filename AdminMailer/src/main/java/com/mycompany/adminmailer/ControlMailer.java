/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adminmailer;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author carlo
 */
public class ControlMailer {
    
    public void sendEmail(Session session, String fromEmail, String toEmail, String subject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
    }
    
    public void sendPersonalizedEmail(String fromEmail, String toEmail, String subject, String body) {
        final String password = "fhiozcucfvzbmslr"; 

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.socketFactory.port", "465"); 
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.port", "465"); 

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        try {
            sendEmail(session, fromEmail, toEmail, subject, body);
        } catch (Exception ex) {
            System.out.println("Failed to send email: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        
    }
    
    
    public void sendEmailWithAttachment(String fromEmail, String toEmail, String subject, String body, String attachmentPath) {
        final String password = "fhiozcucfvzbmslr"; // correct password for gmail id

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Create MimeBodyPart for the message body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Create MimeBodyPart for the attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));

            // Create Multipart
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Set content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
        } catch (Exception ex) {
            System.out.println("Failed to send email with attachment: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}

